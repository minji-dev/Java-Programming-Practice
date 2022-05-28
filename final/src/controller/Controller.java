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

	public static void init() throws IOException {
		BufferedReader brC = new BufferedReader(new FileReader("./club.txt"));
		BufferedReader brU = new BufferedReader(new FileReader("./user.txt"));
		chat.Server.main(null);
		
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

	public static void print() throws IOException{
	
		GridBagLayout gb3 = new GridBagLayout();
		f.setLayout(gb3);
		GridBagConstraints g_11 = new GridBagConstraints(); g_11.fill = GridBagConstraints.BOTH;
		
		GridBagConstraints gl = new GridBagConstraints(); gl.fill = GridBagConstraints.BOTH;
		JLabel l11 = new JLabel("SKKU CLUB"); l11.setFont(l11.getFont().deriveFont(20.0f));//l11.setText(); 
		JTextField input_id = new JTextField(8);
		JTextField input_pw = new JTextField(8);
		
		ActionListener listenLog = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				String getId = input_id.getText();
				String getPw = input_pw.getText();
				boolean loginOK = add.login(getId, getPw);
				if(loginOK == false) { //팝업창으로 PW 틀렸다고 알려주기
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 성공!");
				}
			}
		};	
		ActionListener Chatting = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				try {
					chat.Client.main(null);
				} catch (IOException e1) { e1.printStackTrace(); }
			}
		};
		JButton showChat = new JButton("쪽지함"); showChat.addActionListener(Chatting);
		JButton l12 = new JButton("login"); l12.addActionListener(listenLog);
		
		g_11.gridx = 0; g_11.gridy = 0; g_11.ipady = 2; g_11.insets = new Insets(0,0,4,0);
		f.add(l11,g_11); 
		
		g_11.gridx = 3; g_11.gridy = 1; g_11.insets = new Insets(0,0,4,0);
		f.add(input_id,g_11);
		
		g_11.gridx = 4; g_11.gridy = 1;
		f.add(input_pw,g_11);
		
		g_11.gridx = 5; g_11.gridy = 1;
		f.add(l12,g_11);
		
		g_11.gridx = 3; g_11.gridy = 0; g_11.gridwidth = 3; g_11.insets = new Insets(0,0,0,0);
		f.add(showChat,g_11);
		
