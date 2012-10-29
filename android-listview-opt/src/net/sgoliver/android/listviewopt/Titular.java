package net.sgoliver.android.listviewopt;

public class Titular 
{
	private String titulo;
	private String subtitulo;

	public Titular(String tit, String sub){
		titulo = tit;
		subtitulo = sub;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getSubtitulo(){
		return subtitulo;
	}
}
