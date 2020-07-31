package com.example;

import java.util.Calendar;

public class MemoCalendar {
	private MemoDao memoDao;
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public void memberDate(String year, String month){
		int year2 = Integer.parseInt(year);
		int month2 = Integer.parseInt(month);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year2);
		cal.set(Calendar.MONTH, month2);
		System.out.println("           " + year2 + "년 " + month2 + "월 ");
		System.out.println("  일     월     화    수     목    금    토");
		cal.set(year2,  month2 - 1, 1);

		int end = cal.getActualMaximum(Calendar.DATE);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i < end; i++) {
			if (i == 1) {
				for (int j = 1; j < dayOfWeek; j++) {
					System.out.printf("    ");
				}
			}
			if (i < 10) {
				System.out.printf(" ");
			}
			int j = 1;
			int color = 0;
			for (j = 1; j < 5; j++) {
				Memo memo = memoDao.selectById(Long.valueOf(j));
				if(memo == null)
					continue;
				if (MemberLogin.loginEmail.equals(memo.getEmail()) && year2 == Integer.parseInt(memo.getYear()) && month2 == Integer.parseInt(memo.getMonth())
						&& i == Integer.parseInt(memo.getDay())) {
					System.out.printf(ANSI_GREEN_BACKGROUND + " " + i + " " + ANSI_RESET); // 메모있는 날 색깔칠해지게
					color = 1;
				}
			}
			if(color == 0) {
				System.out.printf(" " + i + " "); //메모 없는 일
			}
			if (dayOfWeek % 7 == 0) {
				System.out.println();
			}
			dayOfWeek++;
		}
		System.out.println();
	}

	public long longValue() {
		return 0;
	}
	
	public void setMemoDao(MemoDao memoDao) {
		this.memoDao = memoDao;
	}
}
