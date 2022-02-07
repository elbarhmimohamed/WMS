package com.wms.model.emplacement;

import com.wms.model.operation.Palette;
import com.wms.model.stock.Composante;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Transactional
@Data
@Entity
public class Emplacement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "refemplacement")
    private  String refemplacement;
    @Column(name = "tauxOccupation")
    private  float tauxOccupation;

    @JoinColumn(name="palette_id")
    @OneToOne
    private Palette palette;


}
