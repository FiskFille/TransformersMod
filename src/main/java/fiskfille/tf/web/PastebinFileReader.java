package fiskfille.tf.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class PastebinFileReader 
{
	public static String pastebinURLPrefix = "http://pastebin.com/raw.php?i=";
	
	public static List<String> readPastebinAsList(String pastebinFileName) throws MalformedURLException, IOException
	{
		return FileDownloader.downloadFileList(pastebinURLPrefix + pastebinFileName);
	}
	
	public static String readPastebin(String pastebinFileName) throws MalformedURLException, IOException
	{
		return FileDownloader.downloadFile(pastebinURLPrefix + pastebinFileName);
	}
}
