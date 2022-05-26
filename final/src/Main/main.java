package Main;
import java.io.*;
import java.util.Scanner;

public class main {
	static club clubs[];	static int clubCnt;
	static user users[];	static int userCnt;
	
	static BufferedReader brC; static BufferedReader brU;
	BufferedWriter bwC; BufferedWriter bwU;
	
	public static void init() throws IOException {
		brC = new BufferedReader(new FileReader("./inC.txt"));
		brU = new BufferedReader(new FileReader("./inU.txt"));
		
		try {
			clubCnt = Integer.parseInt(brC.readLine());
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
			
			userCnt = Integer.parseInt(brU.readLine());
			users = new user[userCnt];
			for(int i=0; i<userCnt; i++) {
				String[] tmp = brU.readLine().split(", ");
				users[i] = new user(tmp[0], tmp[1], tmp[2]);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void print() {
		for(int i=0; i<clubCnt; i++) {
			System.out.println(clubs[i].name + ": " + clubs[i].intro);
			for(int j=0; j<clubs[i].reviewCnt; j++)
				System.out.println(clubs[i].list[j].id + ": " + clubs[i].list[j].text);
		}
		for(int i=0; i<userCnt; i++)
			System.out.println(users[i].id + " in " + users[i].club);
	}
	
	public static void main(String[] args) throws IOException {
		init(); //file input and initialize
		print();
	}

}
