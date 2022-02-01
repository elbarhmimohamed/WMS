package com.wms.model.stock;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;


    @OneToMany(mappedBy = "categorie")
    private List<Composante> composantes;


}


