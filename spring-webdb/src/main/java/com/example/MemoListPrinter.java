package com.example;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MemoListPrinter {
	private MemoDao memoDao;
	
	public MemoListPrinter(MemoDao memoDao) {
		this.memoDao = memoDao;
	}	
	public void printMemo(String year, String month, String day) {		
		for (int j = 1; j < 100; j++) {
			Memo memo = memoDao.selectById(Long.valueOf(j));
			if(memo == null)
				continue;
			if (MemberLogin.loginEmail.equals(memo.getEmail()) && year.equals(memo.getYear())&& month.equals(memo.getMonth()) && day.equals(memo.getDay())) {
				System.out.printf("메모 : %s년 %s월 %s일 메모 = %s \n", memo.getYear(), memo.getMonth(), memo.getDay(), memo.getMemo());
				
				try {
					File sourceimage = new File(memo.getSaveImagePath());
					Image image = ImageIO.read(sourceimage);
					JFrame frame = new JFrame();
					frame.setSize(300, 300);
					JLabel label = new JLabel(new ImageIcon(image));
					frame.add(label);
					frame.setVisible(true);
				} catch (Exception e) {
					
				}
				
			}
		}		
		
		System.out.println();
	}
}
