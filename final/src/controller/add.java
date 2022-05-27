package controller;

import java.util.ArrayList;
import Model.*;

public class add {
	public static user nowUser = new user();
	
	public static boolean login(String id, String p) {
		nowUser.reset(id, p);
		for(int i=0; i<Controller.users.size(); i++) {
			if(Controller.users.get(i).id.equals(id)){
				if(!Controller.users.get(i).pw.equals(p)) return false;
				else return true;
			}
		}
		Controller.users.add(nowUser);
		return true;
	}
	
	public static void addClub(String n,String i) {
		club c = new club(n, i, 0);
		Controller.clubs.add(c);
	}
	
	public static void resetClub(String c) {
		if (nowUser.club.equals("")) { // 현재 소속 동아리가 없음
			for(int i=0; i<Controller.users.size(); i++) {
				if(Controller.users.get(i).id.equals(nowUser.id)) {
					Controller.users.get(i).setClub(c);
				}
			}
		}
	}
}
