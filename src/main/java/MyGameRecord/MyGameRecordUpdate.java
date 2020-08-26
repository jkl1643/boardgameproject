package MyGameRecord;


public class MyGameRecordUpdate {
	private MyGameRecordDao mygamerecorddao;

	public void setMyGameRecordUpdate(MyGameRecordDao mygamerecorddao) {
		this.mygamerecorddao = mygamerecorddao;
	}
	

	public void changedata(Long mem_count, int total, int win, int lose, int draw) {
		System.out.println("count1 : " +  mem_count);
		MyGameRecord record = mygamerecorddao.selectByMEMNUM(mem_count);

		record.changeTotal(total);
		record.changeWin(win);
		record.changeLose(lose);
		record.changeDraw(draw);
		
		mygamerecorddao.update(record);
		}
	}
