package com.example.backend.repositories.jpa;

import com.example.backend.entities.Interaction;
import com.example.backend.enums.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    @Query("select inter from Interaction inter where inter.targetProfileId = :id and inter.profileAction = :action")
    List<Interaction> findInteractionWhereProfileWasTarget(@Param("id") Long profileId,@Param("action") Action action);

}
