package com.touati.org.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.touati.org.model.Country;
import com.touati.org.model.Location;
import com.touati.org.repository.CountryRepository;
import com.touati.org.repository.LocationRepository;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public String list(Model model) {
        List<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locations/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Location location = locationRepository.findOne(id);
        Iterable<Country> countries = countryRepository.findAll();
        model.addAttribute("location", location);
        model.addAttribute("countries", countries);
        return "locations/form";
    }

    @PostMapping("save")
    public String save(Location location) {
        locationRepository.save(location);
        return "redirect:/locations";
    }

}
