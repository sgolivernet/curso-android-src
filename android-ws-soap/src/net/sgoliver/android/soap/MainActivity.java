package net.sgoliver.android.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText txtNombre;
	private EditText txtTelefono;
	private TextView txtResultado;
	private Button btnEnviar;
	private Button btnEnviar2;
	private Button btnConsultar;
	private ListView lstClientes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        btnEnviar2 = (Button)findViewById(R.id.btnEnviar2);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);
        lstClientes = (ListView)findViewById(R.id.lstClientes);
        
        btnEnviar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {		
				TareaWSInsercion1 tarea = new TareaWSInsercion1();
		        tarea.execute();
			}
		});
        
        btnEnviar2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TareaWSInsercion2 tarea = new TareaWSInsercion2();
		        tarea.execute();		
			}
		});
        
        btnConsultar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TareaWSConsulta tarea = new TareaWSConsulta();
		        tarea.execute();		
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Tarea Asíncrona para llamar al WS de consulta en segundo plano
	private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
		
		private Cliente[] listaClientes;
		 
	    protected Boolean doInBackground(String... params) {
	    	
	    	boolean resul = true;
	 
	    	final String NAMESPACE = "http://sgoliver.net/";
			final String URL="http://10.0.2.2:1473/ServicioClientes.asmx";
			final String METHOD_NAME = "ListadoClientes";
			final String SOAP_ACTION = "http://sgoliver.net/ListadoClientes";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try 
			{
				transporte.call(SOAP_ACTION, envelope);

				SoapObject resSoap =(SoapObject)envelope.getResponse();
				
				listaClientes = new Cliente[resSoap.getPropertyCount()];
				
				for (int i = 0; i < listaClientes.length; i++) 
				{
			           SoapObject ic = (SoapObject)resSoap.getProperty(i);
			            
			           Cliente cli = new Cliente();
			           cli.id = Integer.parseInt(ic.getProperty(0).toString());
			           cli.nombre = ic.getProperty(1).toString();
			           cli.telefono = Integer.parseInt(ic.getProperty(2).toString());
			            
			           listaClientes[i] = cli;
			    }
			} 
			catch (Exception e) 
			{
				resul = false;
			} 
	 
	        return resul;
	    }
	    
	    protected void onPostExecute(Boolean result) {
	    	
	    	if (result)
	    	{
		    	//Rellenamos la lista con los nombres de los clientes
				final String[] datos = new String[listaClientes.length];
				 
				for(int i=0; i<listaClientes.length; i++)
					 datos[i] = listaClientes[i].nombre;
					 
				ArrayAdapter<String> adaptador =
				    new ArrayAdapter<String>(MainActivity.this,
				        android.R.layout.simple_list_item_1, datos);
				 
				lstClientes.setAdapter(adaptador);
	    	}
	    	else
	    	{
	    		txtResultado.setText("Error!");
	    	}
	    }
	}
	
	//Tarea Asíncrona para llamar al WS de consulta en segundo plano
	private class TareaWSInsercion1 extends AsyncTask<String,Integer,Boolean> {
		
		private Cliente[] listaClientes;
		 
	    protected Boolean doInBackground(String... params) {
	    	
	    	boolean resul = true;
	 
	    	final String NAMESPACE = "http://sgoliver.net/";
			final String URL="http://10.0.2.2:1473/ServicioClientes.asmx";
			final String METHOD_NAME = "NuevoClienteSimple";
			final String SOAP_ACTION = "http://sgoliver.net/NuevoClienteSimple";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			request.addProperty("nombre", txtNombre.getText().toString()); 
			request.addProperty("telefono", txtTelefono.getText().toString()); 

			SoapSerializationEnvelope envelope = 
					new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = true; 

			envelope.setOutputSoapObject(request);

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try 
			{
				transporte.call(SOAP_ACTION, envelope);

				SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
				String res = resultado_xml.toString();
				
				if(!res.equals("1"))
					resul = false;
			} 
			catch (Exception e) 
			{
				resul = false;
			}  
	 
	        return resul;
	    }
	    
	    protected void onPostExecute(Boolean result) {
	    	
	    	if (result)
	    		txtResultado.setText("Insertado OK");
	    	else
	    		txtResultado.setText("Error!");
	    }
	}
	
	//Tarea Asíncrona para llamar al WS de consulta en segundo plano
	private class TareaWSInsercion2 extends AsyncTask<String,Integer,Boolean> {
		
		private Cliente[] listaClientes;
		 
	    protected Boolean doInBackground(String... params) {
	    	
	    	boolean resul = true;
	 
	    	final String NAMESPACE = "http://sgoliver.net/";
			final String URL="http://10.0.2.2:1473/ServicioClientes.asmx";
			final String METHOD_NAME = "NuevoClienteObjeto";
			final String SOAP_ACTION = "http://sgoliver.net/NuevoClienteObjeto";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			Cliente cli = new Cliente();
			cli.nombre = txtNombre.getText().toString();
			cli.telefono = Integer.parseInt(txtTelefono.getText().toString());
			
			PropertyInfo pi = new PropertyInfo();
	        pi.setName("cliente");
	        pi.setValue(cli);
	        pi.setType(cli.getClass());
	        
			request.addProperty(pi);  

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; 

			envelope.setOutputSoapObject(request);
			
			envelope.addMapping(NAMESPACE, "Cliente", cli.getClass());

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try 
			{
				transporte.call(SOAP_ACTION, envelope);

				SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
				String res = resultado_xml.toString();
				
				if(!res.equals("1"))
					resul = false;
			} 
			catch (Exception e) 
			{
				resul = false;
			}  
	 
	        return resul;
	    }
	    
	    protected void onPostExecute(Boolean result) {
	    	
	    	if (result)
	    		txtResultado.setText("Insertado OK");
	    	else
	    		txtResultado.setText("Error!");
	    }
	}
}
