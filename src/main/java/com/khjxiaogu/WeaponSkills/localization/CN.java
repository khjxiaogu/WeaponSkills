package com.khjxiaogu.WeaponSkills.localization;

public final class CN implements LocalizationProvider {
	public String[] help;

	public CN() {
		// TODO Auto-generated constructor stub
		help = new String[6];
		help[0] = "§e武器技能插件  By khjxiaogu";
		help[1] = "§e输入/wskill set [技能名称] [技能等级|clear] 设置手中物品技能，填入clear即移除";
		help[2] = "§e输入/wskill clear 删除手中物品所有技能";
		help[3] = "§e输入/wskill read 读取手中物品技能";
		help[4] = "§e输入/wskill effect <玩家> <效果名称> <效果等级> <效果持续时间ms> 给玩家一个插件效果";
		help[5] = "§e输入/wskill list <skill/effect> 查看技能/效果列表";
	}

	@Override
	public String[] getHelp() {
		return help;
	}

	@Override
	public String getSkillCleared() {
		return "§e技能已经清除！";
	}

	@Override
	public String getSkillWritten() {
		return "§e技能已经写入！";
	}

	@Override
	public String getSkillRemoved() {
		return "§e技能已经移除！";
	}

	@Override
	public String getNoSkill() {
		return "§4该物品查无信息！";
	}

	@Override
	public String getSkillInHand() {
		return "§e手中物品技能：";
	}

	@Override
	public String getInvalidSkill() {
		return "§4技能无效！";
	}

	@Override
	public String getIsvalidSkill() {
		return "§a技能有效！";
	}

	@Override
	public String getEffectGiven() {
		return "§e效果给予成功！";
	}

	@Override
	public String getEffectInvalid() {
		return "§4效果不存在！";
	}

	@Override
	public String getBadParamCount() {
		return "§4参数不足";
	}

	@Override
	public String getInvalidParam() {
		return "§4参数有误";
	}

	@Override
	public String getListOfSkill() {
		return "§e技能列表：";
	}

	@Override
	public String getListOfEffect() {
		return "§e效果列表：";
	}

	@Override
	public String getCurrentListOfSkill() {
		return "§e生效的技能列表：";
	}
}
