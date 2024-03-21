package com.youngwon.portfolio.home.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngwon.portfolio.home.dto.Contact;
import com.youngwon.portfolio.home.dto.Program;
import com.youngwon.portfolio.home.dto.Project;
import com.youngwon.portfolio.home.entity.ContactEntity;
import com.youngwon.portfolio.home.entity.ProjectEntity;
import com.youngwon.portfolio.home.repository.ContactRepository;
import com.youngwon.portfolio.home.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class PortfolioService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ContactRepository contactRepository;

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

    @Transactional
    public List<Program> programsInProject(Integer projectId) {
        if(projectId == null) return List.of();

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

    public Boolean contactYoungwon(Contact contact) {
        if(contact == null) return false;
        Integer maxId = contactRepository.findMaxId();
        ContactEntity entity = ContactEntity.builder()
            .id(maxId == null ? 0 : maxId + 1)
            .name(contact.getName())
            .email(contact.getEmail())
            .message(contact.getMessage())
            .contactDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
            .build();
        
        if(entity == null) return false;
        entity = contactRepository.save(entity);

        return true;
    }
}
