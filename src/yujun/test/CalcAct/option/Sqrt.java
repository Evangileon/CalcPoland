package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;




public class Sqrt extends Option {
	public Sqrt()
    {
        super("sqrt", ParamC.RightOnly, 3);
    }

    public String function(String num0, String num1)
    {
        Double num1n = 0.0;

        try
        {
            num1n = Double.valueOf(num1);
            if(num1n < 0.0)
            	return null;

            return Sqrt.extract(num1n).toString();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();  
        }

        return null;
    }

    private static Double extract(Double value)
    {
        return Math.sqrt(value);
    }

}
