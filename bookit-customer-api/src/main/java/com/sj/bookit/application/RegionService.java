package com.sj.bookit.application;

import com.sj.bookit.domain.Region;
import com.sj.bookit.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();

        return regions;
    }

}
