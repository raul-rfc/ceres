package com.rfc.ceres.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "user_info")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	private String secondName;

	@NotNull
	private String surname;

	@NotNull
	private String secondSurname;

	@Email
	@NotNull
	private String email;

	@NotNull
	private String userName;
}
