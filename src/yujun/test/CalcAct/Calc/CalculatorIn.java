package yujun.test.CalcAct.Calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import yujun.test.CalcAct.option.Cos;
import yujun.test.CalcAct.option.Cosh;
import yujun.test.CalcAct.option.Divide;
import yujun.test.CalcAct.option.Factorial;
import yujun.test.CalcAct.option.Inverse;
import yujun.test.CalcAct.option.Ln;
import yujun.test.CalcAct.option.Log;
import yujun.test.CalcAct.option.Minus;
import yujun.test.CalcAct.option.Mod;
import yujun.test.CalcAct.option.Multi;
import yujun.test.CalcAct.option.Option;
import yujun.test.CalcAct.option.Plus;
import yujun.test.CalcAct.option.Power;
import yujun.test.CalcAct.option.Rand;
import yujun.test.CalcAct.option.Sin;
import yujun.test.CalcAct.option.Sinh;
import yujun.test.CalcAct.option.Sqrt;
import yujun.test.CalcAct.option.Tan;
import yujun.test.CalcAct.option.Tanh;

/*******************************************************************
 *  WHILE有输入符号
 *		读入下一个符号
 * 		IF 是一个操作数
 *			输出到逆波兰表达式
 *		ELSE IF	是一个"("
 *			入符号栈
 *		ELSE IF 是一个")"
 *			出栈并顺序输出运算符至逆波兰表达式直到遇到第一个'('，遇到的第一个'('出栈但不输出
 *		ELSE 是一个操作符
 *			比较stackOperator栈顶元素与当前元素的优先级
 *			IF 栈顶元素优先级 >= 当前
 *				出栈并顺序输出运算符直到 栈顶元素优先级 < 当前元素优先级，然后当前元素入栈
 *			ELSE
 *				当前元素直接入栈
 *	 顺序出栈并输出运算符直到空栈	
 *******************************************************************	
 */

public class CalculatorIn {
    private Stack<String> operandStack = new Stack<String>();//操作数堆栈
    private Stack<String> operatorStack = new Stack<String>();//操作符堆栈
    private String expression;//算数表达式
    private String result="0.0";//计算结果
    private String reg0;
    private String reg1;
    private String ansreg;
    private String oper;//简单运算的四则运算符
    
    private TypeBefore typeBefore = TypeBefore.No;  //
    /*****************************************************************
     * 用于语法检验，通过当前的前一个不允许是什么来约束
     * 
     * Prevent         IF Before is ,then syntax error
     * 
     * Number          ")"  ,  FuncLeft  ,  NoParam
     * "("             Number  ,  FuncLeft  ,  NoParam
     * ")"             FuncRight  ,  FuncBoth  ,  No
     * FuncLeft        "("  ,  FuncRight  ,  FuncBoth  ,  No  ,  NoParam
     * FuncRight       Number  ,  ")"  ,  FuncLeft  
     * FuncBoth        "("  ,  FuncRight  ,  No
     * NpParam         ")"  ,  Number  ,  FuncLeft
     *   
     * ***************************************************************
     * */
    
    private Map<String, Option> optionMap = new HashMap<String, Option>();
    
    		//初始化优先级约定(可根据计算的复杂程度扩展)
    public CalculatorIn()
    {  
        this.initBasic();
        this.initScience();
    }
   
