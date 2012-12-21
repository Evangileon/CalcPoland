package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public class Tanh extends Option {

	public Tanh() {
		super("tanh", ParamC.RightOnly, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		Double num1 = Double.valueOf(d1);
		Double result = Math.tanh(num1);
		return result.toString();
	}

}
