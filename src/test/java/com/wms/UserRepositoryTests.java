package com.wms;

import static org.assertj.core.api.Assertions.assertThat;
import com.wms.model.personne.Users;
import com.wms.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository repo;

    @Test
    public void testCreateUser() {
        Users user = new Users();
        user.setName("mohamed");
        user.setEmail("mohamed@gmail.com");
        user.setPassword("azerty");

        Users savedUser = repo.save(user);

        Users existUser = entityManager.find(Users.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testFindByEmail(){
        String email = "hassna@gmail.com";
        Users user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }
}


