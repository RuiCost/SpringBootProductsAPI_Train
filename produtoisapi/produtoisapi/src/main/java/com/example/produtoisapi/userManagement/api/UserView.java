package com.example.produtoisapi.userManagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.produtoisapi.userManagement.model.Role;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {

	private String id;

	private String username;
	private String fullName;

	private Set<Role> authorities;
}
