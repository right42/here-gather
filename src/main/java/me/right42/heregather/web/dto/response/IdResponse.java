package me.right42.heregather.web.dto.response;

import lombok.Getter;

@Getter
public class IdResponse<T> {

	private final T id;

	public IdResponse(T id) {
		this.id = id;
	}
}
