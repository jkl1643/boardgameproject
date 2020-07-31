package com.example;

public class MemberPrinter {
	public void print(Member member) {
		System.out.printf(
				"회원 정보 : ID = %s, 이름 = %s, 전화번호 = %s, 주소 = %s,계정 생성 시간 = %s\n",
				member.getEmail(), member.getName(), member.getTel(), member.getAddress(), member.getRegisterDate());
	}

	
}
