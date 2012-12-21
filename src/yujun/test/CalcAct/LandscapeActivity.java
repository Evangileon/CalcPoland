package yujun.test.CalcAct;



import yujun.test.CalcAct.Calc.CalculatorIn;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class LandscapeActivity extends Activity implements OnClickListener{
    private CalculatorIn result;
    private EditText view;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_plus;
    private Button button_minus;
    private Button button_multi;
    private Button button_divide;
    private Button button_equal;
    private Button button_ans;
    private Button button_C;
	private Button button_back;
	private Button button_left;
	private Button button_right;
	
	private Button button_11;
	private Button button_12;
	private Button button_13;
	private Button button_14;
	
	private Button button_21;
	private Button button_22;
	private Button button_23;
	private Button button_24;
	
	private Button button_31;
	private Button button_32;
	private Button button_33;
	private Button button_34;
	
	private Button button_41;
	private Button button_42;
	private Button button_43;
	private Button button_44;
	
	private Button button_51;
	private Button button_52;
	private Button button_53;
	private Button button_54;
	
	private Button button_61;
	private Button button_62;
	private Button button_63;
	private Button button_64;

	
	boolean isClear = false;
	boolean afterEqual = false;
	boolean isSimple = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.landscape);
        this.initCalcIn();
        this.initEditText();
        this.initBasicButton();  
        this.initScienceButton(); 
        this.setBasicListener();
        this.setScienceListener();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);     
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //禁用软键盘
    }
    
    public void onClick(View v) {
		if(view.getText().toString().equals("0"))
			view.setText("");
		
		
		
	
		switch(v.getId()) {
		
			case R.id.Button_pluss:
				this.view.append("+");
				
				break;
			case R.id.Button_minuss:
				this.view.append("-");
				
				break;
			case R.id.Button_multis:
				this.view.append("*");
				
				break;
			case R.id.Button_divides:
				this.view.append("/");
				
				break;
			case R.id.Button_equals:
				
				if(view.getText().toString().equals("")) {
					view.setText("0");
					return;
				}
				System.out.println("is simple "+isSimple);
				
				if(isSimple) {
					view.setText(result.thenReturn());
					isSimple = true;
				}
				else if(result.isSimpleExp()) {
					
					this.view.setText(result.thenReturn());
					isSimple = true;
				}
				else {
					result.setExpression(this.view.getText().toString());
					System.out.println(view.getText().toString());

					if(result.calculate() != null)
						this.view.setText(result.getResult());
					else 
						this.view.setText("Syntax Error");
				}
				isClear = true;
				afterEqual = true;
				break;
			
			case R.id.Button_Cs:
				this.view.setText("0");
				//result.Read(this.view.getText().toString());
				this.result.clear();
				isClear = false;
				afterEqual = false;
				isSimple = false;
				//result.Clear();
				break;
			case R.id.Button_back:
				int length = view.getText().toString().length();
				this.view.setText(view.getText().toString().substring(0, length-1));
				break;
			case R.id.Button_left:
				this.view.append("(");
				
				break;
			case R.id.Button_right:
				this.view.append(")");
				
				break;
				
			default:
				isSimple = false; 
				switch(v.getId()) {
					case R.id.Button_0s:					
							this.view.append("0");
						break;
					case R.id.Button_1s:	
							this.view.append("1");
						break;	
					case R.id.Button_2s:
							this.view.append("2");
						break;			
					case R.id.Button_3s:
							this.view.append("3");
						break;
					case R.id.Button_4s:
							this.view.append("4");
						break;
					case R.id.Button_5s:
							this.view.append("5");
						break;
					case R.id.Button_6s:
							this.view.append("6");
						break;
					case R.id.Button_7s:
							this.view.append("7");
						break;
					case R.id.Button_8s:
							this.view.append("8");
						break;
					case R.id.Button_9s:
							this.view.append("9");
						break;
					
					default:onClickScience(v.getId());//科学计算器
				}
		}
	}
    
    private void onClickScience(int vId) {
    	switch(vId) {
    	case R.id.Button11 :
    		view.append("2nd");
    		break;
    	case R.id.Button12 :
    		view.append("(");
    		break;
    	case R.id.Button13 :
    		view.append(")");
    		break;
    	case R.id.Button14 :
    		view.append("%");
    		break;
    		
    	case R.id.Button21 :
    		view.append("inv");
    		break;
    	case R.id.Button22 :
    		view.append("square");
    		break;
    	case R.id.Button23 :
    		view.append("cube");
    		break;
    	case R.id.Button24 :
    		view.append("^");
    		break;
    		
    	case R.id.Button31 :
    		view.append("!");
    		break;
    	case R.id.Button32 :
    		view.append("sqrt");
    		break;
    	case R.id.Button33 :
    		view.append("nsqrt");
    		break;
    	case R.id.Button34 :
    		view.append("log");
    		break;

    	case R.id.Button41 :
    		view.append("sin");
    		break;
    	case R.id.Button42 :
    		view.append("cos");
    		break;
    	case R.id.Button43 :
    		view.append("tan");
    		break;
    	case R.id.Button44 :
    		view.append("ln");
    		break;
    		
    	case R.id.Button51 :
    		view.append("sinh");
    		break;
    	case R.id.Button52 :
    		view.append("cosh");
    		break;
    	case R.id.Button53 :
    		view.append("tanh");
    		break;
    	case R.id.Button54 :
    		view.append("e");
    		break;
    		
    	case R.id.Button61 :
    		view.append("rad");
    		break;
    	case R.id.Button62 :
    		view.append("PI");
    		break;
    	case R.id.Button63 :
    		//view.append("!");
    		break;
    	case R.id.Button64 :
    		view.append("rand");
    		break;
    	}
    }
    
    private void initCalcIn() {
    		result = new CalculatorIn();
	}
    
    private void initEditText() {
    	view = (EditText) this.findViewById(R.id.editText1);
    	view.setText("0");
    }
    
    private void initBasicButton(){	
    	button_0 = (Button) this.findViewById(R.id.Button_0s);
    	button_1 = (Button) this.findViewById(R.id.Button_1s);
    	button_2 = (Button) this.findViewById(R.id.Button_2s);
    	button_3 = (Button) this.findViewById(R.id.Button_3s);
    	button_4 = (Button) this.findViewById(R.id.Button_4s);
    	button_5 = (Button) this.findViewById(R.id.Button_5s);
    	button_6 = (Button) this.findViewById(R.id.Button_6s);
    	button_7 = (Button) this.findViewById(R.id.Button_7s);
    	button_8 = (Button) this.findViewById(R.id.Button_8s);
    	button_9 = (Button) this.findViewById(R.id.Button_9s);
    	
    	button_plus = (Button) this.findViewById(R.id.Button_pluss);
    	button_minus = (Button) this.findViewById(R.id.Button_minuss);
    	button_multi = (Button) this.findViewById(R.id.Button_multis);
    	button_divide = (Button) this.findViewById(R.id.Button_divides);
    	button_equal = (Button) this.findViewById(R.id.Button_equals);
    	//button_ans = (Button) this.findViewById(R.id.Button_anss);
    	
    	button_C = (Button) this.findViewById(R.id.Button_Cs);
    	button_back = (Button) this.findViewById(R.id.Button_back);
    	button_left = (Button) this.findViewById(R.id.Button_left);
    	button_right = (Button) this.findViewById(R.id.Button_right);
    }
    
    private void initScienceButton() {
    	button_11 = (Button) this.findViewById(R.id.Button11);
		button_12 = (Button) this.findViewById(R.id.Button12);
		button_13 = (Button) this.findViewById(R.id.Button13);
		button_14 = (Button) this.findViewById(R.id.Button14);
		
		button_21 = (Button) this.findViewById(R.id.Button22);
		button_22 = (Button) this.findViewById(R.id.Button22);
		button_23 = (Button) this.findViewById(R.id.Button23);
		button_24 = (Button) this.findViewById(R.id.Button24);
		
		button_31 = (Button) this.findViewById(R.id.Button32);
		button_32 = (Button) this.findViewById(R.id.Button32);
		button_33 = (Button) this.findViewById(R.id.Button33);
		button_34 = (Button) this.findViewById(R.id.Button34);
		
		button_41 = (Button) this.findViewById(R.id.Button42);
		button_42 = (Button) this.findViewById(R.id.Button42);
		button_43 = (Button) this.findViewById(R.id.Button43);
		button_44 = (Button) this.findViewById(R.id.Button44);
		
		button_51 = (Button) this.findViewById(R.id.Button52);
		button_52 = (Button) this.findViewById(R.id.Button52);
		button_53 = (Button) this.findViewById(R.id.Button53);
		button_54 = (Button) this.findViewById(R.id.Button54);
		
		button_61 = (Button) this.findViewById(R.id.Button62);
		button_62 = (Button) this.findViewById(R.id.Button62);
		button_63 = (Button) this.findViewById(R.id.Button63);
		button_64 = (Button) this.findViewById(R.id.Button64);

    }
    
    
    private void setBasicListener() {
    	this.button_0.setOnClickListener(this);
    	this.button_1.setOnClickListener(this);
    	this.button_2.setOnClickListener(this);
    	this.button_3.setOnClickListener(this);
    	this.button_4.setOnClickListener(this);
    	this.button_5.setOnClickListener(this);
    	this.button_6.setOnClickListener(this);
    	this.button_7.setOnClickListener(this);
    	this.button_8.setOnClickListener(this);
    	this.button_9.setOnClickListener(this);
    	
    	this.button_plus.setOnClickListener(this);
    	this.button_minus.setOnClickListener(this);
    	this.button_multi.setOnClickListener(this);
    	this.button_divide.setOnClickListener(this);
    	this.button_equal.setOnClickListener(this);
    	//this.button_ans.setOnClickListener(this);
    	
    	this.button_C.setOnClickListener(this);
    	this.button_back.setOnClickListener(this);
    	this.button_left.setOnClickListener(this);
    	this.button_right.setOnClickListener(this);
    }
    
    private void setScienceListener() {
    	this.button_11.setOnClickListener(this);
    	this.button_12.setOnClickListener(this);
    	this.button_13.setOnClickListener(this);
    	this.button_14.setOnClickListener(this);
    	
    	this.button_21.setOnClickListener(this);
    	this.button_22.setOnClickListener(this);
    	this.button_23.setOnClickListener(this);
    	this.button_24.setOnClickListener(this);
    	
    	this.button_31.setOnClickListener(this);
    	this.button_32.setOnClickListener(this);
    	this.button_33.setOnClickListener(this);
    	this.button_34.setOnClickListener(this);
    	
    	this.button_41.setOnClickListener(this);
    	this.button_42.setOnClickListener(this);
    	this.button_43.setOnClickListener(this);
    	this.button_44.setOnClickListener(this);
    	
    	this.button_51.setOnClickListener(this);
    	this.button_52.setOnClickListener(this);
    	this.button_53.setOnClickListener(this);
    	this.button_54.setOnClickListener(this);
    	
    	this.button_61.setOnClickListener(this);
    	this.button_62.setOnClickListener(this);
    	this.button_63.setOnClickListener(this);
    	this.button_64.setOnClickListener(this);
    }

	
}