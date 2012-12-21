package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;




public class Divide extends Option {
	public Divide()
    {
        super("/", ParamC.Both, 2);
    }

    public String function(String num0, String num1)
    {
        Double num0n = 0.0;
        Double num1n = 0.0;
        Double numR = 0.0;

        try
        {
            num0n = Double.valueOf(num0);
            num1n = Double.valueOf(num1);

            numR = num0n / num1n;

            return numR.toString();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();  
        }

        return null;
    }

}
