package fiskfille.tf.common.motion;

/**
 * @author gegy1000, FiskFille
 */
public class VehicleMotion
{
    private double forwardVelocity;
    private double horizontalVelocity;
    private int nitro;
    private boolean boosting;

    private int jetRoll;
    private int landingTimer;

    public boolean isBoosting()
    {
        return boosting;
    }

    public VehicleMotion setBoosting(boolean boosting)
    {
        this.boosting = boosting;
        return this;
    }

    public double getForwardVelocity()
    {
        return forwardVelocity;
    }

    public double getHorizontalVelocity()
    {
        return horizontalVelocity;
    }

    public VehicleMotion setForwardVelocity(double vel)
    {
        forwardVelocity = vel;
        return this;
    }

    public VehicleMotion setHorizontalVelocity(double vel)
    {
        horizontalVelocity = vel;
        return this;
    }

    public VehicleMotion setNitro(int nitro)
    {
        this.nitro = nitro;
        return this;
    }

    public int getNitro()
    {
        return nitro;
    }

    public VehicleMotion setJetRoll(int roll)
    {
        jetRoll = roll;
        return this;
    }

    public int getJetRoll()
    {
        return jetRoll;
    }

    public VehicleMotion setLandingTimer(int t)
    {
        landingTimer = t;
        return this;
    }

    public int getLandingTimer()
    {
        return landingTimer;
    }
}
