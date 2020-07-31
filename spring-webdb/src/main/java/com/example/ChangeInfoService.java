package com.example;

public class ChangeInfoService {
	private MemberDao memberDao;
	public String changePassword(String email, String oldPwd, String newPwd, String newPwd2, 
			String name, String tel, String address) {
		System.out.println("수정1");
		Member member = memberDao.selectByEmail(email);
		System.out.println("수정2");
		if (member == null)
			throw new MemberNotFoundException();
		else if (!newPwd.equals(newPwd2))
			throw new PasswordNotMatchException();
		if (!oldPwd.equals(member.getPassword()))
			throw new PasswordNotMatchException2();
		if (newPwd.isEmpty()) {
		} else {
			member.changePassword(oldPwd, newPwd);
			memberDao.update(member);
		}
		if (name.isEmpty()) {
		} else {
			member.changeName(name);
			memberDao.update(member);
		}
		if (tel.isEmpty()) {
		} else {
			member.changeTel(tel);
			memberDao.update(member);
		}
		if (address.isEmpty()) {
		} else {
			member.changeAddress(address);
			memberDao.update(member);
		}
		
		return member.getSavePath();
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}