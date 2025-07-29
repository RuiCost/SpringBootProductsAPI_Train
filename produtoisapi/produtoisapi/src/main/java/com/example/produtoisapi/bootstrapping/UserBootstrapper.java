package com.example.produtoisapi.bootstrapping;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepo;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void run(final String... args) throws Exception {

        if (userRepo.findByUsername("admin@mail.com").isEmpty()) {
            final User u1 = new User("admin@mail.com", encoder.encode("Admin123"), "Admin");
            u1.addAuthority(new Role(Role.ADMIN));
            userRepo.save(u1);
        }

        if (userRepo.findByUsername("admin1@mail.com").isEmpty()) {
            final User u1 = new User("admin1@mail.com", encoder.encode("Admin123"), "Admin1");
            u1.addAuthority(new Role(Role.ADMIN));
            userRepo.save(u1);
        }

        if (userRepo.findByUsername("customer@mail.com").isEmpty()) {
            final User u2 = new User("customer@mail.com", encoder.encode("Customer1"), "Customer");
            u2.addAuthority(new Role(Role.CUSTOMER));
            userRepo.save(u2);
        }

        if (userRepo.findByUsername("customer1@mail.com").isEmpty()) {
            final User u2 = new User("customer1@mail.com", encoder.encode("Customer2"), "Customer2");
            u2.addAuthority(new Role(Role.CUSTOMER));
            userRepo.save(u2);
        }

    }
}


