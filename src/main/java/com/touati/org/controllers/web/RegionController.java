package com.touati.org.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.touati.org.model.Region;
import com.touati.org.repository.RegionRepository;

import java.util.List;

@Controller
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public String list(Model model) {
        List<Region> regions = regionRepository.findAll();
        model.addAttribute("regions", regions);
        return "regions/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Region region = regionRepository.findOne(id);
        model.addAttribute("region", region);
        return "regions/form";
    }

    @PostMapping("save")
    public String save(Region region) {
        regionRepository.save(region);
        return "redirect:/regions";
    }

}