    public int getPriority(String op)//得到一个操作符的优先级
    {
        return ((Option)optionMap.get(op)).getPriority();
    }
   
    
    //把表达式转化成逆波兰式
    public String expToIpn()
    {
    
        int nstart = 0;  //number
        int nend = 0;
        int ostart = 0;  //operator
        int oend = 0;
        
        
        
        String Ipn="";
        
        for(int i=0;i<expression.length();i++) {
            
        	char tempc = expression.charAt(i);
            
        	if(tempc <= '9' && tempc >= '0')//检查是否是数字
        	{
                if(isSyntaxRight(typeBefore, TypeBefore.Number) == false)
                	return null;   //语法检查
                
        		typeBefore = TypeBefore.Number;
        		nstart = i;
        		nend = howNumber(expression, nstart);
        		i = nend - 1;
        		Ipn += expression.substring(nstart, nend)+",";
        		//continue;
            }else if(tempc == '(') {
            	System.out.println("before check ( at i = "+i);
            	if(isSyntaxRight(typeBefore, TypeBefore.LeftBracket) == false)
                	return null;   //语法检查
            	System.out.println("after check ( at i = "+i);
            	typeBefore = TypeBefore.LeftBracket;
        		operatorStack.push("(");
        		continue;
            }else if(tempc == ')') {
            	if(isSyntaxRight(typeBefore, TypeBefore.RightBracket) == false)
                	return null;   //语法检查
            	
            	typeBefore = TypeBefore.RightBracket;
        		String temp;
        		if(!operatorStack.contains("("))
        			return null;
        		while(!operatorStack.empty()) {
        			temp = operatorStack.pop();
        			if(temp.equals("("))
        				break;
        			Ipn += temp +",";
        		}//直到第一个“（”，出栈但不输出
        		
        	} 
        	else {          //除括号以外的操作符第一个字符
        		
            	ostart = i;
            	oend = howOperator(expression, ostart);
            	i = oend - 1;
        		//操作符第一个字符之前的是数字，执行推栈
            	//此时遍历至操作符后的第一个数字
            	//之前的即操作符
            	
            	String tempOperator = expression.substring(ostart, oend);
            	Option tempOption = optionMap.get(tempOperator);
            	if(tempOption==null)
            		return null;
            	
            	
            		
            	
            	ParamC tempParam = tempOption.getNum();
            	if(!isSyntaxRight(typeBefore, tempParam)) {
            		System.out.println("syntax is NOT right");
            		if(beforeMinusIsLeftBracket(typeBefore, tempOperator)) {
            			nstart = i+1;
            			nend = howNumber(expression, nstart);
            			String tempNum = expression.substring(nstart, nend);
            			if(tempNum==null)
            				return null;
            			i = nend;
            			Ipn += "-" + tempNum + ",";
            			System.out.println("syntax bracket before +");
                	} //有一个特例，即括号后面紧挨着负号，和开头即是负号
            		else
            			return null;    //语法检查
            	}
            		
            	System.out.println("after issyntaxRight Ipn = "+Ipn);
            	System.out.println("Syntax Right at i = "+i);
            				
            	if((typeBefore = NParamToType(tempParam)) == null)
            		return null;
            	//if((t))
            		
            	if(tempOption!=null)
            		System.out.println("name "+tempOption.getName());
            	
            	int tempPriority = tempOption.getPriority();
        	
            	if(operatorStack.empty())
            		operatorStack.push(tempOperator);
            	else if((operatorStack.peek()).equals("("))
            		operatorStack.push(tempOperator);
            	else if(getPriority(operatorStack.peek())
       					< tempPriority) {
            		operatorStack.push(tempOperator);
            	}
            	else {
            		//栈顶元素优先级大于等于当前
            		String temp;
            		while((!operatorStack.empty()) 
            				&& (getPriority(operatorStack.peek())>=tempPriority)) {
            			temp = operatorStack.pop();
            			Ipn += temp + ",";
            		}
            		operatorStack.push(tempOperator);
            	}
            	System.out.println("end else i =  "+ i);
        	}//end else
        }//end for
        
        String tempo;
        while(!operatorStack.empty()){
        	tempo = operatorStack.pop();
        	Ipn += tempo + ",";
        }
        	
        
        return Ipn;
    }
    
    /**/
    
    public String calculateIpn(String[] Ipn)//计算逆波兰式
    {
         
        for(int i=0;i<Ipn.length;i++)
        {
        	System.out.println("Ipn["+i+"] "+Ipn[i]);
        	if(Ipn.equals(""))
        		continue;
            if(Ipn[i].matches("^[0-9]+.?[0-9]*$"))//正则表达式判断是数字
            {
                operandStack.push(Ipn[i]);
            }
            else
            {
            	if(!popOperand(optionMap.get(Ipn[i]))) {
            		System.out.println("Ipn["+i+"] "+"function is null");
            		return null;
            	}
            	System.out.println("number " + operandStack.peek());	
            }
        }
        this.result = operandStack.pop();
        if(operandStack.empty())
        	System.out.println("Right");
        return this.result;
    }
    //遇到操作符时，弹出操作数，进行相应操作，并保村result
    private boolean popOperand(Option op)
    {
        String d0 = null;
        String d1 = null;
        String temp = null;
        
        if(op.getNum() == ParamC.NoParam) {
        	temp = op.function(null, null);
        }
        else if(op.getNum() == ParamC.LeftOnly)        //只需左操作数
    	{
    		if(!operandStack.empty())
    			d0 = operandStack.pop();
    		else 
    			return false;
    		
    		temp = op.function(d0, null);
    	}
    	else if(op.getNum() == ParamC.RightOnly)
    	{
    		System.out.println("before op.num==1");
    		if(!operandStack.empty())
    			d1 = operandStack.pop();
    		else 
    			return false;
    		System.out.println("after op.num==1");
    		
    		temp = op.function(null, d1);
    	}
    	else if(op.getNum()== ParamC.Both)
    	{
    		if(!operandStack.empty())
    			d1 = operandStack.pop();
    		else 
    			return false;
    		System.out.println(op.getName()+" d1 = "+d1 );
    		if(!operandStack.empty())
    			d0 = operandStack.pop();
    		else 
    			return false;
    		System.out.println(op.getName()+" d0 = "+d0);
    		
    		temp = op.function(d0, d1);
    		System.out.println("after function "+op.getName()+" d0 = "+d0);
    	}
    	
    	//System.out.println(result);
        operandStack.push(temp);
        return true;
    }
    
