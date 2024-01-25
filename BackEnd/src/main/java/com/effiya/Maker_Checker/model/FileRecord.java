package com.effiya.Maker_Checker.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FileRecord {
	@Id
	@Column(name = "file_record_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fileRecordId;
	@Column(name = "name")
	private String name;
	@Column(name = "pan")
	private String pan;
	@Column(name = "email")
	private String email;
	@Column(name = "updated_on")
	private Timestamp updatedOn;
	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;

	public FileRecord(String name, String pan, String email, File file) {
		this.name = name;
		this.pan = pan;
		this.email = email;
		this.file = file;
	}

	@PrePersist
	protected void onCreate() {
		updatedOn = new Timestamp(System.currentTimeMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = new Timestamp(System.currentTimeMillis());
		if (file != null) {
			file.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		}
	}

}
