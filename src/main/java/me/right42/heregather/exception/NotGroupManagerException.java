package me.right42.heregather.exception;

public class NotGroupManagerException extends ApplicationException {
	public NotGroupManagerException() {
		super("해당 유저는 그룹매니저가 아닙니다.");
	}

	public NotGroupManagerException(String message) {
		super(message);
	}
}
