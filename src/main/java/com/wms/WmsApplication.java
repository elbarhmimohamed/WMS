package com.wms;


import com.wms.model.personne.Person;
import com.wms.model.personne.Users;
import com.wms.services.PersonServices;
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
/*
		Users admin = new Users();
		admin.setName("mohamed");
		admin.setPassword("azerty");
		admin.setRole("Admin");
		admin.setEmail("hamza@gmail.com");
		usersServices.saveUser(admin);

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
*/
	}
}
