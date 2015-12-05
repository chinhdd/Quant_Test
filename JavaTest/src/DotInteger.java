
public class DotInteger {

	private int value;
	private int numDot;
	
	public DotInteger() {
		
	}
	
	public DotInteger(int numDot) {
		this.numDot = numDot;
	}
	
	public DotInteger(int numDot, int value) {
		this.numDot = numDot;
		this.value = value;
	}
	
	public DotInteger(int numDot, double rawValue) {
		init(numDot, rawValue);
	}
	
	public DotInteger(String rawStr) {
		int n = rawStr.indexOf(".");
		while (rawStr.charAt(rawStr.length() - 1) == ' ') {
			rawStr = rawStr.substring(0, rawStr.length() - 1);
		}
		init(rawStr.length() - n - 1, Double.parseDouble(rawStr));
	}
	
	private void init(int numDot, double rawValue) {
		this.numDot = numDot;
		int ten = 1;
		while (numDot > 0) {
			ten *= 10;
			numDot--;
		}
		rawValue *= ten;
		this.value = (int) rawValue;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getNumDot() {
		return numDot;
	}
}
