package com.wms;


import com.wms.model.emplacement.ConfigEmplacement;
import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.Commande;
import com.wms.model.operation.LigneCommande;
import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;

import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.repository.CommandeRepository;
import com.wms.repository.LigneCommandeRepository;
import com.wms.repository.PersonRepository;
import com.wms.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
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

	@Autowired
	private PersonRepository personRepository;


	@Autowired
	private CategorieServices categorieServices;

	@Autowired
	private LigneCommandeRepository ligneCommandeRepository;

	@Autowired
	private CommandeRepository commandeRepository;





	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);




	}

	@Override
	public void run(String... args) throws Exception {



		//test of users
		/*Users admin = new Users();
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


		// test de creation de client et fournisseur



/*

		Person client1 = new Person()  ;
		client1.setName("Clien1");
		client1.setMail("client1@gmail.com");
		client1.setAdress("Rabat");
		client1.setPhone("0612345678");
		client1.setImage("azertyui");
		client1.setRole(true);
		Person client2 = new Person()  ;
		client2.setName("Clien2");
		client2.setMail("client2@gmail.com");
		client2.setAdress("Casablanca");
		client2.setPhone("0612345678");
		client2.setImage("azertyui");
		client2.setRole(true);
		personServices.saveCustomer(client1);
		personServices.saveCustomer(client2);


		Person four1 = new Person()  ;
		four1.setName("fournisseur1");
		four1.setMail("ff1@gmail.com");
		four1.setAdress("Rabat");
		four1.setPhone("0699764567");
		four1.setRole(false);
		personServices.saveSupplier(four1);
		Person four2 = new Person()  ;
		four2.setName("fournisseur2");
		four2.setMail("ff2@gmail.com");
		four2.setAdress("casablanca");
		four2.setPhone("0699764567");
		four2.setRole(false);
		personServices.saveSupplier(four2);

*/






/*
		personServices.saveSupplier(four);

		Transport transport = new Transport();
		transport.setMatricule("AZ123456");
		transportServices.saveTransport(transport);
*/


/*
		Person four2 = new Person()  ;
		four2.setName("fournisseur2");
		four2.setMail("ff2@gmail.com");
		four2.setAdress("casablanca");
		four2.setPhone("0699764567");
		four2.setRole(false);
		personServices.saveSupplier(four2);

		Categorie cat1 = new Categorie();
		cat1.setCategorie_name("categories 1 ");
		cat1.setCategorie_description("L'outil de production représente un investissement important  ...");
		categorieServices.saveCategories(cat1);
		Categorie cat2 = new Categorie();
		cat2.setCategorie_name("categories 2 ");
		cat2.setCategorie_description("L'outil de production représente pour la categorie 3  ...");
		categorieServices.saveCategories(cat2);

		Composante composante1 = new Composante();
		composante1.setType(false);
		composante1.setName("RSD1234");
		composante1.setQuantity(300);
		composante1.setSeuil(25);
		composante1.setCategorie(cat1);
		Composante composante2 = new Composante();
		composante2.setType(false);
		composante2.setName("AZ222222");
		composante2.setQuantity(220);
		composante2.setSeuil(14);
		composante1.setCategorie(cat2);
		composantServices.saveComposante(composante1);
		composantServices.saveComposante(composante2);

		List<Composante> composantes = new ArrayList<Composante>(){};
		composantes.add(composantServices.getComposanteById(Long.valueOf(1)));
		composantes.add(composantServices.getComposanteById(Long.valueOf(6)));




		Commande cmd = new Commande();
		cmd.setPerson(personServices.getPersonById(Long.valueOf(1)));
		cmd.setComposantes(composantes);
		cmd.setUser(usersServices.getUserByName("mohamed"));
		commandeServices.saveReceptionCmd(cmd);

 */

		Categorie cat1 = new Categorie();
		cat1.setCategorie_name("categories 1 ");
		cat1.setCategorie_description("L'outil de production représente un investissement important  ...");
		categorieServices.saveCategories(cat1);

		Composante composante1 = new Composante();
		composante1.setType(false);
		composante1.setName("RSD1234");
		composante1.setQuantity(300);
		composante1.setSeuil(25);
		composantServices.saveComposante(composante1);

		Person four2 = new Person()  ;
		four2.setName("fournisseur2");
		four2.setMail("ff2@gmail.com");
		four2.setAdress("casablanca");
		four2.setPhone("0699764567");
		four2.setRole(false);
		personServices.saveSupplier(four2);

		/*Commande cmd = new Commande();
		cmd.setFournisseur(personServices.getPersonById(Long.valueOf(1)));
		cmd.setReference("cm124778");
		cmd.setDate(new Date());
		LigneCommande ligneCommande = new LigneCommande();
		ligneCommande.setComposante(composantServices.getComposanteById(Long.valueOf(1)));
		ligneCommande.setPrix(20);
		ligneCommande.setQuantite(2000);
		ligneCommandeRepository.save(ligneCommande);

		//List<LigneCommande> ligneCommandes = new ArrayList<>();
		//ligneCommandes.add(ligneCommandeRepository.getById(4L));
		//cmd.setLigneCommande(ligneCommandes);
*/






	}



}

