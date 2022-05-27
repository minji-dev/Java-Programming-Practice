package Model;

public class user {
	
	public String id = "";
	public String pw = "";
	public String club = ".";
	
	public user(String i, String p) {
		this.id = i;
		this.pw = p;
	}
	
	public user(String i, String p, String c) {
		this.id = i;
		this.pw = p;
		this.club = c;
	}
	
	public void setClub(String c) {
		this.club = c;
	}
	
}