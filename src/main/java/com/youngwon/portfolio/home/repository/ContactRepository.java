package com.youngwon.portfolio.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youngwon.portfolio.home.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{
    @Query(value = "SELECT MAX(contact.id) FROM ContactEntity contact")
    Integer findMaxId();
}
