package com.wms;


import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Commande;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import com.wms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class WmsApplication implements CommandLineRunner {

	@Autowired
	private UsersServices usersServices;
	@Autowired
	private PersonServices personServices;
	@Autowired
	private TransportServices transportServices;

	@Autowired
	private ComposantServices composantServices;

	@Autowired
	private EmplacementServices emplacementServices;
	@Autowired
	private ConfigEmplacementServices configEmplacementServices;
	@Autowired
	private CommandeServices commandeServices;



	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);




	}

	@Override
	public void run(String... args) throws Exception {







		Composante composante = new Composante();
		composante.setType(false);
		composante.setName("AZE1234");
		composante.setQuantity(100);
		composante.setSeuil(10);
		composantServices.saveComposante(composante);

		Person four = new Person()  ;

		four.setName("ensias");
		four.setMail("ensias@gmail.com");
		four.setAdress("Rabat");
		four.setPhone("0699764567");
		personServices.saveSupplier(four);


/*
		Commande commande = new Commande();
		commande.setType(false);
		commande.setComposantes(composantServices.getAllComposants());
		commande.setPerson(personServices.getPerson(1L));
		commande.setDate(new Date());
		this.commandeServices.saveReceptionCmd(commande);
*/






		/*
		Iterable<Person> clients = personServices.getClients();
		for (Person client : clients ){
			System.out.println(client);
		}
*/
/*
		//test of users
		Users admin = new Users();
		Users user = new Users();

		admin.setName("mohamed");
		admin.setPassword("azerty");
		admin.setRole("Admin");
		admin.setEmail("mohamed@gmail.com");

		user.setEmail("mohamed5elbarhmi@gmail.com");
		user.setName("mohamed");
		user.setPassword("123456");
		usersServices.saveUser(admin);
		//usersServices.updateUser(Long.valueOf(1),user);

		Emplacement emplacement = new Emplacement();
		Emplacement emp = new Emplacement();
		emplacement.setRefemplacement("Z9001");
		emplacement.setTauxOccupation(80);
		emplacementServices.saveEmplacement(emplacement);
		emp.setRefemplacement("AZ1234");*/
		/*ConfigEmplacement configEmplacement = new ConfigEmplacement();
		configEmplacement.setIndexRangee("A");
		configEmplacement.setNumNiveau(4);
		configEmplacement.setNumRack(4);
		configEmplacement.setNumPosition(3);
		configEmplacement.setOccupation(65);
		configEmplacementServices.saveConfigEmplacement(configEmplacement);*/
		/*configEmplacementServices.saveConfigEmplacement(configEmplacement);*/


		//emplacementServices.updateEmplacement(Long.valueOf(1),emp);

	/*
		Person client = new Person()  ;
		client.setName("SahlTr7el");
		client.setMail("sahltr7el@gmail.com");
		client.setAdress("Rabat");
		client.setPhone("0612345678");
		client.setImage("azertyui");
		System.out.println(client);
		personServices.saveCustomer(client);

		Person four = new Person()  ;

		four.setName("ensias");
		four.setMail("ensias@gmail.com");
		four.setAdress("Rabat");
		four.setPhone("0699764567");


		personServices.saveSupplier(four);

		Transport transport = new Transport();
		transport.setMatricule("AZ123456");
		transportServices.saveTransport(transport);
*/
		/*Composante composante = new Composante();
		Composante c = new Composante();
		composante.setType(false);
		composante.setName("AZE1234");
		composante.setQuantity(100);
		composante.setSeuil(10);
		//composantServices.saveComposante(composante);
		c.setQuantity(200);
		composantServices.updateComposante(Long.valueOf(1),c);*/

	}



}

