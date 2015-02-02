package fiskfille.tf.updatechecker;

public class Update 
{
	public boolean isAvailable;
	public String version;
	public String updateLog;
	
	public Update(boolean isAvailable, String version, String update)
	{
		this.isAvailable = isAvailable;
		this.version = version;
		this.updateLog = update;
	}
	
	public Update()
	{
		this.isAvailable = false;
	}
}
