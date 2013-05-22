package com.chorus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.caelum.vraptor.util.StringUtils;

public class ShortUrl {
	
	/***
	 *Classe para detecção de Urls no texto e encurtamento das mesmas
	 *@author Paulo Silva
	 *@data 09/05/2013 
	 *@param String texto
	 *@return String texto
	 */
	
	public String envioLink(String link) {
		
		String shortLink = "";
		
		if ("".equals(link)){
			System.out.println("Link não pode ser vazio!");
		}
		
	      try {
	    	  
	    	  	URL url = new URL("http://migre.me/api.txt?url=" + link);
				URLConnection con = url.openConnection();
				con.setReadTimeout( 2000 ); //2 segundos
	            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	            String line;

	            while ((line = reader.readLine()) != null) {
	                shortLink = "<a href='" + line + "'>" + line + "</a>";
	            }
	            reader.close();
	 
	      } catch (MalformedURLException e){
	         System.out.println("Erro ao criar URL. Formato inválido.");
	      } catch (IOException e2) {
	         System.out.println("Erro ao acessar URL.");
	      }
	      
		return shortLink;
	}
	
	public ArrayList<String> retornaUrls(String texto){
		
		ArrayList<String> urlsEncurtar = new ArrayList<String>();
		
		String palavra = "http";
		String [] arrayStrings = texto.split(" ");
		
		for (String string : arrayStrings) {
			if (string.contains(palavra)){
				urlsEncurtar.add(string.trim());
			}
		}
		return urlsEncurtar;
	}
	
	public String formataTexto(String texto) throws Exception{
		
		if(texto == null || texto == "")
			throw new Exception("Texto é Requerido");
		
		/*RETORNANDO TODAS AS URLS NO TEXTO*/
		ArrayList<String> urlsGrande = this.retornaUrls(texto);
		
		/*CRIANDO UM MAPA COM AS URLS MAPA(URL_GRANDE,URL_PEQUENA)*/
		HashMap<String, String> mapaUrls = new HashMap<String, String>();
		
		for (String urlGrande : urlsGrande) {
			mapaUrls.put(urlGrande, this.envioLink(urlGrande));
		}
		
		/*FAZENDO O REPLACE NO TEXTO DAS URLS_GRANDES PELAS URLS_PEQUENAS*/
		
		String [] arrayPalavrasTexto = texto.split(" ");
		
		for (String palavra : arrayPalavrasTexto) {
			if (mapaUrls.get(palavra) != null){
				texto = texto.replace(palavra,mapaUrls.get(palavra));
			}
		}
		
		return texto;
	}
	
	
	public static void main(String[] args) {
		ShortUrl url = new ShortUrl();
		
		String texto = "http://www.d7d.com.br";
		
		try {
			texto = url.formataTexto(texto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(texto);
		
		
	}
	
}
