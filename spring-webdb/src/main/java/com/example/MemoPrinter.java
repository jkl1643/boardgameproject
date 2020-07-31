package com.example;

public class MemoPrinter {
	public void print3(Memo memo) {
		System.out.printf("메모 : 번호 = %d, 이메일 = %s, %s년 %s월 %s일 메모 = %s, 파일저장경로 = %s\n", memo.getId(), memo.getEmail(),
				memo.getYear(), memo.getMonth(), memo.getDay(), memo.getMemo(), memo.getSaveImagePath());		
		System.out.println();
	}
}
