package controller;

import Model.*;
import chat.*;
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
	
		GridBagLayout gb3 = new GridBagLayout();
		f.setLayout(gb3);
		GridBagConstraints g_11 = new GridBagConstraints(); g_11.fill = GridBagConstraints.BOTH;
		
		GridBagConstraints gl = new GridBagConstraints(); gl.fill = GridBagConstraints.BOTH;
		JLabel l11 = new JLabel(); l11.setText("SKKU CLUB"); 
		JTextField input_id = new JTextField(8);
		JTextField input_pw = new JTextField(8);
		
		ActionListener listenLog = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				// call Login
				// String getId = input_id.getText();
				// String getPw = input_pw.getText();
			}
		};	
		JButton l12 = new JButton("login"); l12.addActionListener(listenLog);
		
		g_11.gridx = 0; g_11.gridy = 0;
		f.add(l11,g_11); 
		
		g_11.gridx = 3; g_11.gridy = 0;
		f.add(input_id,g_11);
		
		g_11.gridx = 4; g_11.gridy = 0;
		f.add(input_pw,g_11);
		
		g_11.gridx = 5; g_11.gridy = 0;
		f.add(l12,g_11);
		
		ActionListener listen = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				showDetail(e.getActionCommand());
			}
		};

		JButton[] b1 = new JButton[clubs.size()];
		GridBagConstraints g1 = new GridBagConstraints(); g1.fill = GridBagConstraints.BOTH;

		for(int i=0;i<clubs.size();i++) {
			b1[i] = new JButton(clubs.get(i).name);
			b1[i].addActionListener(listen);
			g1.gridx = i; g1.gridy = 1;
			f.add(b1[i],g1);
		}
		for(int i=clubs.size();i<5;i++) {
			JButton notb = new JButton(" ");
			g1.gridx = i; g1.gridy = 1;
			f.add(notb,g1);
		}
		// 동아리 등록 
		ActionListener AddClub = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				// 등록 칸으로 가기
			}
		};
		JButton plus = new JButton("동아리 등록"); plus.addActionListener(AddClub);
		g1.gridx = 5; g1.gridy = 1;
		f.add(plus,g1);

		for(int i=clubs.size()+1;i<12;i++) {
			JButton nonb = new JButton(" ");
			g1.gridx = i%6; g1.gridy = (int)(i/6)+1;
			f.add(nonb,g1);
		}

		f.setSize(1000,1000);
		f.setVisible(true);
	}

	public static void showDetail(String name) {
		int i;

		GridBagLayout gb = new GridBagLayout();
		JFrame f1 = new JFrame(name);
		f1.setLayout(gb);

		for(i=0;i<clubs.size() && clubs.get(i).name != name;i++) { }
		club c = clubs.get(i);

		//GridBagLayout gbin = new GridBagLayout();
		JLabel intro = new JLabel(); intro.setText(name+" 소개\n: "+c.intro);
		GridBagConstraints g = new GridBagConstraints(); g.fill = GridBagConstraints.BOTH;
		g.gridx = 0; g.gridy = 2; g.gridwidth = 6;
		f1.add(intro,g);

		for(int j=0;j<c.list.size();j++) {
			JLabel label = new JLabel(); label.setText(c.list.get(j).id);
			g.gridx = 0; g.gridy = 3+j; g.gridwidth = 2;
			f1.add(label,g);

			JLabel label1 = new JLabel(); label1.setText(c.list.get(j).text);
			g.gridx = 2; g.gridy = 3+j; g.gridwidth = 3;
			f1.add(label1,g);

			JButton b = new JButton("쪽지");
			g.gridx = 5; g.gridy = 3+j;
			f1.add(b,g);
			ActionListener listen = new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					try {
						chat.MultiChatServer.main(null);
						chat.MultiChatClient.main(null);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				//	(e.getActionCommand());
				}
			};
			b.addActionListener(listen);
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