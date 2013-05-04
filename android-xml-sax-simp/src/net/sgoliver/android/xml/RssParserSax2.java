package net.sgoliver.android.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

public class RssParserSax2 
{
	private URL rssUrl;
	private Noticia noticiaActual;
	
	public RssParserSax2(String url)
	{
		try 
		{
            this.rssUrl = new URL(url);
        } 
		catch (MalformedURLException e) 
		{
            throw new RuntimeException(e);
        }
	}

	public List<Noticia> parse() 
	{
        final List<Noticia> noticias = new ArrayList<Noticia>();
        
        RootElement root = new RootElement("rss");
        Element channel = root.getChild("channel");
        Element item = channel.getChild("item");
        
        item.setStartElementListener(new StartElementListener(){
            public void start(Attributes attrs) {
                noticiaActual = new Noticia();
            }
        });
        
        item.setEndElementListener(new EndElementListener(){
            public void end() {
                noticias.add(noticiaActual);
            }
        });
        
        item.getChild("title").setEndTextElementListener(
        		new EndTextElementListener(){
		            public void end(String body) {
		            	noticiaActual.setTitulo(body);
		            }
        });
        
        item.getChild("link").setEndTextElementListener(
        		new EndTextElementListener(){
		            public void end(String body) {
		            	noticiaActual.setLink(body);
		            }
        });
        
        item.getChild("description").setEndTextElementListener(
        		new EndTextElementListener(){
		            public void end(String body) {
		            	noticiaActual.setDescripcion(body);
		            }
        });
        
        item.getChild("guid").setEndTextElementListener(
        		new EndTextElementListener(){
		            public void end(String body) {
		            	noticiaActual.setGuid(body);
		            }
        });
        
        item.getChild("pubDate").setEndTextElementListener(
        		new EndTextElementListener(){
		            public void end(String body) {
		            	noticiaActual.setFecha(body);
		            }
        });
        
        try 
        {
            Xml.parse(this.getInputStream(), 
            		  Xml.Encoding.UTF_8, 
            		  root.getContentHandler());
        } 
        catch (Exception e) 
        {
            throw new RuntimeException(e);
        }
        
        return noticias;
    }

	private InputStream getInputStream() 
	{
        try 
        {
            return rssUrl.openConnection().getInputStream();
        } 
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
    }
}
