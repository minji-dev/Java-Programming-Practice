package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Model.club;
import Model.user;

public class Controller {

	public static club clubs[]; public static int clubCnt;
	public static user users[];	public static int userCnt;

	public static void init() throws IOException {
		BufferedReader brC = new BufferedReader(new FileReader("./club.txt"));
		BufferedReader brU = new BufferedReader(new FileReader("./user.txt"));
		
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
			brC.close(); brU.close();
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
	
	public static void save() throws IOException {
		BufferedWriter bwC = new BufferedWriter(new FileWriter("./club.txt"));
		BufferedWriter bwU = new BufferedWriter(new FileWriter("./user.txt"));
		
		try {
			bwC.write(clubCnt+"\n");
			for(int i=0; i<clubCnt; i++) {
				bwC.write(clubs[i].name + ", " + clubs[i].intro + ", " + clubs[i].reviewCnt+"\n");
				for(int j=0; j<clubs[i].reviewCnt; j++)
					bwC.write(clubs[i].list[j].text + ", " + clubs[i].list[j].id+"\n");
			}
			bwU.write(userCnt+"\n");
			for(int i=0; i<userCnt; i++)
				bwU.write(users[i].id + ", " + users[i].pw + ", " + users[i].club+"\n");
			bwC.flush(); bwU.flush(); bwC.close(); bwU.close(); 
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}
