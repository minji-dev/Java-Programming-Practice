package chat;

import controller.*;
import Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


class WriteThread {
	Socket socket;
	ClientFrame cf;
	String str;
	user me;
	user other;
	
	public WriteThread(ClientFrame cf) {
		this.cf = cf;
		this.socket= cf.socket;
		
		this.me = Controller.users[0];
		
		for(int i=0; i<Controller.userCnt; i++) {
			if(selectId.getId().equals(Controller.users[i].id)) {
				this.other = Controller.users[i];
			}
//			else if(본인Id().equals(Controller.users[i].id)) {
//				this.me = Controller.users[i];
//			}
		}
	}
	
	public void sendMsg() {
		BufferedReader br=
				new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(socket.getOutputStream(),true);
			if(cf.isFirst == true) {
				InetAddress iaddr = socket.getLocalAddress();				
				String ip = iaddr.getHostAddress();
				
				System.out.println("ip:"+ip+"id:"+me.id);
				str = "["+me.id+"] login"+ip+")"; 
			} else{
				str= "["+me.id+"] "+cf.txtF.getText();
			}
			pw.println(str);
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		} finally{
			try {
				if(br!=null) br.close();
				//if(pw!=null) pw.close();
				//if(socket!=null) socket.close();
			} catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}
}

class ReadThread extends Thread{
	Socket socket;
	ClientFrame cf;
	
	public ReadThread(Socket socket, ClientFrame cf) {
		this.cf = cf;
		this.socket=socket;
	}
	
	public void run() {
		BufferedReader br = null;
		try {
			br=new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while(true){
				String str = br.readLine();
				if(str == null){
					System.out.println("connect end...");
					break;
				}
				//System.out.println("[server] " + str);
				cf.txtA.append(str+"\n");
			}
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		} finally{
			try{
				if(br!=null) br.close();
				if(socket!=null) socket.close();
			} catch(IOException ie){}
		}
	}
}

public class MultiChatClient {
	
	public static void main(String[] args) throws IOException {
		
		Controller.init();
		
		Socket socket = null;
		ClientFrame cf;
		try {
			socket = new Socket("127.0.0.1",3000);
			System.out.println("Connect success!");
			cf = new ClientFrame(socket);
			new ReadThread(socket, cf).start();
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}