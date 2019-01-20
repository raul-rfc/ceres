package com.rfc.ceres.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "user_info")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	@NotNull
	private String name;

	private String secondName;

	@NotNull
	private String surname;

	@NotNull
	private String second_surname;

	@Email
	@NotNull
	private String email;

	@NotNull
	private String user_name;
}
