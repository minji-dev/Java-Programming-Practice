package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


class EchoThread extends Thread{
	Socket socket;
	Vector<Socket> vec;
	
	public EchoThread(Socket socket, Vector<Socket> vec){
		this.socket = socket;
		this.vec = vec;
	}
	
	public void run(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String str = null;
			while(true){
				str = br.readLine();
				if(str == null){
					vec.remove(socket);
					break;
				}
				sendMsg(str);				
			}
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		} finally{
			try {
				if(br != null) br.close();
				if(socket != null) socket.close();
			} catch(IOException ie){
				System.out.println(ie.getMessage());
			}
		}
	}


	public void sendMsg(String str){
		try {
			for(Socket socket:vec) {
				if(socket != this.socket){
					PrintWriter pw = 
						new PrintWriter(socket.getOutputStream(), true);
					pw.println(str);
					pw.flush();
				}
			}
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
}


public class MultiChatServer {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		Vector<Socket> vec = new Vector<Socket>();
		try {
			server= new ServerSocket(3000);
			while(true){
				System.out.println("wait..");
				socket = server.accept();
				vec.add(socket);
				new EchoThread(socket, vec).start();
			}
		} catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
	
}
