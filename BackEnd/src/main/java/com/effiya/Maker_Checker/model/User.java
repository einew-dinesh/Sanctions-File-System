package com.effiya.Maker_Checker.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@Column(name = "user_id", length = 45)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "user_name", length = 100)
	private String userName;
	@Column(name = "user_email", length = 100)
	private String userEmail;
	@Column(name = "user_role", length = 40)
	private String userRole;
	@Column(name = "password", length = 100)
	private String password;
	@Column(name = "created_on", length = 100)
	private Timestamp createdOn;
	@Column(name = "last_log", length = 100)
	private Timestamp lastLog;

	public User(String username, String userEmail, String userRole, String password) {

		this.userName = username;
		this.userEmail = userEmail;
		this.userRole = userRole;
		this.password = password;
	}

	@PrePersist
	protected void onCreate() {
		createdOn = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		lastLog = new Timestamp(System.currentTimeMillis());
	}

}
