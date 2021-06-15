package me.right42.heregather.exception;

public class MeetingNotFoundException extends ApplicationException {
	public MeetingNotFoundException() {
		super("해당 미팅은 존재하지않는 미팅입니다");
	}

	public MeetingNotFoundException(String message) {
		super(message);
	}
}
