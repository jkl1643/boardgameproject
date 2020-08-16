package com.example;

import MyGameRecord.MyGameRecord;
import MyGameRecord.MyGameRecordDao;

public class MemberRegisterService {
    private MemberDao memberDao; //DB에 업데이트 시키기위해 Dao가 필요
    private MyGameRecordDao mygamerecordDao;

    public MemberRegisterService(MemberDao memberDao, MyGameRecordDao mygamerecordDao) { //Dao에 대한정보를 여기통해서 주입
        this.memberDao = memberDao;
        this.mygamerecordDao = mygamerecordDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail()); //이메일 중복확인용
        if (member != null) { //같은이메일이있다
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        System.out.println("3");
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getNickname(), req.getRegisterDate()); //맴버객체를 만듬 new Date()
        MyGameRecord newrecord = new MyGameRecord(req.getNickname(), 0, 0, 0, 0);
        System.out.println("4");
        memberDao.insert(newMember); //Dao에 삽입
        System.out.println("5");
        mygamerecordDao.insert(newrecord);
        return newMember.getId();
    }
}