    private void initBasic() {
    	this.addOption(new Plus());
    	this.addOption(new Minus());
    	this.addOption(new Multi());
    	this.addOption(new Divide());
    }
    
    private void initScience() {
    	this.addOption(new Cos());
    	this.addOption(new Sin());
    	this.addOption(new Cosh());
    	this.addOption(new Sinh());
    	this.addOption(new Tan());
    	this.addOption(new Tanh());
    	this.addOption(new Log());
    	this.addOption(new Ln());
    	this.addOption(new Rand());
    	this.addOption(new Mod());
    	this.addOption(new Inverse());
    	this.addOption(new Factorial());
    	this.addOption(new Power());
    	this.addOption(new Sqrt());
    }
    
    public void addOption(Option opt) {
    	this.optionMap.put(opt.getName(), opt);
    }

    public Stack<String> getOperandStack() {
        return operandStack;
    }
    public void setOperandStack(Stack<String> operandStack) {
        this.operandStack = operandStack;
    }
    public Stack<String> getOperatorStack() {
        return operatorStack;
    }
    public void setOperatorStack(Stack<String> operatorStack) {
        this.operatorStack = operatorStack;
    }
    public String getExpression() {
        return expression;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    
    //NOT Negative!!!
    private int howNumber(String exp,int nstart) {
		int i = nstart;
		int length = exp.length();
		
		
		while(i < length) {
			char tempn = exp.charAt(i++);
			if(!(tempn<='9' && tempn >= '0')) {
				i--;
				break;
			}
		}
		
		System.out.println("in howNumber nstart = "+nstart+", nend = "+i);
 
    	return i;  //return nend
    }
    
    private int howOperator(String exp,int ostart) {
		int i = ostart;
		int length = exp.length();
		
		char tempc = exp.charAt(i++);
		if( !((tempc <='z' && tempc >= 'a')
				|| (tempc <='Z' && tempc >= 'A')))
    		return i;
		
		while(i < length) {
			tempc = exp.charAt(i++);
			
			if(!((tempc <='z' && tempc >= 'a')
				|| (tempc <='Z' && tempc >= 'A'))) {
				i--;
				break;
			}	
		}
		
    	return i;  //return oend
    }
    
   
    
    public String calculate() {
  
 	   String temp = this.expToIpn();
 	   System.out.println("expToIpn completed");
 	   if(temp == null) {
 		   result = null;
 		   System.out.println("Syntax Error calculate()");
 		   return "Syntax Error";
 	   }
 	   System.out.println("Ipn "+temp);
        String[] Ipn= temp.split(","); 
        result = this.calculateIpn(Ipn);
        return result;
    }
    
    private boolean isSyntaxRight(TypeBefore tBefore,TypeBefore tPrevent) {
    	switch(tPrevent) {
    	case No:
    		
    		return true;
    	case Number:
    		if(tBefore == TypeBefore.RightBracket 
    			|| typeBefore == TypeBefore.FuncLeft
    			|| typeBefore == TypeBefore.NoParam)
    			return false;
    		break;
    	case LeftBracket:
    		if(tBefore == TypeBefore.Number
				|| typeBefore == TypeBefore.FuncLeft
				|| typeBefore == TypeBefore.NoParam)
    			return false;
    		break;
    	case RightBracket:
    		if(tBefore == TypeBefore.FuncRight
				|| typeBefore == TypeBefore.FuncBoth
				|| typeBefore == TypeBefore.No)
    			return false;
    		break;
    	case FuncLeft:
    		if(tBefore == TypeBefore.LeftBracket
    			||tBefore == TypeBefore.FuncRight
    			||tBefore == TypeBefore.FuncBoth
    			||tBefore == TypeBefore.No
    			|| typeBefore == TypeBefore.NoParam)
    			return false;
    		break;
    	case FuncRight:
    		if(tBefore == TypeBefore.RightBracket
			||tBefore == TypeBefore.FuncLeft
			||tBefore == TypeBefore.Number)
			return false;
    		break;
    	case FuncBoth:
    		if(tBefore == TypeBefore.LeftBracket
			||tBefore == TypeBefore.FuncRight
			||tBefore == TypeBefore.No)
			return false;
    		break;
    	case NoParam:
    		if(tBefore == TypeBefore.RightBracket
			||tBefore == TypeBefore.FuncLeft
			||tBefore == TypeBefore.Number)
			return false;
    		break;
    	default:
    			return false;
    	}
    	return true;
    }
    
    private boolean isSyntaxRight(TypeBefore tBefore,ParamC pPrevent) {
    	TypeBefore tempPrevent;
    	if(pPrevent == ParamC.NoParam)
    		tempPrevent = TypeBefore.NoParam;
    	else if(pPrevent == ParamC.LeftOnly)
    		tempPrevent = TypeBefore.FuncLeft;
    	else if(pPrevent == ParamC.RightOnly)
    		tempPrevent = TypeBefore.FuncRight;
    	else if(pPrevent == ParamC.Both)
    		tempPrevent = TypeBefore.FuncBoth;
    	else
    		return false;
    	
    	return isSyntaxRight(tBefore, tempPrevent);
    }
    
    private TypeBefore NParamToType(ParamC param) {
    	TypeBefore tempPrevent;
    	if(param == ParamC.NoParam)
    		tempPrevent = TypeBefore.NoParam;
    	else if(param == ParamC.LeftOnly)
    		tempPrevent = TypeBefore.FuncLeft;
    	else if(param == ParamC.RightOnly)
    		tempPrevent = TypeBefore.FuncRight;
    	else if(param == ParamC.Both)
    		tempPrevent = TypeBefore.FuncBoth;
    	else
    		return null;
    	
    	return tempPrevent;
    }
    
    private boolean beforeMinusIsLeftBracket(TypeBefore t,String operation) {
    	if(!operation.equals("-"))
    		return false;
    	if(t == TypeBefore.LeftBracket || t == TypeBefore.No)
    		return true;
    	return false;
    }//括号后面紧挨着负号，和开头就是负号符合语法规范
    
   
    //pair to thenReturn()
    public boolean isSimpleExp() {
    	//double flag = 1;
    	int nstart = 0;
    	int nend = 0;
    	
    	if(expression == null)
    		return false;
    	
    	if(expression.charAt(0) == '-') {
    		if(!(expression.charAt(1) >= '0'  
    				&& expression.charAt(1) <= '9'))
    			return false;
    		nstart = 1; 
    	}
    	
    	nend = howNumber(expression, nstart);
    	reg0 = expression.substring(nstart, nend);
    	System.out.println("reg0 = "+reg0);
    	if(reg0 == null)
    		return false;
    	if(nend == expression.length())
    		 return true;
    	
    	if(!((oper = expression.substring(nend, nend+1)).equals("+")
    			|| oper.equals("-") || oper.equals("*") || oper.equals("/"))) {
        		return false;
    	}
    	
    	nstart = nend + 1;
    	nend = howNumber(expression, nstart);
    	
    	if(nend != expression.length())
    		return false;
    	
    	reg1 = expression.substring(nstart, nend);
    	System.out.println("reg1 = "+reg1);
    	if(reg1 == null)
    		return false;
    	
    	return true;
    }//如果是简单表达式，则支持连等
    
    public String thenReturn() {
    	if(reg0 == null)
    		return null;
    	if(reg1 == null)
    		return null;
    	if(oper == null)
    		return reg0;
    	expression = reg0 + oper + reg1;
    	this.calculate();
    	ansreg = this.result;
    	reg0 = ansreg;
    	return this.result;
    }
    
    public void clear() {
    	expression = null;
    	result = null;
    	reg0 = null;
    	reg1 = null;
    	ansreg = null;
    }
    
    public static void main(String[] args) {
        CalculatorIn cal=new CalculatorIn();
        String exp="32+sqrt2*2*2*2-2/5+2^6-cos(sin(6))";
        cal.setExpression(exp);
       
        
        System.out.println(cal.calculate());
        
    }
}

