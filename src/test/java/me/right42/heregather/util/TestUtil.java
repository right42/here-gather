package me.right42.heregather.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private TestUtil(){}

	public static String objToJson(Object o) throws JsonProcessingException {
		return objectMapper.writeValueAsString(o);
	}
}
