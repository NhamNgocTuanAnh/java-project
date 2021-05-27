package com.studentmanager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "email") })
        @Data
public class UserLogin {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotBlank
    // @Size(max = 20)
    @Column(name = "name")
    private String username;

    @NotBlank
    // @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    // @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public UserLogin() {
	}

	public UserLogin(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
