import java.io.*;
import java.util.Scanner;

public class main {
	static club clubs[];
	static user users[];
	
	static BufferedReader brC; static BufferedReader brU;
	BufferedWriter bwC; BufferedWriter bwU;
	
	public static void init() throws IOException {
		brC = new BufferedReader(new FileReader("./inC.txt"));
		brU = new BufferedReader(new FileReader("./inU.txt"));
		
		try {
			int clubCnt = Integer.parseInt(brC.readLine());
			clubs = new club[clubCnt];
			for(int i=0; i<clubCnt; i++) {

			}
			
			int userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			for(int i=0; i<userCnt; i++) {
				String[] tmp = brU.readLine().split(", ");
				users[i] = new user(tmp[0], tmp[1], tmp[2]);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	public static void main(String[] args) throws IOException {
		init(); //file input and initialize
	}
}
