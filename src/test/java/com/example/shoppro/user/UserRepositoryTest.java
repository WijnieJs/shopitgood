package com.example.shoppro.user;


import com.example.shoppro.entity.User;
import com.example.shoppro.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

//import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        // 4 in SqL file
        userRepository.save(  new User("asa", "www", "zx@mail.com", "wwww"));
        userRepository.save(  new User( "sad", "wwww", "teZxst@mail.com", "sadasd"));

    };

    @Test
    public void testFindAll() {
        // Arrange

        // Action
        List<User> all = userRepository.findAll();
        // assert
        assertEquals(6, all.size());
    }

    @Test
    public void testFindById_TRUE() {
        // Action
        Optional<User> user = userRepository.findById(1);
        assertTrue(user.isPresent());
    }
    @Test
    public void testFindById_FALSE() {
        // Action
            Optional<User> user = userRepository.findById(11);
            // assert
        assertFalse(user.isPresent());
    }


}
