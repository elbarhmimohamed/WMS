package com.wms.model.operation;

import com.wms.model.emplacement.Emplacement;
import com.wms.model.stock.Composante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Palette")
public class Palette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="reference")
    private String reference;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fichierStock")
    private FichierStock fichierStock;

    @OneToOne(cascade = CascadeType.ALL)
    private Emplacement emplacement;

    @OneToOne
    @JoinColumn(name="composante")
    private Composante composante;

    private Long quantite;

}
