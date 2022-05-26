package controller;

import java.util.ArrayList;
import Model.*;

public class add {
	public user u;
	
	public void login() {
		String i = tfLoginId.getText();
		String p = tfLoginPw.getText();
		u = new user(i, p);
		Controller.users.add(u);
	}
	
	public void addClub() {
		String n = tfClubName.getText();
		String i = tfClubIntro.getText();
		club c = new club(n, i, 0);
		Controller.clubs.add(c);
	}
	
	public void addReview() {
		String t = tfReview.getText();
		String c = b1[].getText(); // 버튼 클릭할 때 동아리 어디 했는지
		review r = new review(t, u.id);
		for(int i=0; i<Controller.clubs.size(); i++) {
			if(Controller.clubs.get(i).name.equals(c)) {
				Controller.clubs.get(i).list.add(r);
				break;
			} else {
				// 기존에 없을 경우 동아리 등록이나 하라는 메시지 
			}
		}		
		if (u.club.equals("")) { // 현재 소속 동아리가 없음
			for(int i=0; i<Controller.users.size(); i++) {
				if(Controller.users.get(i).id.equals(u.id)) {
					Controller.users.get(i).setClub(c);
				}
			}
		}
	}
	
	
	
}
