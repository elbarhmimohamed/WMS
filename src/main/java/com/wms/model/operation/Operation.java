package com.wms.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="date")
    private Date date;
    @Column(name="type")
    private boolean type;  // type == false => reception   //   type == true  => expedition

    @ManyToOne
    private Transport transport;

    @OneToOne
    private  Commande commande;
}