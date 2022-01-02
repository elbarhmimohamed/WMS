package com.wms;


import com.wms.model.operation.Transport;
import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.model.stock.Composante;
import com.wms.services.ComposantServices;
import com.wms.services.PersonServices;
import com.wms.services.TransportServices;
import com.wms.services.UsersServices;
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

		Users admin = new Users();
		Users user = new Users();
		admin.setName("mohamed");
		admin.setPassword("azerty");
		admin.setRole("Admin");
		admin.setEmail("mohamed@gmail.com");
		usersServices.saveUser(admin);

		//user.setEmail("mohamed5elbarhmi@gmail.com");
		//usersServices.updateUSer(admin.getId(),user);

		//admin.setRole();

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

		Composante composante = new Composante();
		composante.setType(false);
		composante.setName("AZER1234");
		composante.setQuantity(100);
		composante.setSeuil(10);
		composantServices.saveComposante(composante);
	}



}

