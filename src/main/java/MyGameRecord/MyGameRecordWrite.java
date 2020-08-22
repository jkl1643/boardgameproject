package MyGameRecord;



public class MyGameRecordWrite {
	private MyGameRecordDao mygamerecorddao;

	public MyGameRecordWrite(MyGameRecordDao mygamerecorddao) {
		this.mygamerecorddao = mygamerecorddao;
	}

	public Long input(MyGameRecordRequest req) {
		
		MyGameRecord newRequest = new MyGameRecord(
			req.getTotal(), req.getWin(), req.getLose(), req.getDraw(), req.getGame_number(), 
			req.getMember_number());
			
	
		mygamerecorddao.insert(newRequest);
		return newRequest.getGamerecord_number();
	}
}
