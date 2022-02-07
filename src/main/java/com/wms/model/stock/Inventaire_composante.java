package com.wms.model.stock;


import com.wms.model.operation.Commande;
import com.wms.model.operation.Inventaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Builder(toBuilder = true)
@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventaire_composante")
public class Inventaire_composante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventaire_composante_id")
    private long id;

    @OneToOne
    private Composante composante;

    @Column(name="quantityInReality")
    private long quantityInReality;

    @Column(name="Ecart")
    private long Ecart;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventaire_id")
    private Inventaire inventaire;


}
