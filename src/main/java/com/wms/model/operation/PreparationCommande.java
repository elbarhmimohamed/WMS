package com.wms.model.operation;

import com.wms.model.personne.Person;
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
@Table(name = "preparationCommande")
public class PreparationCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date")
    private Date date;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Person client;


    @Column(name="reference")
    private String reference;

    @OneToMany(mappedBy="preparationCommande" ,cascade = CascadeType.ALL)
    private List<Lignelivraison> lignelivraisons;




}
