package me.right42.heregather.domain.role.type;

import java.util.Arrays;

import lombok.Getter;
import me.right42.heregather.exception.ApplicationException;

@Getter
public enum RoleType {

	ADMIN(1),
	MANAGER(2),
	NORMAL(3);

	private final int level;

	RoleType(int level) {
		this.level = level;
	}

	public static RoleType findRoleTypeByLevel(int level) {
		return Arrays.stream(values()).filter(value -> value.level == level).findFirst()
			.orElseThrow(ApplicationException::new);
	}
}
