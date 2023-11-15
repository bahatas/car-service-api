package com.group3.dto;


import com.group3.enums.GearType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {


    private Long id;
    private String model;
    private String make;
    private String year;
    private BigDecimal price;
    private String color;
    private GearType gearType;

}
