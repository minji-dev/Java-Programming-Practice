package chat;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ClientFrame extends JFrame implements ActionListener{ // 채팅창
	JTextArea txtA = new JTextArea();
	JTextField txtF = new JTextField(15);
	JButton btnTransfer = new JButton("Send");
	JButton btnExit = new JButton("Close");
	boolean isFirst = true;
	JPanel p1 = new JPanel();
	Socket socket;
	WriteThread wt;
		
	public ClientFrame(Socket socket) {
		super("Chat");
		this.socket = socket;
		wt = new WriteThread(this);
		
		add("Center", txtA);
		
		p1.add(txtF);
		p1.add(btnTransfer);
		p1.add(btnExit);
		add("South", p1);
		
		btnTransfer.addActionListener(this);
		btnExit.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 300, 350, 300);
		setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e){
		String myId = wt.me.id;
		if(e.getSource() == btnTransfer){ // 전송버튼 누름
			if(txtF.getText().equals("")){ //메세지 없이 전송만 누름
				return;
			}			
			txtA.append("["+ myId +"] "+ txtF.getText()+"\n");
			wt.sendMsg();
			txtF.setText("");
		} else {
			this.dispose();
		}
	}
}
