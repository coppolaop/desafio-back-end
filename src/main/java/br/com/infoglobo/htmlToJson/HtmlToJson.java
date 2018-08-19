package br.com.infoglobo.htmlToJson;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HtmlToJson {

	public JSONArray convert(String html) {
		String [] splitedArray = html.split("<", -1);
		boolean hasUl = false;
		JSONArray json = new JSONArray( );
		JSONObject auxiliarNode = new JSONObject();
		
		for(String content : splitedArray) {
			if(content.startsWith("img")) {
				String[] x = content.split("src",-1);
				String[] y = x[1].split("\"", -1);
				
				JSONObject node = new JSONObject();
				node.put("type", "image");
				node.put("content", y[1]);
				
				json.put(node);
				
			}else if(content.startsWith("ul")) {
				hasUl = true;
				auxiliarNode = new JSONObject();
				auxiliarNode.put("type", "links");
				
				json.put(auxiliarNode);
			}else if(hasUl ) {
				if(content.startsWith("a ")) {
					String[] y = content.split("\"", -1);
					auxiliarNode.accumulate("content", y[1]);
				}else if(content.startsWith("/ul")) {
					hasUl = false;
				}
			}else if(content.startsWith("p>")) {
				content = content.substring(2, content.length());//retirada do p>
				
				if(content.length() > 10) {
					content = StringEscapeUtils.unescapeHtml4(content);
					content = content.substring(3, content.length());
					
					JSONObject node = new JSONObject();
					node.put("type", "text");
					node.put("content", content);
					
					json.put(node);
				}
			}
		}
		System.out.println(json.toString());
		return json;
	}
}
