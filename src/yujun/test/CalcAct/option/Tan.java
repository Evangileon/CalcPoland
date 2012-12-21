package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public class Tan extends Option {

	public Tan() {
		super("tan", ParamC.RightOnly, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		Double num1 = Double.valueOf(d1);
		Double result = Math.tan(num1);
		return result.toString();
	}

}
