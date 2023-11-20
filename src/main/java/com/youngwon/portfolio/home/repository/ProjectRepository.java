package com.youngwon.portfolio.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youngwon.portfolio.home.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    
}