//		g_11.insets = new Insets(0,0,0,0);
		
		ActionListener listen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					showDetail(e.getActionCommand());
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}
		};

		JButton[] b1 = new JButton[clubs.size()];
		GridBagConstraints g1 = new GridBagConstraints(); 
		g1.fill = GridBagConstraints.BOTH; //g.gridheight = 2;

		for(int i=0;i<clubs.size();i++) {
			b1[i] = new JButton(clubs.get(i).name);
			b1[i].addActionListener(listen);
			g1.gridx = i%6; g1.gridy = (int)(i/6)+3;
			f.add(b1[i],g1);
		}
		for(int i=clubs.size();i<6;i++) {
			JButton notb = new JButton(" ");
			g1.gridx = i%6; g1.gridy = (int)(i/6)+3;
			f.add(notb,g1);
		}
		// 동아리 등록 
		ActionListener AddClub = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JFrame addj = new JFrame();
				GridBagLayout gb = new GridBagLayout();
				addj.setLayout(gb);
				GridBagConstraints g = new GridBagConstraints(); g.fill = GridBagConstraints.BOTH;
				JButton addname = new JButton(); addname.setText("동아리 이름: ");
				JButton addintro = new JButton(); addintro.setText("소개: ");
				JTextField newname = new JTextField(10);
				JTextField newintro = new JTextField(10);
				JButton saveClub = new JButton("save");

				saveClub.addActionListener(new ActionListener() {
					@Override
				    public void actionPerformed(ActionEvent e) {
						try {
							String clubn = newname.getText();
							String clubin = newintro.getText();
							add.addClub(clubn, clubin);
							save();
							addj.setVisible(false);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});

				g.gridx = 0; g.gridy = 2; g.ipady = 2; addj.add(addname,g);
				g.gridx = 1; g.gridy = 2; g.ipady = 2; addj.add(newname,g);
				g.gridx = 0; g.gridy = 3; g.ipady = 2; addj.add(addintro,g);
				g.gridx = 1; g.gridy = 3; g.ipady = 2; addj.add(newintro,g);
				g.gridx = 1; g.gridy = 4; g.ipady = 3; addj.add(saveClub,g);
				addj.setSize(500,300); addj.setVisible(true);
			}
		};

		for(int i=clubs.size();i<11;i++) {
			JButton nonb = new JButton(" ");
			g1.gridx = i%6; g1.gridy = (int)(i/6)+3;
			f.add(nonb,g1);
		}
		JButton plus = new JButton("동아리 등록"); plus.addActionListener(AddClub);	
		g1.gridx = 5; g1.gridy = 4;
	
		f.add(plus,g1);
		f.setSize(600,600);
		f.setVisible(true);
	}

	public static void showDetail(String name) throws IOException{
		int i;

		GridBagLayout gb = new GridBagLayout();
		JFrame f1 = new JFrame(name);
		f1.setLayout(gb);

		for(i=0;i<clubs.size() && clubs.get(i).name != name;i++) { }
		club c = clubs.get(i);

		JButton cName = new JButton(name);
		JButton intro = new JButton(c.intro);
		JButton addRe = new JButton("후기등록"); 

		ActionListener addReview = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				try {
					addClubReview(name);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		addRe.addActionListener(addReview);
		GridBagConstraints g = new GridBagConstraints(); g.fill = GridBagConstraints.BOTH;
		g.gridx = 0; g.gridy = 2;
		f1.add(cName,g);
		g.gridx = 3; g.gridy = 2;
		f1.add(intro,g);
		g.gridx = 7; g.gridy = 2;
		f1.add(addRe,g);
		
		for(int j=0;j<c.list.size();j++) {
			JLabel label = new JLabel("",SwingConstants.CENTER); label.setText(c.list.get(j).id);
			g.gridx = 0; g.gridy = 3+j;
			f1.add(label,g);

			String reviewWriter = c.list.get(j).id;
			JLabel label1 = new JLabel(); label1.setText(c.list.get(j).text);
			g.gridx = 3; g.gridy = 3+j;
			f1.add(label1,g);

			JButton b = new JButton("쪽지");
			g.gridx = 7; g.gridy = 3+j;
			f1.add(b,g);
			ActionListener listen = new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					try {
						if(add.nowUser.id == "") { JOptionPane.showMessageDialog(null, "로그인을 해주세요"); return; }
						add.selectUser = new user(reviewWriter, "");
						chat.Client.main(null);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			};
			b.addActionListener(listen);
		}
		f1.setSize(600,300);
		f1.setVisible(true);
	}

	public static void addClubReview(String clubName) throws IOException{
		int i=0;
		if(add.nowUser.id == ""){ JOptionPane.showMessageDialog(null, "로그인을 해주세요"); return; }
		for(i=0;i<clubs.size() && clubs.get(i).name != clubName;i++) {}
		club c = clubs.get(i);
		JFrame newReview = new JFrame();
		JTextField reviewText = new JTextField(10);
		JLabel r = new JLabel(); r.setText(clubName+"의 후기를 작성해주세요");
		JButton savebu = new JButton("등록");
		savebu.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				String input_text = reviewText.getText();
				c.list.add(new review(input_text, add.nowUser.id));
				try {
					save();
				} catch (IOException e1) { e1.printStackTrace(); }
				newReview.setVisible(false);
			}
		});
		newReview.setLayout(new GridLayout(3,1));
		newReview.add(r); newReview.add(reviewText); newReview.add(savebu);
		newReview.setSize(500,300);
		newReview.setVisible(true);
	}
	
	public static void save() throws IOException {
		BufferedWriter bwC = new BufferedWriter(new FileWriter("./club.txt"));
		BufferedWriter bwU = new BufferedWriter(new FileWriter("./user.txt"));

		try {
			bwC.write(clubs.size()+"\n");
			for(int i=0; i<clubs.size(); i++) {
				bwC.write(clubs.get(i).name + ", " + clubs.get(i).intro + ", " + clubs.get(i).list.size()+"\n");
				for(int j=0; j<clubs.get(i).list.size(); j++)
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
		save();
	}
}