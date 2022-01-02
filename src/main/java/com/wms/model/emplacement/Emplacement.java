package com.wms.model.emplacement;

import com.wms.model.stock.Composante;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
public class Emplacement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private  String refemplacement;
    private  float tauxOccupation;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Composante> composantes;



}
