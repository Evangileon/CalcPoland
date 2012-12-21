package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;



public class Mod extends Option {

	public Mod() {
		super("%", ParamC.Both, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		try {
			Long num0 = Long.valueOf(d0);
			Long num1 = Long.valueOf(d1);
			
			return String.valueOf((num0 % num1));
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
