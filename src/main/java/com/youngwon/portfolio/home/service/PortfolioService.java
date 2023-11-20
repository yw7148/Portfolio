package com.youngwon.portfolio.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngwon.portfolio.home.dto.Program;
import com.youngwon.portfolio.home.dto.Project;
import com.youngwon.portfolio.home.entity.ProjectEntity;
import com.youngwon.portfolio.home.repository.ProjectRepository;

@Service
public class PortfolioService {
    @Autowired
    ProjectRepository projectRepository;

    public List<Project> projects() {
        return projectRepository.findAll().stream()
            .map(this::mapToDTO).toList();
    }

    private Project mapToDTO(ProjectEntity project) {
        return Project.builder()
            .category(project.getCategory())
            .id(project.getId())
            .img(project.getImg())
            .name(project.getName())
            .build();
    }

    public List<Program> programsInProject(Integer projectId) {
        ProjectEntity project = projectRepository.findById(projectId).get();

        List<Program> programs = project.getPrograms().stream().map(programProject -> {
            return Program.builder()
                .name(programProject.getProgram().getName())
                .icon(programProject.getProgram().getIcon())
                .link(programProject.getLink())
                .build();
        }).toList();

        return programs;
    }
}
