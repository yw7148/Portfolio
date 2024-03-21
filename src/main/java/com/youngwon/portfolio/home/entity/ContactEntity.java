package com.youngwon.portfolio.home.entity;

import java.time.LocalDateTime;

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

    String name;
    String email;
    String message;
    LocalDateTime  contactDate;
}
