package com.youngwon.portfolio.home.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contact")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ContactEntity {
    @Id
    Integer id;

    @Column(name="name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "message")
    String message;

    @CreatedDate
    @Column(name="date")
    LocalDateTime  date;
}
