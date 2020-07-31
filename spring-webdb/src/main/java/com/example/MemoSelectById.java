package com.example;

public class MemoSelectById {
	private MemoDao memoDao;
	public Memo memoSelectById() {
		Memo memo = memoDao.selectById(Long.valueOf(MainForSpring.i));
		return memo;
	}
	
	public void setMemoDao(MemoDao memoDao) {
		this.memoDao = memoDao;
	}
}
