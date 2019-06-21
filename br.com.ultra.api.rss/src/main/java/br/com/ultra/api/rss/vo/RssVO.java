package br.com.ultra.api.rss.vo;

import java.io.Serializable;

public class RssVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119872864747603605L;
	
	private String endereco;
	private String encoding;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	

}
