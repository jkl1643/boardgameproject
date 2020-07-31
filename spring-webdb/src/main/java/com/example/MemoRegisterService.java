package com.example;

public class MemoRegisterService {
	private MemoDao memoDao;
	
	public MemoRegisterService(MemoDao memoDao) { //Dao에 대한정보를 여기통해서 주입
		this.memoDao = memoDao;
	}

	public Long regist2(MemoRegisterRequest req2) {
		Memo newMemo = new Memo(req2.getEmail(), req2.getYear(), req2.getMonth(), req2.getDay(),
				req2.getMemo(), req2.getSaveImagePath());	
		int loopSize = memoDao.count();
		System.out.println("1");
		System.out.println("루프사이즈 = " + loopSize);
		if(loopSize == 0) {
			System.out.println("메모가 등록되었습니다.");
			memoDao.insert(newMemo); // 날짜에 메모가 없을때
			return newMemo.getId();
		} else {
			for (int i = 1; i < 100; i++) {
				Memo memoId = memoDao.selectById(Long.valueOf(i));
				if(memoId == null) {
					continue;
				}
				else if (memoId.getEmail().equals(MemberLogin.loginEmail) && memoId.getYear().equals(req2.getYear())
						&& memoId.getMonth().equals(req2.getMonth()) && memoId.getDay().equals(req2.getDay())) {
					System.out.println("메모가 수정되었습니다.");
					memoDao.update(newMemo); // 날짜에 메모가 이미 있을때 수정
					return newMemo.getId();
				}
			}
			System.out.println("메모가 등록되었습니다.");
			memoDao.insert(newMemo); // 날짜에 메모가 없을때
			return newMemo.getId();
		}
	}
}
