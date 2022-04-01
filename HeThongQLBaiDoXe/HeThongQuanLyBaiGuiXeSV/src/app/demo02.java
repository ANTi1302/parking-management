package app;
public class demo02 {
	public static void main(String[] args) {
	    String example = new String("InterviewBit");
	    example = null;
	    System.gc(); // Garbage collector called
	}
	public void finalize() {
	    // Finalize called
	}
}