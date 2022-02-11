package com.wms.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livraison")
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="reference")
    private String reference;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date")

    private Date date;


    private  String transport;

    private  String description;


    @OneToOne
    @JoinColumn(name="preparationCommande_id")
    private PreparationCommande preparationCommande;
}
