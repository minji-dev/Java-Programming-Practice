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
				String[] tmp = brC.readLine().split(", ");
				int c = Integer.parseInt(tmp[2]);
				clubs[i] = new club(tmp[0], tmp[1], c);
				for(int j=0; j<c; j++) {
					String[] tmp2 = brC.readLine().split(", ");
					clubs[i].setReview(tmp2[0], tmp2[1], c);
				}
			}
			
			int userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			for(int i=0; i<userCnt; i++) {
				String[] tmp = brU.readLine().split(", ");
				users[i] = new user(tmp[0], tmp[1], tmp[2]);
			}
		} catch (IOException e) { e.printStackTrace(); }
		
		
	}

}
