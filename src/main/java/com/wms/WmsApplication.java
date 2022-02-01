package com.wms;


import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
<<<<<<< HEAD
import com.wms.model.stock.Categorie;
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
import com.wms.model.stock.Composante;
import com.wms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
<<<<<<< HEAD

	@Autowired
	private CategorieServices categorieServices;
=======
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4



	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);




	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Iterable<Person> clients = personServices.getClients();
		for (Person client : clients ){
			System.out.println(client);
		}
*/

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

		emp.setRefemplacement("AZ1234");
		//emplacementServices.updateEmplacement(Long.valueOf(1),emp);

<<<<<<< HEAD
		// tst de creation de client et fournisseur

		Person client = new Person()  ;

=======
	/*
		Person client = new Person()  ;
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
		client.setName("SahlTr7el");
		client.setMail("sahltr7el@gmail.com");
		client.setAdress("Rabat");
		client.setPhone("0612345678");
		client.setImage("azertyui");
<<<<<<< HEAD
		client.setRole(true);
		System.out.println(client);
		personServices.saveCustomer(client);


		Person four = new Person()  ;
=======
		System.out.println(client);
		personServices.saveCustomer(client);

		Person four = new Person()  ;

>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
		four.setName("ensias");
		four.setMail("ensias@gmail.com");
		four.setAdress("Rabat");
		four.setPhone("0699764567");
<<<<<<< HEAD
		four.setRole(false);
		personServices.saveSupplier(four);

		// test de modification de client et fournisseur
		/*
		Person client1 = new Person()  ;
		client1.setAdress("Rabat");
		client1.setRole(true);
		personServices.updatePerson(Long.valueOf(1),client1);

		Person four1 = new Person()  ;
		four1.setRole(false);
		four1.setAdress("Rabat");
		personServices.updatePerson(Long.valueOf(2),four1);
		/*
=======


		personServices.saveSupplier(four);

>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
		Transport transport = new Transport();
		transport.setMatricule("AZ123456");
		transportServices.saveTransport(transport);
*/
		Composante composante = new Composante();
<<<<<<< HEAD
=======
		Composante c = new Composante();
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4
		composante.setType(false);
		composante.setName("AZE1234");
		composante.setQuantity(100);
		composante.setSeuil(10);
<<<<<<< HEAD

		Composante composante1 = new Composante();
		composante1.setType(false);
		composante1.setName("RSD1234");
		composante1.setQuantity(300);
		composante1.setSeuil(25);

		Composante composante2 = new Composante();
		composante2.setType(false);
		composante2.setName("AZ00221");
		composante2.setQuantity(120);
		composante2.setSeuil(14);

		composantServices.saveComposante(composante);
		composantServices.saveComposante(composante1);
		composantServices.saveComposante(composante2);
		//Composante c = new Composante();
		//c.setQuantity(200);
		//composantServices.updateComposante(Long.valueOf(1),c);

		Categorie cat = new Categorie();
		cat.setName("categories 1 ");
		cat.setDescription("L'outil de production représente un investissement important ; la composante de main-d'oeuvre dans le prix de revient est relativement faible. Les modifications ...");
		categorieServices.saveCategories(cat);

		Categorie cat1 = new Categorie();
		cat1.setName("categories 1");
		//categorieServices.updateCategories(Long.valueOf(1),cat1);

=======
		//composantServices.saveComposante(composante);
		c.setQuantity(200);
		composantServices.updateComposante(Long.valueOf(1),c);
>>>>>>> f2b7a46a917d3335c2b16ea6dd0df5d10ac97fd4

	}



}

