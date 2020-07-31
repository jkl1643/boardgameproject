package com.example;

import java.util.Calendar;

public class PrintCalendar {
	public void printDate(String year, String month) {
		int year2 = Integer.parseInt(year);
		int month2 = Integer.parseInt(month);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year2);
		cal.set(Calendar.MONTH, month2);
		System.out.println("           " + year2 + "년 " + month2 + "월 ");
		System.out.println("  일     월     화    수     목    금    토");
		cal.set(year2, month2 - 1, 1);
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
			System.out.printf(" " + i + " "); 
			if (dayOfWeek % 7 == 0) {
				System.out.println();
			}
			dayOfWeek++;
		}
		System.out.println();
	}
}
