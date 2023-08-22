package com.svalero.TiendaVideojuegos.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pDate;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dDate;
    @Column(columnDefinition = "double default null")

    private double orderPrice;
    @Column
    private String note;

    @OneToOne
    @JoinColumn(name = "clientes_id")
    private Client client;




}
