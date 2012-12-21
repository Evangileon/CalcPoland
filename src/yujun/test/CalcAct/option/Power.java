package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;



public class Power extends Option {

	public Power() {
		super("^", ParamC.Both, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		return String.valueOf(Math.pow(Double.valueOf(d0), Double.valueOf(d1)));
	}

}
