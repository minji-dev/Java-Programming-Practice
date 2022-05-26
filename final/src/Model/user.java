package Model;
import java.io.*;

public class user {
	
	public String id;
	public String pw;
	public String club;
	
	public user(String i, String p, String c) {
		this.id = i;
		this.pw = p;
		this.club = c;
	}
	
	public void login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
}
