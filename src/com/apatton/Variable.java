package com.apatton;

public class Variable {

	private String name;
	private double value;
	
	public Variable(String s)
	{
		try
		{
			name = s.substring(0, s.indexOf('='));
			value = Double.parseDouble(s.substring(s.indexOf('=')+1, s.length()));
			
			if(isIllegal(name))
			{
				throw new IllegalArgumentException();
			}
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Argument "+s+" is not a valid set argument.");
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public double changeValue(String newVal)
	{
		value = Double.parseDouble(newVal);
		return value;
	}
	
	public boolean isIllegal(String nameArgs)
	{
		return( nameArgs.equals("pi") || nameArgs.equals("e") || nameArgs.equals("x") || nameArgs.equals("exit")
				|| nameArgs.equals("clear") || nameArgs.equals("help") || nameArgs.equals("ans"));
	}
}
