package br.com.ultra.api.rss.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.json.JSONObject;
import org.json.XML;

public class Test {

	public static void main(String[] args) {
		try {
				String proxy = "proxy.ultra.corp";
				String port = "9090";
				
			    Properties systemProperties = System.getProperties();
				systemProperties.setProperty("http.proxyHost",proxy);
				systemProperties.setProperty("http.proxyPort",port);
				
				URL url = new URL("http://rss.uol.com.br/feed/tecnologia.xml");			
		        
		        HttpURLConnection httpSource= (HttpURLConnection)url.openConnection();

		        httpSource.setConnectTimeout(5 * 1000);
		        httpSource.setReadTimeout(5 * 1000);
		        httpSource.connect();
			     
		        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("iso-8859-1")));

		        String line;
		        StringBuilder content = new StringBuilder();
		        while ((line = reader.readLine()) != null) {
		            content.append(line + "\n");
		        }
		        reader.close();
		        
		        System.out.println(content.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
