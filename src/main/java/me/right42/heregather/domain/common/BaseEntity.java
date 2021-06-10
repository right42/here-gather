package me.right42.heregather.domain.common;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

	private LocalDateTime createAt;

	private LocalDateTime updateAt;

	private String createUsername;

	private String updateUsername;

	@PostPersist
	public void postPersist(){
		createAt = LocalDateTime.now();
		updateAt = LocalDateTime.now();

		createUsername = "";
		updateUsername = "";
	}

	@PostUpdate
	public void postUpdate(){
		updateAt = LocalDateTime.now();
		updateUsername = "";
	}

}
