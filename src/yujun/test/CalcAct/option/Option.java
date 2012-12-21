package yujun.test.CalcAct.option;

import yujun.test.CalcAct.Calc.ParamC;

public abstract class Option {
	private String name;    //���������ƣ���������
    private ParamC num;       //��Ҫ�Ĳ�������0Ϊֻ������������1Ϊֻ���Ҳ�������2Ϊ��Ҫ����
    private int priority;   //���ȼ�

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
