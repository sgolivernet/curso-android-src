package net.sgoliver.android.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnEscribirXml = null;
	private Button btnEscribirXmlPull = null;
	private Button btnLeerXmlDesdeMem = null;
	private Button btnLeerXmlDesdeXml = null;
	private Button btnLeerXmlDesdeRaw = null;
	private Button btnLeerXmlDesdeAssets = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnEscribirXml = (Button)findViewById(R.id.btnEscribirXml);
        btnEscribirXmlPull = (Button)findViewById(R.id.btnEscribirXmlPull);
        btnLeerXmlDesdeMem = (Button)findViewById(R.id.btnLeerDesdeMemoria);
        btnLeerXmlDesdeXml = (Button)findViewById(R.id.btnLeerDesdeXml);
        btnLeerXmlDesdeRaw = (Button)findViewById(R.id.btnLeerDesdeRaw);
        btnLeerXmlDesdeAssets = (Button)findViewById(R.id.btnLeerDesdeAssets);
        
        btnEscribirXml.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				try
				{
					//Creamos un fichero en la memoria interna
					OutputStreamWriter fout =
						new OutputStreamWriter(
							openFileOutput("prueba.xml", Context.MODE_PRIVATE));

					StringBuilder sb = new StringBuilder();
					
					//Construimos el XML
					sb.append("<usuario>");
					sb.append("<nombre>" + "Usuario1" + "</nombre>");
					sb.append("<apellidos>" + "ApellidosUsuario1" + "</apellidos>");
					sb.append("</usuario>");
					
					//Escribimos el resultado a un fichero
					fout.write(sb.toString());
					fout.close();
					
					Log.i("XmlTips", "Fichero XML creado correctamente.");
				}
				catch (Exception ex)
				{
					Log.e("XmlTips", "Error al escribir fichero XML.");
				}
			}
		});
        
        btnEscribirXmlPull.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				
				try {
					//Creamos el serializer
					XmlSerializer ser = Xml.newSerializer();
					
					//Creamos un fichero en memoria interna
					OutputStreamWriter fout =
							new OutputStreamWriter(
								openFileOutput("prueba_pull.xml", 
										Context.MODE_PRIVATE));
					
					//Asignamos el resultado del serializer al fichero
					ser.setOutput(fout);

					//Construimos el XML
					ser.startTag("", "usuario");

					ser.startTag("", "nombre");
					ser.text("Usuario1");
					ser.endTag("", "nombre");
					
					ser.startTag("", "apellidos");
					ser.text("ApellidosUsuario1");
					ser.endTag("", "apellidos");
					
					ser.endTag("", "usuario");
					
					ser.endDocument();
					
					fout.close();
					
					Log.i("XmlTips", "Fichero XML creado correctamente.");
				} 
				catch (Exception e) 
				{
					Log.e("XmlTips", "Error al escribir fichero XML.");
				}
			}
		});
        
        btnLeerXmlDesdeMem.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				try
				{
					FileInputStream fil = openFileInput("prueba.xml");

					//DOM (Por ejemplo)
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document dom = builder.parse(fil);
		            
		            //A partir de aquí se trataría el árbol DOM como siempre.
		            //Por ejemplo:
		            Element root = dom.getDocumentElement();
		            
		            //...
		            
					Log.i("XmlTips", "Fichero XML leido correctamente.");
				}
				catch (Exception ex)
				{
					Log.e("XmlTips", "Error al leer fichero XML.");
				}
			}
		});
        
        btnLeerXmlDesdeXml.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				try
				{
					XmlResourceParser xrp = getResources().getXml(R.xml.prueba);

					//A partir de aquí utilizamos la variable 'xrp' como 
					//cualquier otro parser de tipo XmlPullParser. Por ejemplo:
					
					int evento = xrp.getEventType();
					
					if(evento == XmlPullParser.START_DOCUMENT)
						Log.i("XmlTips", "Inicio del documento");
					
					//...
		            
					Log.i("XmlTips", "Fichero XML leido correctamente.");
				}
				catch (Exception ex)
				{
					Log.e("XmlTips", "Error al leer fichero XML.");
				}
			}
		});
        
        btnLeerXmlDesdeRaw.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				try
				{
					InputStream is = getResources().openRawResource(R.raw.prueba);

					//DOM (Por ejemplo)
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document dom = builder.parse(is);
		            
		            //A partir de aquí se trataría el árbol DOM como siempre.
		            //Por ejemplo:
		            Element root = dom.getDocumentElement();
		            
		            //...
		            
					Log.i("XmlTips", "Fichero XML leido correctamente.");
				}
				catch (Exception ex)
				{
					Log.e("XmlTips", "Error al leer fichero XML.");
				}
			}
		});
        
        btnLeerXmlDesdeAssets.setOnClickListener(new OnClickListener() {	
			public void onClick(View arg0) {
				try {
					AssetManager assetMan = getAssets();
					InputStream is = assetMan.open("prueba_asset.xml");
					
					//DOM (Por ejemplo)
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document dom = builder.parse(is);
		            
		            //A partir de aquí se trataría el árbol DOM como siempre.
		            //Por ejemplo:
		            Element root = dom.getDocumentElement();
		            
		            //...
					
					Log.i("XmlTips", "Fichero XML leido correctamente.");
				}
				catch(Exception ex)
				{
					Log.e("XmlTips", "Error al leer fichero XML.");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
