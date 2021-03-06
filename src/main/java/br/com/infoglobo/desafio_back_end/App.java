package br.com.infoglobo.desafio_back_end;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import br.com.infoglobo.htmlToJson.HtmlToJson;

/**
 * Hello world!
 *
 */
public class App 
{   
	static final String FILE_DIRECTORY = "C:\\Users\\Public\\Documents\\";
    static final String FILE_PATH = FILE_DIRECTORY + "autoesporte.json";
    static final String LINK = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
	
	public static void main( String[] args )
    {
    	InputStream is = null;
        BufferedReader br;
        String line;
        StringBuffer xmlFile = new StringBuffer();
        StringBuffer jsonFile = new StringBuffer();
        
    	try {
    		System.out.println("Fazendo download do aquivo XML");
			URL url = new URL(LINK);
			is = url.openStream();
	        br = new BufferedReader(new InputStreamReader(is));
	        
	        while ((line = br.readLine()) != null) {
	        	xmlFile.append(line);
	        	xmlFile.append(System.getProperty("line.separator"));
	        }
	        
	        System.out.println("Convertendo arquivo para JSON");
	        JSONObject xmlJSONObj = XML.toJSONObject(xmlFile.toString());
	        
	        JSONArray list = xmlJSONObj.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
	        HtmlToJson converter = new HtmlToJson();
	        JSONArray node = new JSONArray();
	        
	        for(int i = 0; i < list.length(); i++) {
	        	node = converter.convert(list.getJSONObject(i).getString("description"));
	        	
	        	list.getJSONObject(i).put("description", node);
	        }
	        
	        String jsonPrettyPrintString = xmlJSONObj.toString(4);
            jsonFile.append(jsonPrettyPrintString);
            
            
            System.out.println("Gravando arquivo");
            writeToFile(FILE_PATH, jsonFile);
			System.out.println("Arquivo gravado com Sucesso");
			
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
