package com.wms;


import com.wms.model.emplacement.Emplacement;
import com.wms.model.operation.*;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;

import com.wms.model.stock.Categorie;
import com.wms.model.stock.Composante;
import com.wms.model.stock.Inventaire_composante;
import com.wms.repository.InventaireRepository;
import com.wms.repository.PersonRepository;
import com.wms.repository.ReceptionRepository;
import com.wms.repository.UsersRepository;
import com.wms.services.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class WmsApplication implements CommandLineRunner {

	@Autowired
	private UsersServices usersServices;

	@Autowired
	private InventaireRepository inventaireRepository;
	@Autowired
	private PersonServices personServices;
	@Autowired
	private TransportServices transportServices;

	@Autowired
	private ComposantServices composantServices;

	@Autowired
	private EmplacementServices emplacementServices;

	@Autowired
	private UsersRepository usersRepository;



	@Autowired
	private CategorieServices categorieServices;

	@Autowired
	private CommandeServices commandeServices;
	@Autowired
	private InventaireServices inventaireServices;

	@Autowired
	private ReceptionServices receptionServices;



	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);




	}

	@Override
	public void run(String... args) throws Exception {


		//test of users
		Users admin = new Users();
		Users agent = new Users();
		Users operateur = new Users();

		admin.setName("mohamed");
		admin.setPassword("azerty");
		admin.setRole("Admin");
		admin.setEmail("mohamed@gmail.com");

		agent.setName("hamza");
		agent.setPassword("azerty");
		agent.setRole("Agent Expédition/Réception");
		agent.setEmail("hamza@gmail.com");

		operateur.setName("hassna");
		operateur.setPassword("azerty");
		operateur.setRole("Opérateur");
		operateur.setEmail("hassna@gmail.com");


		usersServices.saveUser(admin);
		usersServices.saveUser(agent);
		usersServices.saveUser(operateur);
		//usersServices.updateUser(Long.valueOf(1),user);

		Emplacement emplacement = new Emplacement();
		Emplacement emp = new Emplacement();
		emplacement.setRefemplacement("Z9001");
		emplacement.setTauxOccupation(80);
		emplacementServices.saveEmplacement(emplacement);

		emp.setRefemplacement("AZ1234");
		//emplacementServices.updateEmplacement(Long.valueOf(1),emp);


		// test de creation de client et fournisseur





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








/*
		personServices.saveSupplier(four);

		Transport transport = new Transport();
		transport.setMatricule("AZ123456");
		transportServices.saveTransport(transport);
*/




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




		//commandeServices.saveReceptionCmd(cmd);
/*
		Inventaire_composante invc1 = new Inventaire_composante();
		invc1.setComposante(composantServices.getComposanteById(Long.valueOf(1)));
		invc1.setQuantityInReality(290);

		Inventaire_composante invc2 = new Inventaire_composante();
		invc2.setComposante(composantServices.getComposanteById(Long.valueOf(2)));
		invc2.setQuantityInReality(200);
		List<Inventaire_composante> inventaireComposanteList = new ArrayList<Inventaire_composante>();
		inventaireComposanteList.add(invc1);
		inventaireComposanteList.add(invc2);
		//--------------------
		Inventaire inventaire = new Inventaire();
		//inventaire.setDate(LocalDateTime.now());
		inventaire.setReference("INV-001");
		inventaire.setInventaire_composantes(inventaireComposanteList);
		inventaire.setUser(usersServices.getUserByName("mohamed"));
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println(inventaire.getUser().getRole());
		//inventaireComposanteRepository.saveInvComp(invc1);
		//inventaireComposanteRepository.saveInvComp(invc2);
		//inventaireServices.saveInventaire(inventaire);

*/
		//Inventaire inventaire = inventaireServices.getInventaireById(1);


		//for (int i = 0 ; i < inventaire.getInventaire_composantes().size() )
		//System.out.println(inventaire.getInventaire_composantes().size());



		//inventaireServices.deleteById(Long.valueOf(3));

		Reception rec = new Reception();

		rec = receptionServices.findReceptionById(1);
		Commande cmd = commandeServices.findCommandeById(1);
		System.out.println("--------------");
		//System.out.println(rec.getReference());
		//System.out.println(rec.getDate());
		//System.out.println("reference commande"+rec.getCommande().getReference());

		//System.out.println("size of liste : " + cmd.getLigneCommande().size());




		System.out.println("nombre des categorie : "+categorieServices.getNumberOfCat());
		System.out.println("nombre des comande : "+commandeServices.getNumberOfCommande());
		System.out.println("nombre des clients : "+personServices.numberOfCustomer());
		System.out.println("nombre des fournisseur : "+personServices.numberOfSupplier());
		System.out.println("nombre des Article : "+composantServices.numberOfArticle());
		System.out.println("nombre des Produit : "+composantServices.numberOfProducte());
		System.out.println("nombre des Invantaire : "+inventaireServices.numberOfInvantaire());
		System.out.println("--------------");

		Inventaire di = new Inventaire();
		List<Inventaire> listinv = inventaireServices.getAllInventaire();

		System.out.println("hhhhhhhhhh");
		System.out.println("last inventaire : " + listinv.size());
		System.out.println("hhhhhhhhhh");
	}



}

