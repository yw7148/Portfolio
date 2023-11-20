package com.youngwon.portfolio.home.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Project {
    int id;
    String name;
    String category;
    String img;
}
