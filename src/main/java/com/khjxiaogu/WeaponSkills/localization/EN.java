package com.khjxiaogu.WeaponSkills.localization;

public class EN implements LocalizationProvider {
	public String[] help;
	public EN() {
		// TODO Auto-generated constructor stub
		help=new String[6];
		help[0]=("§eWeaponSkills Plugin  By khjxiaogu");
		help[1]=("§euse/wskill set [skillname] [level|0]§f to add or modify a skill on the item in your hand,set level to 0 means removal.");
		help[2]=("§euse/wskill clear§f to clear all skills on the item in your hand");
		help[3]=("§euse/wskill read§f to read the list of skill on the item in your hand");
		help[4]=("§euse/wskill effect <playername> <effectname> <level> <duration/ms>§f to give player a plugin effect");
		help[5]=("§euse/wskill list <skill/effect>§f to list all skills or effects");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return help;
	}

	@Override
	public String getSkillCleared() {
		// TODO Auto-generated method stub
		return "§eall skills has been removed";
	}

	@Override
	public String getSkillWritten() {
		// TODO Auto-generated method stub
		return "§eskill has been written";
	}

	@Override
	public String getSkillRemoved() {
		// TODO Auto-generated method stub
		return "§eskill has been removed";
	}

	@Override
	public String getNoSkill() {
		// TODO Auto-generated method stub
		return "§eno skill has been bind to this item.";
	}

	@Override
	public String getSkillInHand() {
		// TODO Auto-generated method stub
		return "§elist of skills in hand";
	}

	@Override
	public String getInvalidSkill() {
		// TODO Auto-generated method stub
		return "§4Skill Invalid";
	}

	@Override
	public String getIsvalidSkill() {
		// TODO Auto-generated method stub
		return "§aSkill Valid";
	}

	@Override
	public String getEffectGiven() {
		// TODO Auto-generated method stub
		return "§eeffect given";
	}

	@Override
	public String getEffectInvalid() {
		// TODO Auto-generated method stub
		return "§4Effect Invalid";
	}

	@Override
	public String getBadParamCount() {
		// TODO Auto-generated method stub
		return "§4Bad param count";
	}

	@Override
	public String getInvalidParam() {
		// TODO Auto-generated method stub
		return "§4Invalid param";
	}

	@Override
	public String getListOfSkill() {
		// TODO Auto-generated method stub
		return "§4List of all skills";
	}

	@Override
	public String getListOfEffect() {
		// TODO Auto-generated method stub
		return "§4List of all effects";
	}

}
