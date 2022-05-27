package controller;

import java.util.ArrayList;
import Model.*;

public class add {
	public static user nowUser;
	
	public static boolean login(String id, String p) {
		nowUser = new user(id, p);
		for(int i=0; i<Controller.users.size(); i++) {
			if(Controller.users.get(i).id.equals(id)){
				if(!Controller.users.get(i).pw.equals(p)) return false;
				else return true;
			}
		}
		Controller.users.add(nowUser);
		return true;
	}
	
	public static void addClub() {
		String n = tfClubName.getText();
		String i = tfClubIntro.getText();
		club c = new club(n, i, 0);
		Controller.clubs.add(c);
	}
	
	public static boolean addReview() {
		String t = tfReview.getText();
		String c = b1[].getText(); // 버튼 클릭할 때 동아리 어디 했는지
		review r = new review(t, nowUser.id);
		for(int i=0; i<Controller.clubs.size(); i++) {
			if(Controller.clubs.get(i).name.equals(c)) {
				Controller.clubs.get(i).list.add(r);
				break;
			} else { //기존에 없는 동아리임. 동아리 등록이나 하라는 메시지 
				return false;
			}
		}
		if (nowUser.club.equals("")) { // 현재 소속 동아리가 없음
			for(int i=0; i<Controller.users.size(); i++) {
				if(Controller.users.get(i).id.equals(nowUser.id)) {
					Controller.users.get(i).setClub(c);
				}
			}
		}
		return true;
	}
}
