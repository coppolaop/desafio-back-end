package br.com.infoglobo.desafio_back_end;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{   
    public static void main( String[] args )
    {
    	InputStream is = null;
        BufferedReader br;
        String line;
        StringBuffer xmlFile = new StringBuffer();
        
    	try {
			URL url = new URL("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
			is = url.openStream();
	        br = new BufferedReader(new InputStreamReader(is));
	        
	        while ((line = br.readLine()) != null) {
	        	xmlFile.append(line);
	        	xmlFile.append(System.getProperty("line.separator"));
	        }
	        
	        writeToFile("C:\\Users\\Public\\Documents\\test.xml",xmlFile);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    
    public static void writeToFile(String pFilename, StringBuffer pData) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));
        out.write(pData.toString());
        out.flush();
        out.close();
    }
}
