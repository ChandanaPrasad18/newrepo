package com.te.jspiders;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.collect.Lists;
import com.te.jspiders.entity.Admin;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Role;
import com.te.jspiders.repository.AdminRepository;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class JspidersApplication {

	private final AppUserRepository appUserRepository;
	private final AdminRepository adminRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JspidersApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Optional<Role> findByRoleName = roleRepository.findByRoleName("ROLE_EMPLOYEE");
			if (!findByRoleName.isPresent()) {
				Admin admin01 = Admin.builder().adminName("admin01").build();

				AppUser adminAppUser = AppUser.builder().username("admin01").password(passwordEncoder.encode("qwerty"))
						.build();

				Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();

				Role employeeRole = Role.builder().roleName("ROLE_EMPLOYEE").build();

				Role trainerRole = Role.builder().roleName("ROLE_TRAINER").build();

				Role studentRole = Role.builder().roleName("ROLE_STUDENT").build();

				adminAppUser.setRoles(Lists.newArrayList(adminRole));
				adminRole.setAppUsers(Lists.newArrayList(adminAppUser));

				roleRepository.save(employeeRole);
				roleRepository.save(trainerRole);
				roleRepository.save(studentRole);

				adminRepository.save(admin01);
				appUserRepository.save(adminAppUser);
			}

		};
	}

}
