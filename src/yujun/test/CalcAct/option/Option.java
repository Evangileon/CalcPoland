package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public abstract class Option {
	private String name;    //操作符名称，包括函数
    private ParamC num;       //需要的操作数，0为只需做操作数，1为只需右操作数，2为需要两者
    private int priority;   //优先级

    public Option(String _name,ParamC param,int priority) {
    	this.name = _name;
    	this.num = param;
    	this.priority = priority;
    }
    
    public String getName() {return this.name;}
    public ParamC getNum() {return this.num;}
    public int getPriority() {return this.priority;}
    
    public abstract String function(String d0, String d1);
}
