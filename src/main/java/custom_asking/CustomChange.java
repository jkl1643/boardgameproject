package custom_asking;

import org.springframework.transaction.annotation.Transactional;


public class CustomChange {
	private CustomDao customdao;

	public void setCustomChange(CustomDao customdao) {
		this.customdao = customdao;
	}
	
	@Transactional
	public void changedata(Long count1, String title1, String content1, String name1, String email1) {
		System.out.println("count1 : " + count1);
		Custom custom = customdao.selectByCount(count1);
		System.out.println("수정2");
		if(!title1.isEmpty()){
			custom.changeTitle(title1);
			customdao.update(custom);
		}
	}
}
/*
private MemberDao memberDao;

@Transactional
public void changePassword(String email, String oldPwd, String newPwd) {
	Member member = memberDao.selectByEmail(email);
	if (member == null)
		throw new MemberNotFoundException();

	member.changePassword(oldPwd, newPwd);

	memberDao.update(member);
}

public void setMemberDao(MemberDao memberDao) {
	this.memberDao = memberDao;
}*/