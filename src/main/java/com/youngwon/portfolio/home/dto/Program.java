package com.youngwon.portfolio.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Program {
    String name;
    String icon;
    String link;
}
