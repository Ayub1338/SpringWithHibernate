package com.local.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id",updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int userId;

	@Column(name="display_name")
	private String displayName;

	private String email;

	private String password;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", displayName=" + displayName + ", email=" + email + ", password=" + password
				+ "]";
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}