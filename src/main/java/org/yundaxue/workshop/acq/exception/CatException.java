package org.yundaxue.workshop.acq.exception;

/**
 * Created by admin on 2019/3/23.
 */
public class CatException extends Exception {
	public static final int SUCCESS = 0;
	public static final int USER_EMPTY = 1;
	public static final int USER_NOT_EXISTS = 2;
	public static final int PASSWORD_ERROR = 3;

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CatException(int code, String msg) {
		super(code + " " +msg);

		this.code = code;
		this.msg = msg;
	}

	public String toString() {
		return this.code +" " +this.msg;
	}
}
