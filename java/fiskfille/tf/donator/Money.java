package fiskfille.tf.donator;

import java.util.regex.Pattern;

public class Money 
{
	private float money;
	private String moneyString;
	
	public Money(String moneyString)
	{
		this.moneyString = moneyString;
		this.money = fromString(moneyString);
	}
	
	public void setMoney(String amount)
	{
		this.moneyString = amount;
		this.money = fromString(amount);
	}
	
	private float fromString(String moneyString)
	{
		return Float.parseFloat(moneyString.replaceAll(Pattern.quote("$"), "").replaceAll(",", ""));
	}
	
	public float getMoney()
	{
		return money;
	}
	
	@Override
	public String toString()
	{
		return moneyString;
	}
}
