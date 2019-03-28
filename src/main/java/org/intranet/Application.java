package org.intranet;

import org.intranet.entity.Authority;
import org.intranet.entity.User;
import org.intranet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);


        BCryptPasswordEncoder bean = run.getBean(BCryptPasswordEncoder.class);

        UserRepository userRepository = run.getBean(UserRepository.class);
        User user = new User();
        user.setFirstName("qwe");
        user.setLastName("qwe");
        user.setUsername("user");
        user.setPassword(bean.encode("123"));
        user.setEnabled(true);
        Authority authority = new Authority();
        authority.setAuthority("ADMIN");
        user.setAuthority(authority);
        userRepository.save(user);
    }
}
