package com.youngwon.portfolio.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youngwon.portfolio.home.dto.Program;
import com.youngwon.portfolio.home.service.PortfolioService;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;


    @GetMapping("")
    public String portfolioHome(Model model) {
        model.addAttribute("projects", portfolioService.projects());
        return "home";
    }

    @GetMapping("/programs")
    public @ResponseBody List<Program> portfolioProjects(
        @RequestParam("projectId") Integer projectId
    ) {
        return portfolioService.programsInProject(projectId);
    }

    @GetMapping("/cv")
    public String portfolioCVPage() {
        return "portfolio-cv";
    }
}
