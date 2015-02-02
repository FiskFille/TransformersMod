package fiskfille.tf.updatechecker;

import java.util.List;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.pastebin.PastebinFileReader;

public class UpdateChecker extends Thread
{
	public Update update;
	
	public void run()
	{
		boolean isNewUpdateAvailable = false;

		try 
		{
			List<String> text = PastebinFileReader.readFile("hBG80bPW");

			String version = text.get(0).split("\\:")[1];
			Version newestVersion = new Version(version);
			Version currentVersion = new Version(TransformersMod.version);

			if(newestVersion.compareTo(currentVersion) == 1)
			{
				isNewUpdateAvailable = true;
				update = new Update(isNewUpdateAvailable, version, text.get(1).split("\\:")[1]);
			}
			else
			{
				isNewUpdateAvailable = false;
			}
		} 
		catch (Exception e) 
		{
			System.err.println("[Transformers Mod] Failed to read mod version! Please notify FiskFille or gegy1000!");
			e.printStackTrace();
		}
		
		if(update == null)
		{
			update = new Update();
		}
		
		TransformersMod.latestUpdate = update;
	}
	
	public void handleUpdates()
	{
		this.start();
	}
}
