package fiskfille.transformersmod.update;

public class Update 
{
	private boolean isAvailable;
	private String version;
	private String updateLog;
	
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

	public boolean isAvailable() 
	{
		return isAvailable;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public String getUpdateLog()
	{
		return updateLog;
	}
}
