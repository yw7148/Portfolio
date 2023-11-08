package com.youngwon.portfolio.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngwon.portfolio.home.service.HomeService;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("")
    public String redirectToPortfolio() {
        return "redirect:/portfolio";
    }

    @GetMapping("/portfolio")
    public String home(Model model) {
        model.addAttribute("projects", homeService.projects());
        return "home";
    }
}
