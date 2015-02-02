package fiskfille.tf.misc;

public class TransformedPlayer 
{
	private double forwardVelocity;
	private double horizontalVelocity;
	private int nitro;
	private boolean boosting;
	
	private float roll;
	private float pitch;
	private float yaw;
	
	public TransformedPlayer(int nitro, double forwardVelocity, double horizontalVelocity) 
	{
		this.forwardVelocity = forwardVelocity;
		this.horizontalVelocity = horizontalVelocity;
		
		this.nitro = nitro;
		this.boosting = false;
	}
	
	public boolean isBoosting()
	{
		return boosting;
	}
	
	public void setBoosting(boolean boosting)
	{
		this.boosting = boosting;
	}
	
	public double getForwardVelocity()
	{
		return forwardVelocity;
	}
	
	public double getHorizontalVelocity()
	{
		return horizontalVelocity;
	}
	
	public int getNitro()
	{
		return nitro;
	}

	public void setForwardVelocity(double vel)
	{
		this.forwardVelocity = vel;
	}
	
	public void setHorizontalVelocity(double vel)
	{
		this.horizontalVelocity = vel;
	}
	
	public void setNitro(int nitro)
	{
		this.nitro = nitro;
	}
	
	public void setRoll(float roll)
	{
		this.roll = roll;
	}
	
	public float getRoll()
	{
		return roll;
	}

	public float getPitch()
	{
		return pitch;
	}
	
	public void setPitch(float pitch)
	{
		this.pitch = pitch;
	}
	
	public float getYaw()
	{
		return yaw;
	}
	
	public void setYaw(float yaw)
	{
		this.yaw = yaw;
	}
}
