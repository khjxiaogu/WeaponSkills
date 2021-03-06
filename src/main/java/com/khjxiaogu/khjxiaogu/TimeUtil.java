package com.khjxiaogu.khjxiaogu;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class TimeUtil {
	private static JavaPlugin plugin;
	private static boolean canGetCalendar = true;
	private static boolean canGetDate = true;
	private static boolean canGetNano = true;

	public static void setPlugin(JavaPlugin plugin) {
		TimeUtil.plugin = plugin;
	}

	public static long getTime() {
		if (TimeUtil.canGetCalendar) {
			try {
				return Calendar.getInstance().getTimeInMillis();
			} catch (Throwable T) {
				T.printStackTrace();
				TimeUtil.canGetCalendar = false;
				if (TimeUtil.plugin != null) {
					TimeUtil.plugin.getLogger().log(Level.WARNING, "日历获取失败，尝试使用日期获得时间...");
				}
			}
		} else if (TimeUtil.canGetDate) {
			try {
				return new Date().getTime();
			} catch (Throwable T) {
				T.printStackTrace();
				TimeUtil.canGetDate = false;
				if (TimeUtil.plugin != null) {
					TimeUtil.plugin.getLogger().log(Level.WARNING, "日期获取失败，尝试使用精确系统时间获得时间...");
				}
			}
		} else if (TimeUtil.canGetNano) {
			try {
				return System.nanoTime() / 1000000L;
			} catch (Throwable T) {
				T.printStackTrace();
				TimeUtil.canGetNano = false;
				if (TimeUtil.plugin != null) {
					TimeUtil.plugin.getLogger().log(Level.WARNING, "精确系统时间获取失败，尝试使用粗略系统时间获得时间...");
				}
			}
		}
		return System.currentTimeMillis();
	}

}
