import java.io.*;

public class user {
	
	public String id;
	public String pw;
	public String club;
	
	user() {
		this.id = "";
		this.pw = "";
	}
	
	public void login(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
}
