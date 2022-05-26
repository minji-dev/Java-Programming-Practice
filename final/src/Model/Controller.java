package Model;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class Controller extends JFrame implements ActionListener {

	public static club clubs[]; public static int clubCnt;
	public static user users[];	public static int userCnt;
	public static JLabel ClubDetail = new JLabel();
	public static void init() throws IOException {
		BufferedReader brC = new BufferedReader(new FileReader("./club.txt"));
		BufferedReader brU = new BufferedReader(new FileReader("./user.txt"));
		
		try {
			clubCnt = Integer.parseInt(brC.readLine());
			clubs = new club[clubCnt];
			for(int i=0; i<clubCnt; i++) {
				String[] tmp = brC.readLine().split(", ");
				int c = Integer.parseInt(tmp[2]); // 후기 수
				clubs[i] = new club(tmp[0], tmp[1], c);
				for(int j=0; j<c; j++) {
					String[] tmp2 = brC.readLine().split(", ");
					clubs[i].setReview(tmp2[0], tmp2[1], c);
				}
			}
			
			userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			for(int i=0; i<userCnt; i++) {
				String[] tmp = brU.readLine().split(", ");
				users[i] = new user(tmp[0], tmp[1], tmp[2]);
			}
			brC.close(); brU.close();

		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void print() {
		for(int i=0; i<clubCnt; i++) {
			System.out.println(clubs[i].name + ": " + clubs[i].intro);
			for(int j=0; j<clubs[i].reviewCnt; j++)
				System.out.println(clubs[i].list[j].id + ": " + clubs[i].list[j].text);
		}
		for(int i=0; i<userCnt; i++)
			System.out.println(users[i].id + " in " + users[i].club);
	
		//gui
		JFrame f = new JFrame("CLUB");
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		GridLayout g1 = new GridLayout(1,2);
		GridLayout g2 = new GridLayout(clubCnt/3+1,3);
		System.out.print(clubCnt);
		L1.setLayout(g1);
		L2.setLayout(g2);

		JLabel[] l1 = new JLabel[2]; 
		l1[0] = new JLabel(); l1[1] = new JLabel();
		l1[0].setText("Title");
		l1[1].setText("login");
		
		L1.add(l1[0]); L1.add(l1[1]);
		
		ActionListener listen = new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				showDetail(e.getActionCommand());
			}
		};
		
		JButton[] b1 = new JButton[clubCnt];
		for(int i=0;i<clubCnt;i++) {
			b1[i] = new JButton(clubs[i].name);
			b1[i].addActionListener(listen);
			L2.add(b1[i]);
		}
		L1.setSize(700, 50);
		L1.setVisible(true);
		L2.setSize(700, 300);
		L2.setVisible(true);
		
		f.add(L1); 
		f.add(L2);
		f.setVisible(true);
		f.setSize(700,350);
	}
	public static void showDetail(String name) {
		JLabel des = new JLabel();
		JLabel 
		for(int i=0;i<clubsCnt;i++) { 
			if
		} // 동아리 찾기
		des.setText = 입력
		ClubDetail.setSize(300,300);
		ClubDetail.setVisible(true);
	}
	public static void save() throws IOException {
		BufferedWriter bwC = new BufferedWriter(new FileWriter("./club.txt"));
		BufferedWriter bwU = new BufferedWriter(new FileWriter("./user.txt"));
		
		try {
			bwC.write(clubCnt+"\n");
			for(int i=0; i<clubCnt; i++) {
				bwC.write(clubs[i].name + ", " + clubs[i].intro + ", " + clubs[i].reviewCnt+"\n");
				for(int j=0; j<clubs[i].reviewCnt; j++)
					bwC.write(clubs[i].list[j].text + ", " + clubs[i].list[j].id+"\n");
			}
			bwU.write(userCnt+"\n");
			for(int i=0; i<userCnt; i++)
				bwU.write(users[i].id + ", " + users[i].pw + ", " + users[i].club+"\n");
			bwC.flush(); bwU.flush(); bwC.close(); bwU.close(); 
		} catch (IOException e) { e.printStackTrace(); }
	}
	public static void main(String[] args) throws IOException{
		init();
		print();
	}
}
