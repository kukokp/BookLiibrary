package com.iot.api;

import com.iot.api.model.user.AppRole;
import com.iot.api.model.user.AppUser;
import com.iot.api.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = "com.iot")
public class IOTApplication implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    private static final Logger logger = LoggerFactory.getLogger(IOTApplication.class);

    public static void main(String[] args) {
        logger.info("........IOTApplication STARTED........");
        SpringApplication.run(IOTApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("........IOTApplication CommandLineRunner........");

        List<AppRole> enumValues = new ArrayList<AppRole>(EnumSet.allOf(AppRole.class));

        AppUser appUser=new AppUser();
        appUser.setUserName("iotAdmin");
        appUser.setFullName("IOT ADMIN");
        appUser.setMobileNo(9725080848L);
        appUser.setEmail("iot@example.com");
        appUser.setAppRoles(enumValues);
        appUser.setPassword(encoder.encode("admin@123"));

        if (appUserRepository.existsByUserName("iotAdmin")) {
            logger.info("........IOTApplication CommandLineRunner ........ userExits");
            Optional<AppUser> iotAdmin = appUserRepository.findByUserName("iotAdmin");
            appUser.setId(iotAdmin.get().getId());
            appUserRepository.save(appUser);
        } else {
            appUserRepository.save(appUser);
        }
    }
}
