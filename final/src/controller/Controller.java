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


public class Controller extends JFrame {

	public static ArrayList<club> clubs = new ArrayList<club>();
	public static ArrayList<user> users = new ArrayList<user>();
	public static JFrame f = new JFrame("CLUB");
	public static JLabel[] ClubDetail;
	
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
//		for(int i=0; i<clubs.size(); i++) {
//			System.out.println(clubs.get(i).name + ": " + clubs.get(i).intro);
//			for(int j=0; j<clubs.get(i).reviewCnt; j++)
//				System.out.println(clubs.get(i).list.get(j).id + ": " + clubs.get(i).list.get(j).text);
//		}
//		for(int i=0; i<users.size(); i++)
//			System.out.println(users.get(i).id + " in " + users.get(i).club);
	
		//gui
		
		GridBagLayout gb3 = new GridBagLayout();
		f.setLayout(gb3);
//		JLabel L1 = new JLabel();
//		JLabel L2 = new JLabel();
//		
//		GridBagLayout gll = new GridBagLayout();
		
		GridBagConstraints g_11 = new GridBagConstraints(); g_11.fill = GridBagConstraints.BOTH;
//		
//		L1.setLayout(gl);
//		L2.setLayout(gll);
		
		GridBagConstraints gl = new GridBagConstraints(); gl.fill = GridBagConstraints.BOTH;
		JLabel l11 = new JLabel(); l11.setText("Title"); 
		JLabel l12 = new JLabel("login"); l12.setText("login");
		g_11.gridx = 0; g_11.gridy = 0; g_11.gridwidth = 3;
		f.add(l11,g_11); 
		g_11.gridx = 4; g_11.gridy = 0; g_11.gridwidth = 3;
		f.add(l12,g_11);
		//L1.setSize(1000,300);
		//gl.gridx = 0; gl.gridy = 0; gl.gridwidth = 6;
		//f.add(L1,g1);
		
		ActionListener listen = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				showDetail(e.getActionCommand());
			}
		};

		JButton[] b1 = new JButton[clubs.size()];
		for(int i=0;i<clubs.size();i++) {
			GridBagConstraints g1 = new GridBagConstraints(); g1.fill = GridBagConstraints.BOTH;
			b1[i] = new JButton(clubs.get(i).name);
			b1[i].addActionListener(listen);
			g1.gridx = 1; g1.gridy = i+1;
			f.add(b1[i],g1);
		}

		f.setSize(1000,1000);
		f.setVisible(true);
	}
	
	public static void showDetail(String name) {
		int i;

		GridBagLayout gb = new GridBagLayout();
		JFrame f1 = new JFrame(name);
		f1.setLayout(gb);
		//설명 
		
		for(i=0;i<clubs.size() && clubs.get(i).name != name;i++) { }
		GridBagConstraints g = new GridBagConstraints(); g.fill = GridBagConstraints.BOTH;
		club c = clubs.get(i);
		
		GridBagLayout gbin = new GridBagLayout();
		JLabel intro = new JLabel(c.intro);
		g.gridx = 0; g.gridy = 2; g.gridwidth = 6;
		f1.add(intro,g);
		
		for(int j=0;j<c.list.size();j++) {
			JLabel label = new JLabel(c.list.get(j).id);
			g.gridx = 0; g.gridy = 3+j;
			f1.add(label,g);
			
			JLabel label1 = new JLabel(c.list.get(j).text);
			g.gridx = 1; g.gridy = 3+j; g.gridwidth = 4;
			f1.add(label1,g);
			
			JButton b = new JButton("쪽지");
			g.gridx = 5; g.gridy = 3+j; g.gridwidth = 4;
			f1.add(b,g);
		}
		f1.setSize(1000,300);
		f1.setVisible(true);
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
}
