package controller;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Controller extends JFrame implements ActionListener {

	public static ArrayList<club> clubs = new ArrayList<club>();
	public static ArrayList<user> users = new ArrayList<user>();
	public static JFrame f = new JFrame("CLUB");
	public static JLabel ClubDetail = new JLabel();
	public static void init() throws IOException {
		BufferedReader brC = new BufferedReader(new FileReader("./club.txt"));
		BufferedReader brU = new BufferedReader(new FileReader("./user.txt"));
		
		try {
			int clubCnt = Integer.parseInt(brC.readLine());
			for(int i=0; i<clubCnt; i++) {
				String[] tmp = brC.readLine().split(", ");
				int c = Integer.parseInt(tmp[2]); // 후기 수
				ArrayList<review> l = new ArrayList<review>();
				for(int j=0; j<c; j++) {
					String[] tmp2 = brC.readLine().split(", ");
					review tmpr = new review(tmp2[0], tmp2[1]);
					l.add(tmpr);
				}
				clubs.add(new club(tmp[0], tmp[1], c, l));
			}
			
			int userCnt = Integer.parseInt(brU.readLine());
			for(int i=0; i<userCnt; i++) {
				String[] tmp = brU.readLine().split(", ");
				users.add(new user(tmp[0], tmp[1], tmp[2]));
			}
			brC.close(); brU.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void print() {
		for(int i=0; i<clubs.size(); i++) {
			System.out.println(clubs.get(i).name + ": " + clubs.get(i).intro);
			for(int j=0; j<clubs.get(i).reviewCnt; j++)
				System.out.println(clubs.get(i).list.get(j).id + ": " + clubs.get(i).list.get(j).text);
		}
		for(int i=0; i<users.size(); i++)
			System.out.println(users.get(i).id + " in " + users.get(i).club);
	
		//gui
		JLabel L1 = new JLabel();
		JLabel L2 = new JLabel();
		GridLayout g1 = new GridLayout(1,2);
		GridLayout g2 = new GridLayout(clubs.size()/3+1,3);
		System.out.print(clubs.size());
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
		
		JButton[] b1 = new JButton[clubs.size()];
		for(int i=0;i<clubs.size();i++) {
			b1[i] = new JButton(clubs.get(i).name);
			b1[i].addActionListener(listen);
			L2.add(b1[i]);
		}
//		f1.add(l1[0]);
//		f1.add(l1[1]);
//		f1.setSize(700, 300);
//        f1.setVisible(true);
		//JLabel[] l2 = new JLabel[clubCnt];
		for(int i=0;i<2;i++) {
			l1[i].setText(clubs.get(i).name);
			l1[i].setText(clubs.get(i).intro);
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
		int i; 
		for(i=0;i<clubs.size() && clubs.get(i).name == name;i++) { }
		JLabel des = new JLabel();
		JLabel[] hugi = new JLabel[clubs.get(i).list.size()];
		club nClub = clubs.get(i);
		des.setText(nClub.intro);
		for(int j=0;j<nClub.list.size();j++) {
			hugi[j] = new JLabel(nClub.list.get(j).id);
			hugi[j].setText(nClub.list.get(j).text);
			ClubDetail.add(hugi[j]);
		}
		f.add(ClubDetail);
		f.setSize(700,700);
		ClubDetail.setSize(700,350);
		ClubDetail.setVisible(true);
	}
	
	public static void save() throws IOException {
		BufferedWriter bwC = new BufferedWriter(new FileWriter("./club.txt"));
		BufferedWriter bwU = new BufferedWriter(new FileWriter("./user.txt"));
		
		try {
			bwC.write(clubs.size()+"\n");
			for(int i=0; i<clubs.size(); i++) {
				bwC.write(clubs.get(i).name + ", " + clubs.get(i).intro + ", " + clubs.get(i).reviewCnt+"\n");
				for(int j=0; j<clubs.get(i).reviewCnt; j++)
					bwC.write(clubs.get(i).list.get(j).text + ", " + clubs.get(i).list.get(j).id+"\n");
			}
			bwU.write(users.size()+"\n");
			for(int i=0; i<users.size(); i++)
				bwU.write(users.get(i).id + ", " + users.get(i).pw + ", " + users.get(i).club+"\n");
			bwC.flush(); bwU.flush(); bwC.close(); bwU.close(); 
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) throws IOException {
		init();
		print();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
