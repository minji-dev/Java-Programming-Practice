import java.io.*;
import java.util.Scanner;

public class main {
	static club clubs[];
	static user users[];
	
	static BufferedReader brC; static BufferedReader brU;
	BufferedWriter bwC; BufferedWriter bwU;
	
	public static void main(String[] args) throws IOException {
		brC = new BufferedReader(new FileReader("./inC.txt"));
		brU = new BufferedReader(new FileReader("./inU.txt"));
		
		try {
			int clubCnt = Integer.parseInt(brC.readLine());
			clubs = new club[clubCnt];
			for(int i=0; i<clubCnt; i++) {
				clubs[i] = new club();
			}
			
			int userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			for(int i=0; i<userCnt; i++) {
				users[i] = new user();
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		
	}

}
