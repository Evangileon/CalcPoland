package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;



public class Sinh extends Option {

	public Sinh() {
		super("sinh", ParamC.RightOnly, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		Double num1 = Double.valueOf(d1);
		return String.valueOf(Math.sin(num1));
	}

}
