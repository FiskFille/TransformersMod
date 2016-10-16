package fiskfille.tf.client.tutorial;

import fiskfille.tf.client.tutorial.ticker.TutorialTicker;
import fiskfille.tf.client.tutorial.ticker.TutorialTickerCar;
import fiskfille.tf.client.tutorial.ticker.TutorialTickerJet;
import fiskfille.tf.client.tutorial.ticker.TutorialTickerTank;
import fiskfille.tf.client.tutorial.ticker.TutorialTickerTruck;

public enum EnumTutorialType
{
    JET(new TutorialTickerJet()),
    TANK(new TutorialTickerTank()),
    CAR(new TutorialTickerCar()),
    TRUCK(new TutorialTickerTruck());

    public TutorialTicker ticker;

    EnumTutorialType(TutorialTicker ticker)
    {
        this.ticker = ticker;
    }

    public String toString()
    {
        return "tutorial(" + name() + ")";
    }

    public static EnumTutorialType fromName(String name)
    {
        for (EnumTutorialType type : values())
        {
            if (type.name().equals(name))
            {
                return type;
            }
        }

        return null;
    }
}
