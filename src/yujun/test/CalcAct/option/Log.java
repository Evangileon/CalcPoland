package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public class Log extends Option {

	public Log() {
		super("log", ParamC.Both, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		Double num0 = Double.valueOf(d0);
		Double num1 = Double.valueOf(d1);
		if(num1 <= 0.0 || num0 <= 0.0)
			return null;
		else {
			Double result = 0.0;
			result = Math.log(num1) / Math.log(num0);
			return result.toString();
		}
	}

}
