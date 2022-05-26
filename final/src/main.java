import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class main extends JFrame{
	static club[] clubs;
	static user[] users;
	static int clubCnt;
	
	static BufferedReader brC; static BufferedReader brU;
	BufferedWriter bwC; BufferedWriter bwU;
	
	public static void init() throws IOException{		
		// data 
		brC = new BufferedReader(new FileReader("./inC.txt"));
		brU = new BufferedReader(new FileReader("./inU.txt"));
		
		try {
			clubCnt = Integer.parseInt(brC.readLine());
			clubs = new club[clubCnt];
			
			for(int i=0; i<clubCnt; i++) {
				String[] tmp = brC.readLine().split(", ");
				int c = Integer.parseInt(tmp[2]);

				clubs[i] = new club(tmp[0], tmp[1], c);
				for(int j=0; j<c; j++) {
					String[] tmp2 = brC.readLine().split(", ");
					clubs[i].setReview(tmp2[0], tmp2[1], c);
					
				}
			}
			int userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			String read = brU.readLine();
			for(int i=0; i<userCnt && read != null; i++) {
				String[] tmp = read.split(", ");
				users[i] = new user(tmp[0], tmp[1], tmp[2]);
				read = brU.readLine();
			}
			
			//gui
			JFrame f1 = new JFrame("CLUB");
			JFrame f2 = new JFrame("CLUB INTRO");
			GridLayout g1 = new GridLayout(2,2);
			GridLayout g2 = new GridLayout(clubCnt,3);
			f1.setLayout(g1);
			f2.setLayout(g2);
			
			JLabel[] l1 = new JLabel[2];
			l1[0].setText("Title");
			l1[1].setText("login");
			
			JLabel[] l2 = new JLabel[clubCnt];
			for(int i=0;i<clubCnt;i++) {
				l2[i].setText(clubs[i].name);
				l2[i].setText(clubs[i].intro);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	public static void main(String[] args) throws IOException {
		init(); //file input and initialize
	}
}
