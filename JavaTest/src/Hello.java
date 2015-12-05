
public class Hello {

	public static void main(String[] argv) {
		System.out.println("Hello Java");
		InputSignalFile inputFile = new InputSignalFile("EUR_USD_1m.txt");
		BlockData data = null;
		do {
			data = inputFile.nextBlock();
			System.out.println(data);
		} while (data != null);
	}
}
