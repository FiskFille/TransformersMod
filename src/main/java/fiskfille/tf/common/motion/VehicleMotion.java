package fiskfille.tf.common.motion;

/**
 * @author gegy1000
 */
public class VehicleMotion
{
    private double forwardVelocity;
    private double horizontalVelocity;
    private int nitro;
    private boolean boosting;
    
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
        this.forwardVelocity = vel;

        return this;
    }
    
    public VehicleMotion setHorizontalVelocity(double vel)
    {
        this.horizontalVelocity = vel;

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
}
