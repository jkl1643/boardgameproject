package com.example;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReadFile {	
	public void readTxt(String inputPath) {
		try {
			File file = new File(inputPath + "/Memo.txt");
			FileReader fileReader = new FileReader(file);
			int singleCh = 0;
            while((singleCh = fileReader.read()) != -1){
                System.out.print((char)singleCh);
            }
			fileReader.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			System.out.println(e);
		}		
	}

	public void readImage(String inputPath) {
		try {
			File sourceimage = new File(inputPath);
			Image image = ImageIO.read(sourceimage);
			JFrame frame = new JFrame();
			frame.setSize(300, 300);
			JLabel label = new JLabel(new ImageIcon(image));
			frame.add(label);
			frame.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void readVidio(String inputPath) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
