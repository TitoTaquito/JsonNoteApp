package main.java.hello;

public class Note {
	private int id=0;
	private final String body;
	
	public Note(int i,String b) {
		id= i;
		body= b;
	}
	
	public Note(String b) {
		body=b;
	}
	
	public void setId(int i){
		id=i;
	}
	
	public int getId() {
		return id;
	}
	
	public String getBody() {
		return body;
	}
}
