package net.sgoliver.android.xml;

public class Noticia {
    private String titulo;
    private String link;
    private String descripcion;
    private String guid;
    private String fecha;
 
    public String getTitulo() {
        return titulo;
    }
 
    public String getLink() {
        return link;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public String getGuid() {
        return guid;
    }
 
    public String getFecha() {
        return fecha;
    }
 
    public void setTitulo(String t) {
        titulo = t;
    }
 
    public void setLink(String l) {
        link = l;
    }
 
    public void setDescripcion(String d) {
        descripcion = d;
    }
 
    public void setGuid(String g) {
        guid = g;
    }
 
    public void setFecha(String f) {
        fecha = f;
    }
}
