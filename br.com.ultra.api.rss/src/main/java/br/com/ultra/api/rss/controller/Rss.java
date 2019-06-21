package br.com.ultra.api.rss.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultra.api.rss.vo.RssVO;

@RestController
@CrossOrigin
public class Rss {
	
	
	public static final  int PRETTY_PRINT_INDENT_FACTOR = 4;
	
	@PostMapping(value="/rss/xml/to/json")
	public String listarAcessos(@RequestBody RssVO rss) {
		
		String jsonFormat = "";
		try {
			
			JSONObject xmlJSONObj = XML.toJSONObject(getXML(rss));
			jsonFormat = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
	        
	    } catch (JSONException je) {
	        System.out.println(je.toString());
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonFormat;
	}
	
	
	public String getXML(RssVO rss) throws IOException {
		
		String encode = StringUtils.isEmpty(rss.getEncoding()) ? "iso-8859-1" :  rss.getEncoding();
	  
		URL url = new URL(rss.getEndereco());
		HttpURLConnection httpSource= (HttpURLConnection)url.openConnection();
		  String proxy = "proxy.ultra.corp";
		    String port = "9090";
		    Properties systemProperties = System.getProperties();
			systemProperties.setProperty("http.proxyHost",proxy);
			systemProperties.setProperty("http.proxyPort",port);
        httpSource.setConnectTimeout(5 * 1000);
        httpSource.setReadTimeout(5 * 1000);
        httpSource.connect();
	    
     
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(encode)));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line + "\n");
        }
        reader.close();
        		
	    return content.toString();
	}

}
