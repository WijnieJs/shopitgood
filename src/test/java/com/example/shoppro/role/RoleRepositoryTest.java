package com.example.shoppro.role;


import com.example.shoppro.entity.Role;
import com.example.shoppro.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
   private RoleRepository repo;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("Admin", "Manage everything");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }
    @Test
    public void testCreateRestRoles() {
            Role roleSalesperson = new Role("Salesperson", "manage products");
            Role roleEditer = new Role("Editor", "can add articles");
            Role roleShopipper = new Role("Shipper", " shop products orders");

        repo.saveAll(List.of(roleSalesperson, roleEditer, roleShopipper ));
    }

}
