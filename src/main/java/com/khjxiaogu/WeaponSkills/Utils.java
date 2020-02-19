package com.khjxiaogu.WeaponSkills;

public class Utils {

	private Utils() {
		// TODO Auto-generated constructor stub
	}
	public static boolean randomPercent(double percent) {
		return Math.random()*100<percent;
	}
}
