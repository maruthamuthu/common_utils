package com.core;

public class CoreException extends Exception
{
	private int errorCode = -1;

	public CoreException() {
	}

	public CoreException(String message) {
		super(message);
	}

	public CoreException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public CoreException(Throwable cause) {
		super(cause);
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public CoreException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder(100);
		builder.append(super.toString());
		builder.append(", Error Code: ");
		builder.append(this.getErrorCode());
		return builder.toString();
	}

	public int getErrorCode()
	{
		return errorCode;
	}
}
