package com.sj.bookit.interfaces;

import com.sj.bookit.application.RegionService;
import com.sj.bookit.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = regionService.getRegions();

        return regions;
    }

    @PostMapping("/regions")
    public ResponseEntity<?> create(
            @RequestBody Region resource
    ) throws URISyntaxException {
        String name = resource.getName();

        Region region = regionService.addRegion(name);

        String url = "/regions/" + region.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
