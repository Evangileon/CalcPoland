package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;



public class Inverse extends Option {

	public Inverse() {
		super("inv", ParamC.RightOnly, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String function(String d0, String d1) {
		// TODO Auto-generated method stub
		Double num1 = Double.valueOf(d1);
		if(num1==0.0) {
			return null;
		}
		return String.valueOf((1/num1));
	}

}
