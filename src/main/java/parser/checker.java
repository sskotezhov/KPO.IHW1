package main.java.parser;

public interface checker {
	public static boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?");
	}
}
