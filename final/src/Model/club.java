package Model;
import java.io.*;

public class club {
	
	public int reviewCnt;
	public String name;
	public String intro;
	public review list[];
	
	BufferedReader br;	BufferedWriter bw;
	
	public club(String n, String i, int c) throws IOException {	
		this.reviewCnt = c;
		this.name = n;
		this.intro = i;
		list = new review[c];
	}
	
	public void setReview(String t, String id, int c) {
		for(int i=0; i<c; i++) {
			list[i] = new review(t, id);
		}
	}
}
