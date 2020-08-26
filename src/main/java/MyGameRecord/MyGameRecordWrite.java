package MyGameRecord;


import javax.servlet.http.HttpSession;

public class MyGameRecordWrite {
	private MyGameRecordDao mygamerecorddao;

	public MyGameRecordWrite(MyGameRecordDao mygamerecorddao) {
		this.mygamerecorddao = mygamerecorddao;
	}

	public Long input(MyGameRecordRequest req, HttpSession session) {
		System.out.println("req.getWin = " + req.getWin());
		MyGameRecord newRequest = new MyGameRecord(
			req.getTotal(), req.getWin(), req.getLose(), req.getDraw(), req.getGame_number(),
				(long) req.getMember_number());

		System.out.println("1111111111 = " + req.getWin());
		//mygamerecorddao.insert2(newRequest, session,);
		System.out.println("333333333 = " + req.getWin());
		return newRequest.getGamerecord_number();
	}
}
