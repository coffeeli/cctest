package utils;

import utils.UUID;

public class IDUtils {

	public static String getSerialNo() {
		return UUID.randomUUID().toString();
	}
}
