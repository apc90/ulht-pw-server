package com.ulht.pw.domain;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name="last_name")
	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;
	private BigInteger nif;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AddressEntity> addresses = new HashSet<>();

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContactEntity> contacts = new HashSet<>();

	@Override
	public String toString(){
		return "Client " + nif;
	}
}
