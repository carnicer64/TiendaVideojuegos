package com.svalero.TiendaVideojuegos.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutDTO {

    private long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private double cost;
    @JsonFormat(pattern="yyyy-MM-dd")
    private double sale;
    private int barCode;
    private String imageURL;
}
