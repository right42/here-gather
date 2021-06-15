package me.right42.heregather.web.dto.response;

import lombok.Getter;

@Getter
public class ListResponse<T> {

	private final T data;

	public ListResponse(T data) {
		this.data = data;
	}
}
