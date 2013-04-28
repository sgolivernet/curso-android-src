package net.sgoliver.android.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnEscribirFichero = null;
	private Button btnLeerFichero = null;
	private Button btnEscribirSD = null;
	private Button btnLeerSD = null;
	private Button btnLeerRaw = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnEscribirFichero = (Button)findViewById(R.id.BtnEscribirFichero);
        btnLeerFichero = (Button)findViewById(R.id.BtnLeerFichero);
        btnEscribirSD = (Button)findViewById(R.id.BtnEscribirSD);
        btnLeerSD = (Button)findViewById(R.id.BtnLeerSD);
        btnLeerRaw = (Button)findViewById(R.id.BtnLeerRaw);
        
        btnEscribirFichero.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{	
				try
				{
					OutputStreamWriter fout =
						new OutputStreamWriter(
							openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));

					fout.write("Texto de prueba.");
					fout.close();
					
					Log.i("Ficheros", "Fichero creado!");
				}
				catch (Exception ex)
				{
					Log.e("Ficheros", "Error al escribir fichero a memoria interna");
				}
			}
		});
        
        btnLeerFichero.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{	
				try
				{
					BufferedReader fin = 
						new BufferedReader(
							new InputStreamReader(
								openFileInput("prueba_int.txt")));

					String texto = fin.readLine();
					fin.close();
					
					Log.i("Ficheros", "Fichero leido!");
					Log.i("Ficheros", "Texto: " + texto);
				}
				catch (Exception ex)
				{
					Log.e("Ficheros", "Error al leer fichero desde memoria interna");
				}
			}
		});
        
        btnLeerRaw.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{
				String linea = "";
				
				try
				{
					InputStream fraw = 
						getResources().openRawResource(R.raw.prueba_raw);
				
					BufferedReader brin = 
						new BufferedReader(new InputStreamReader(fraw));
					
					linea = brin.readLine();
					fraw.close();
					
					Log.i("Ficheros", "Fichero RAW leido!");
					Log.i("Ficheros", "Texto: " + linea);
				}
				catch (Exception ex)
				{
					Log.e("Ficheros", "Error al leer fichero desde recurso raw");;
				}
			}
		});
        
        btnEscribirSD.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{	
				boolean sdDisponible = false;
				boolean sdAccesoEscritura = false;
				
				//Comprobamos el estado de la memoria externa (tarjeta SD)
				String estado = Environment.getExternalStorageState();

				if (estado.equals(Environment.MEDIA_MOUNTED))
				{
					sdDisponible = true;
					sdAccesoEscritura = true;
				} 
				else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
				{
					sdDisponible = true;
					sdAccesoEscritura = false;
				} 
				else 
				{
					sdDisponible = false;
					sdAccesoEscritura = false;
				}
				
				//Si la memoria externa está disponible y se puede escribir
				if (sdDisponible && sdAccesoEscritura)
				{
					try
					{
						File ruta_sd_global = Environment.getExternalStorageDirectory();
						//File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
						
						File f = new File(ruta_sd_global.getAbsolutePath(), "prueba_sd.txt");
						
						OutputStreamWriter fout = 
							new OutputStreamWriter(
									new FileOutputStream(f));
						
						fout.write("Texto de prueba.");
						fout.close();
						
						Log.i("Ficheros", "Fichero SD creado!");
					}
					catch (Exception ex)
					{
						Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
					}
				}
			}
		});
        
        btnLeerSD.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{	
				try
				{
					File ruta_sd_global = Environment.getExternalStorageDirectory();
					
					File f = new File(ruta_sd_global.getAbsolutePath(), "prueba_sd.txt");
					
					BufferedReader fin =
						new BufferedReader(
								new InputStreamReader(
										new FileInputStream(f)));
					
					String texto = fin.readLine();
					fin.close();
					
					Log.i("Ficheros", "Fichero SD leido!");
					Log.i("Ficheros", "Texto: " + texto);
				}
				catch (Exception ex)
				{
					Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
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
