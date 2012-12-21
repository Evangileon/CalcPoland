package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public class Rand extends Option {

	public Rand() {
		super("rand", ParamC.NoParam, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		Double result = Math.random();
		return result.toString();
	}

}
