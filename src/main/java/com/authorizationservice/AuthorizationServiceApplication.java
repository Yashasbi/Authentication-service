package com.authorizationservice;

import com.authorizationservice.constants.Constants;
import com.authorizationservice.model.entities.Role;
import com.authorizationservice.service.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Set;

@SpringBootApplication
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AuthorizationServiceApplication.class, args);

		RoleService roleService = applicationContext.getBean(RoleService.class);

		// Create Default role for system.
		final Role ADMIN_ROLE = Role.builder()
				.id(Constants.ADMIN_ROLE_ID)
				.roleName("ADMIN_ROLE")
				.roleDescription("Contains all permission")
				.build();


		final Role DEFAULT_ROLE = Role.builder()
				.id(Constants.DEFAULT_ROLE_ID)
				.roleName("DEFAULT_ROLE")
				.roleDescription("Contains limited permission")
				.build();
		roleService.createRoles(Set.of(ADMIN_ROLE, DEFAULT_ROLE));



	}

}
