package cn.bx.bid.entity;

import java.util.*;

public class Page<T> {
	public static final int PAGE_SIZE = 10;
	int code = 0, limit = PAGE_SIZE, page = 1;
	String msg = "";
	int count = 0;
	private List<T> data = new ArrayList<>();

	public Page() {
	}

	public Page(int count, List<T> data) {
		super();

		this.count = count;
		this.data = data;
	}

	public Page(int code, int count, int limit, List<T> data) {
		super();
		this.code = code;
		this.count = count;
		this.limit = limit;
		this.data = data;
	}

	public Page(int count, int limit, List<T> data) {

		this.count = count;
		this.limit = limit;
		this.data = data;
	}

	public Page(int code, String msg, int count, List<T> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}