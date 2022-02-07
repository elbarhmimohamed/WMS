package com.wms.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FichierStock")
public class FichierStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="reference")
    private String reference;

    @OneToOne()
    @JoinColumn(name = "reception")
    private Reception reception;

    @OneToMany(mappedBy="fichierStock",cascade = CascadeType.ALL)
    private List<Palette> palettes;




}
