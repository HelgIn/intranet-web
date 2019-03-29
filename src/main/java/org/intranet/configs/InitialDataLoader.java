package org.intranet.configs;

import org.intranet.entity.Role;
import org.intranet.entity.User;
import org.intranet.repositories.RoleRepository;
import org.intranet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Role adminRole = createRole("ROLE_ADMIN");
        Role userRole = createRole("ROLE_USER");


        User user = new User();

        user.setPassword(passwordEncoder.encode("test"));
        user.setUsername("test");
        user.setRoles(Arrays.asList(adminRole, userRole));
        user.setEnabled(true);

        user.setFirstName("Oleg");
        user.setLastName("Koibaev");
        user.setEmail("test@test.com");

        userRepository.save(user);
        alreadySetup = true;
    }

    @Transactional
    public Role createRole(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}