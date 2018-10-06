package cn.com.tsjx.common.model;

import cn.com.tsjx.common.util.Utility;

public class Result<T> implements java.io.Serializable {

    private static final long serialVersionUID = -6404817488804373730L;

    private Boolean result;
	
	private String message;
	
	private T object;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	public String toString() {
		return Utility.toString(this);
	}
	
	
}
