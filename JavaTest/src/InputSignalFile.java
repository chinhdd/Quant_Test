import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InputSignalFile {
	
	public enum eDataType {
		NONE,
		INVALID,
		DATE_TIME,
		OPEN,
		CLOSE,
		LOW,
		HIGH,
		EMA,
	}
	
	private BufferedReader mBufferReader = null;

	public InputSignalFile(String fileName) {
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mBufferReader = bufferReader;
	}
	
	private eDataType getDataType(String s) {
		if (s == null) return eDataType.INVALID;
		
		while (s.length() > 0 && s.charAt(0) == ' ') {
			s = s.substring(1);
		}
		if (s.length() == 0) return eDataType.NONE;
		if (s.startsWith(BlockData.TEXT_DATE)) {
			return eDataType.DATE_TIME;
		} else if (s.startsWith(BlockData.TEXT_HIGH)) {
			return eDataType.HIGH;
		} else if (s.startsWith(BlockData.TEXT_LOW)) {
			return eDataType.LOW;
		} else if (s.startsWith(BlockData.TEXT_OPEN)) {
			return eDataType.OPEN;
		} else if (s.startsWith(BlockData.TEXT_CLOSE)) {
			return eDataType.CLOSE;
		} else if (s.startsWith(BlockData.TEXT_EMA)) {
			return eDataType.EMA;
		}
		return eDataType.INVALID;
	}
	
	public BlockData nextBlock() {
		if (mBufferReader == null) return null;
		while (true) {
			try {
				String firstLine = mBufferReader.readLine();
				eDataType type = getDataType(firstLine);
				if (type == eDataType.NONE) {
					continue;
				} else if (type == eDataType.INVALID) {
					return null;
				} else if (type == eDataType.DATE_TIME) {
					// start read block data here
					BlockData data = new BlockData();
					data.date = (Date) getRealData(firstLine, eDataType.DATE_TIME);
					
					String line = mBufferReader.readLine();
					data.ema = (Double) getRealData(line, eDataType.EMA);
					
					line = mBufferReader.readLine();
					data.open = (DotInteger) getRealData(line, eDataType.OPEN);
					
					line = mBufferReader.readLine();
					data.close = (DotInteger) getRealData(line, eDataType.CLOSE);
					
					line = mBufferReader.readLine();
					data.high = (DotInteger) getRealData(line, eDataType.HIGH);
					
					line = mBufferReader.readLine();
					data.low = (DotInteger) getRealData(line, eDataType.LOW);
					return data;
				} else {
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	
	private Object getRealData(String str, eDataType type) {
		switch (type) {
		case OPEN:
			str = str.substring(BlockData.TEXT_OPEN.length() + 1);
			return new DotInteger(str);
		case CLOSE:
			str = str.substring(BlockData.TEXT_CLOSE.length() + 1);
			return new DotInteger(str);
		case LOW:
			str = str.substring(BlockData.TEXT_LOW.length() + 1);
			return new DotInteger(str);
		case HIGH:
			str = str.substring(BlockData.TEXT_HIGH.length() + 1);
			return new DotInteger(str);
		case DATE_TIME:
			str = str.substring(BlockData.TEXT_DATE.length() + 1);
			return getDateTime(str);
		case EMA:
			str = str.substring(BlockData.TEXT_EMA.length() + 1);
			return new Double(str);
		default:
			break;
		}
		return null;
	}
	
	private Date getDateTime(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm s");
		try {
			Date date = format.parse(str);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

