package com.example.backend.repositories.jdbc;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.mappers.ProfileDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    public List<ProfileDTO> findAllProfilesWhoPostedSuitableListing(Long profileId) {
        String query = """
                SELECT 
                    prof.id,prof.name,
                    date_part('year',age(prof.dob)) AS age, prof.email,
                    prof.phone_number,prof.gender,prof.is_roommate_needed,prof.is_housing_needed,
                    list.title,list.description,list.price,list.available_from,list.available_to,list.pet_friendly,list.smoke_free,
                    addr.street, addr.apartment,addr.latitude,addr.longitude
                FROM profiles prof
                JOIN preferences pref ON pref.profile_id = ?
                JOIN listings list ON prof.listing_id = list.id
                JOIN addresses addr ON list.address_id = addr.id
                JOIN gender_preferences gp ON gp.preference_id = pref.id
                JOIN locations loc on loc.id = pref.location_id
                WHERE prof.is_roommate_needed = true
                AND list.deleted_at IS NULL AND list.price BETWEEN pref.budget_min AND pref.budget_max
                AND prof.gender IN (
                    SELECT preffered_gender FROM gender_preferences WHERE preference_id = pref.id
                )
                AND date_part('year',age(prof.dob)) BETWEEN  pref.min_age AND pref.max_age
                AND prof.id NOT IN (SELECT target_profile_id FROM interactions WHERE profile_id = pref.profile_id)
                AND earth_distance(
                    ll_to_earth(addr.latitude, addr.longitude),
                    ll_to_earth(loc.latitude, loc.longitude)
                ) <= pref.radius
                ORDER BY RANDOM()
                LIMIT 10;
                """;
        //TODO union to interactions
        return jdbcTemplate.query(query, new ProfileDtoRowMapper(), profileId);
    }

    public List<ProfileDTO> findAllSuitableRoommates(Long profileId) {
        return null;
    }
}
