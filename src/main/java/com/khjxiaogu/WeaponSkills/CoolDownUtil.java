package com.khjxiaogu.WeaponSkills;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.khjxiaogu.khjxiaogu.TimeUtil;

public class CoolDownUtil {
	private static Map<Player, PlayerCoolDown> cooldownmap = new ConcurrentHashMap<>();

	public static long applyCoolDown(Player p, String reason, int milli) {
		PlayerCoolDown pcd = CoolDownUtil.cooldownmap.get(p);
		if (pcd == null) {
			pcd = new PlayerCoolDown();
			CoolDownUtil.cooldownmap.put(p, pcd);
		}
		return pcd.applyCoolDown(reason, milli);
	}

	public static boolean isCoolDown(Player p, String reason) {
		PlayerCoolDown pcd = CoolDownUtil.cooldownmap.get(p);
		if (pcd == null) {
			pcd = new PlayerCoolDown();
			CoolDownUtil.cooldownmap.put(p, pcd);
		}
		return pcd.isCoolDown(reason);
	}

	public static void resetCoolDown(Player p, String reason) {
		PlayerCoolDown pcd = CoolDownUtil.cooldownmap.get(p);
		if (pcd == null) {
			pcd = new PlayerCoolDown();
			CoolDownUtil.cooldownmap.put(p, pcd);
		}
		pcd.resetCoolDown(reason);
	}

	public static void renewCoolDown(Player p, String reason, int milli) {
		PlayerCoolDown pcd = CoolDownUtil.cooldownmap.get(p);
		if (pcd == null) {
			pcd = new PlayerCoolDown();
			CoolDownUtil.cooldownmap.put(p, pcd);
		}
		pcd.renewCoolDown(reason, milli);
	}

}

class PlayerCoolDown {
	private Map<String, Long> CoolDowns = new ConcurrentHashMap<>();

	PlayerCoolDown() {
	}

	public long applyCoolDown(String reason, int milli) {
		long current = TimeUtil.getTime();
		Long l = CoolDowns.get(reason);
		if (l != null && l > current)
			return l - current;
		CoolDowns.put(reason, current + milli);
		return 0;
	};

	public boolean isCoolDown(String reason) {
		Long l = CoolDowns.get(reason);
		if (l != null && l > TimeUtil.getTime())
			return true;
		;
		return false;
	}

	public void resetCoolDown(String reason) {
		CoolDowns.remove(reason);
	};

	public void renewCoolDown(String reason, int milli) {
		CoolDowns.put(reason, TimeUtil.getTime() + milli);
	};
}
