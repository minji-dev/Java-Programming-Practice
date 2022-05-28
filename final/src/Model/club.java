package Model;
import java.io.*;
import java.util.ArrayList;

public class club {
	
	public String name;
	public String intro;
	public int reviewCnt;
	public ArrayList<review> list = new ArrayList<review>();
	
	public club(String n, String i, int c) {	
		this.name = n;
		this.intro = i;
		this.reviewCnt = c;
	}
	
	public club(String n, String i, int c, ArrayList<review> l) throws IOException {	
		this.name = n;
		this.intro = i;
		this.reviewCnt = c;
		this.list = l;
	}

}