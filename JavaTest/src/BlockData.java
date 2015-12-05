import java.util.Date;


public class BlockData {
	
	public static final String TEXT_OPEN = "OPEN";
	public static final String TEXT_CLOSE = "CLOSE";
	public static final String TEXT_LOW = "LOW";
	public static final String TEXT_HIGH = "HIGH";
	public static final String TEXT_DATE = "TIME";
	public static final String TEXT_EMA = "EMA";

	DotInteger open;
	DotInteger close;
	DotInteger low;
	DotInteger high;
	
	double ema;
	
	Date date;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append(TEXT_DATE).append(":").append(date);
		str.append(",").append(TEXT_EMA).append(":").append(ema);
		return str.toString();
	}
}
