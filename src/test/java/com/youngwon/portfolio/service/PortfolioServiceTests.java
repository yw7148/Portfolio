package com.youngwon.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.youngwon.portfolio.home.service.PortfolioService;

@SpringBootTest
@ActiveProfiles("test")
public class PortfolioServiceTests {
    @Autowired
    PortfolioService service;

    @Test
    public void testGetProjectPrograms() {
        //given
        int portfolioProjectId = 1;

        var programs = service.programsInProject(portfolioProjectId);
        assertNotNull(programs);
        assertFalse(programs.isEmpty());
    }   
}
