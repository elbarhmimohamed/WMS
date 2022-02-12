package com.wms.model.operation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="reference")
    private String reference;
    @Column(name="matricule")
    private String matricule;

    @OneToMany(mappedBy = "transport")
    private Collection<Operation> operations;

}
