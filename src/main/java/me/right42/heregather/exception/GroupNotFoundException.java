package me.right42.heregather.exception;

public class GroupNotFoundException extends ApplicationException{

	public GroupNotFoundException() {
		super("해당 그룹은 없는 그룹입니다.");
	}

	public GroupNotFoundException(String message) {
		super(message);
	}
}
