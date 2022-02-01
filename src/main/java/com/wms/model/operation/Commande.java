package com.wms.model.operation;


import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
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
@Table(name = "commande")
public class Commande{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="date")
    private Date date;
    @Column(name="type")     // type = false  => commande fournosseur  // type == true => commande client
    private boolean type;



    @ManyToOne
    private Users user;

    @ManyToOne
    private Person person;




    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Composante> composantes;




}
