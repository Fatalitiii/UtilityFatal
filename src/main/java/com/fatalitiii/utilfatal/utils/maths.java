package com.fatalitiii.utilfatal.utils;

public class maths {

	public static int round(double number, int multiple) {
		int result = multiple;
		if (number % multiple == 0) {
			return (int) number;
		}
		if (number % multiple != 0) {
			int division = (int) ((number / multiple) + 1);
			result = division * multiple;
		}
		return result;
	}
}
