package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;


public class Factorial extends Option {
	public Factorial()
    {
        super("!", ParamC.LeftOnly, 4);
    }

    public String function(String num0, String num1)
    {
        Long num0n = (long) 0;
       
        try
        {
            num0n = Long.valueOf(num0);

            return Factorial.fact(num0n).toString();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();  
        }

        return null;
    }
    
    private static Long fact(Long value)
    {
        if (value == 0)
            return (long) 1;
        return value * Factorial.fact(value - 1);
    }
}
