package net.sgoliver.android.preferences1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnGuardar;
	private Button btnCargar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnGuardar = (Button)findViewById(R.id.BtnGuardar);
		btnCargar = (Button)findViewById(R.id.BtnCargar);
		
		btnGuardar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Guardamos las preferencias
				SharedPreferences prefs =
					     getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("email", "modificado@email.com");
				editor.putString("nombre", "Prueba");
				editor.commit();
			}
		});
		
		btnCargar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Recuperamos las preferencias
				SharedPreferences prefs =
					     getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				
				String correo = prefs.getString("email", "por_defecto@email.com");
				String nombre = prefs.getString("nombre", "nombre_por_defecto");
				String otra = prefs.getString("otra", "otra_por_defecto");
				
				Log.i("Preferences", "Correo: " + correo);
				Log.i("Preferences", "Nombre: " + nombre);
				Log.i("Preferences", "Otra: " + otra);
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
