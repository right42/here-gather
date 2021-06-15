package me.right42.heregather.web.dto.response;

import lombok.Getter;

@Getter
public class RestResponse<T> {

	private final T data;

	public RestResponse(T data) {
		this.data = data;
	}
}
