package fiskfille.tf.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileDownloader
{
    public static List<String> downloadFileList(String urlString)
            throws MalformedURLException, IOException
    {
        List<String> text = new ArrayList<String>();

        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String currentLine;

        while ((currentLine = reader.readLine()) != null)
        {
            text.add(currentLine);
        }

        reader.close();

        return text;
    }

    public static String downloadFile(String urlString)
            throws MalformedURLException, IOException
    {
        String text = "";

        URL url = new URL(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String currentLine;

        while ((currentLine = reader.readLine()) != null)
        {
            text += currentLine + "\r\n";
        }

        reader.close();

        return text;
    }
}
