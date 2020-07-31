package com.example;

public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo() {
		Member member = memDao.selectByEmail(MemberLogin.loginEmail);
		if(member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
