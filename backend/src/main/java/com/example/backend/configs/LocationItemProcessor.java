package com.example.backend.configs;

import com.example.backend.entities.Location;
import org.springframework.batch.item.ItemProcessor;

public class LocationItemProcessor implements ItemProcessor<Location,Location> {
    @Override
    public Location process(Location location) throws Exception {
        return location;
    }
}
