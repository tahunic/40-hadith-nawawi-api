package com.api.hadithnawawi.data.enums;

public enum Translation {

	BS,
	EN;

	public static Translation fromString(String translation) {
		try {
			return Translation.valueOf(translation);
		} catch (Exception e) {
			return null;
		}
	}
}
