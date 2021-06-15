package me.right42.heregather.exception;

public class GroupUserMaximumException extends ApplicationException {

	public GroupUserMaximumException() {
		super("그룹의 인원이 최대인원 입니다");
	}

	public GroupUserMaximumException(String message) {
		super(message);
	}
}
