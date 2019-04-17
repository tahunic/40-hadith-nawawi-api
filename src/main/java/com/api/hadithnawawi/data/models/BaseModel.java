package com.api.hadithnawawi.data.models;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseModel {

	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Getter
	private LocalDateTime updatedAt;

	@PrePersist
	public void createdAt() {
		this.createdAt = this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void updatedAt() {
		this.updatedAt = LocalDateTime.now();
	}
}
