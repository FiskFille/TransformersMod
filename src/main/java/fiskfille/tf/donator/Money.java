package fiskfille.tf.donator;

import java.util.regex.Pattern;

public class Money
{
    private double money;
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
    
    private double fromString(String moneyString)
    {
        return Double.parseDouble(moneyString.replaceAll(Pattern.quote("$"), "").replaceAll(",", ""));
    }
    
    public double getMoney()
    {
        return money;
    }
    
    @Override
    public String toString()
    {
        return moneyString;
    }
}
