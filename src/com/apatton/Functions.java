package com.apatton;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Functions {

	private ArrayList<Variable> vars = new ArrayList<Variable>();
	
	public Functions()
	{
		
	}
	
	public boolean isAction(String s)
	{
		return (  s.contains("clear")  ||  s.contains("exit")  ||  s.contains("help")  ||  s.contains("cowculator")  );
	}
	
	public boolean isFunction(String s)
	{
		return (  s.contains("sin")  ||  s.contains("cos")  ||  s.contains("tan")  ||  s.contains("binary")  ||  s.contains("hex")  ||  s.contains("set")  );
	}
	
	public String sinOf(String s)
	{
		return String.valueOf(BigDecimal.valueOf(Math.sin(Double.parseDouble(s))).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
	}
	
	public String cosOf(String s)
	{
		return String.valueOf(BigDecimal.valueOf(Math.cos(Double.parseDouble(s))).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
	}
	
	public String tanOf(String s)
	{
		return String.valueOf(BigDecimal.valueOf(Math.tan(Double.parseDouble(s))).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
	}
	
	public String toBinary(String s)
	{
		return Integer.toBinaryString(Integer.parseInt(s));
	}
	
	public String toHex(String s)
	{
		return (Integer.toHexString(Integer.parseInt(s))).toUpperCase();
	}
	
	public String toVar(String s)
	{
		for(Variable v : vars)
		{
			if(s.substring(0, s.indexOf('=')).equals(v.getName()))
			{
				v.changeValue(s.substring(s.indexOf('=')+1, s.length()));
				return ("Variable '"+v.getName()+"' was changed to "+BigDecimal.valueOf(v.getValue()).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
			}
		}
		
		createVar(s);
		String name = vars.get(vars.size()-1).getName();
		double val = vars.get(vars.size()-1).getValue();
		return ("Variable '"+name+"' was set to "+BigDecimal.valueOf(val).setScale(8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
	}
	
	public void createVar(String s)
	{
		Variable v = new Variable(s);
		vars.add(v);
	}
	
	public Variable getVar(int index)
	{
		return vars.get(index);
	}
	
	public ArrayList<Variable> getVarList()
	{
		return vars;
	}
	
}
