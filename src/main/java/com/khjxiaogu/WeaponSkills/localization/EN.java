package com.khjxiaogu.WeaponSkills.localization;

public class EN implements LocalizationProvider {
	public String[] help;
	public EN() {
		// TODO Auto-generated constructor stub
		help=new String[6];
		help[0]=(Messages.getString("EN.0")); //$NON-NLS-1$
		help[1]=(Messages.getString("EN.1")); //$NON-NLS-1$
		help[2]=(Messages.getString("EN.2")); //$NON-NLS-1$
		help[3]=(Messages.getString("EN.3")); //$NON-NLS-1$
		help[4]=(Messages.getString("EN.4")); //$NON-NLS-1$
		help[5]=(Messages.getString("EN.5")); //$NON-NLS-1$
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
		return Messages.getString("EN.6"); //$NON-NLS-1$
	}

	@Override
	public String getSkillWritten() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.7"); //$NON-NLS-1$
	}

	@Override
	public String getSkillRemoved() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.8"); //$NON-NLS-1$
	}

	@Override
	public String getNoSkill() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.9"); //$NON-NLS-1$
	}

	@Override
	public String getSkillInHand() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.10"); //$NON-NLS-1$
	}

	@Override
	public String getInvalidSkill() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.11"); //$NON-NLS-1$
	}

	@Override
	public String getIsvalidSkill() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.12"); //$NON-NLS-1$
	}

	@Override
	public String getEffectGiven() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.13"); //$NON-NLS-1$
	}

	@Override
	public String getEffectInvalid() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.14"); //$NON-NLS-1$
	}

	@Override
	public String getBadParamCount() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.15"); //$NON-NLS-1$
	}

	@Override
	public String getInvalidParam() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.16"); //$NON-NLS-1$
	}

	@Override
	public String getListOfSkill() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.17"); //$NON-NLS-1$
	}

	@Override
	public String getListOfEffect() {
		// TODO Auto-generated method stub
		return Messages.getString("EN.18"); //$NON-NLS-1$
	}

}
