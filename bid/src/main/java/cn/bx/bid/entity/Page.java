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
	public Page( int count, int limit, List<T> data,int page) {

		this.count = count;
		this.limit = limit;
		this.data = data;
		this.page=page;
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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	//计算总页数
	public int getTotalpage(){
		return this.count/this.limit+(this.count%this.limit==0?0:1);
	}
}
