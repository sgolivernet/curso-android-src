package net.sgoliver.android.gcm;

import com.google.android.gcm.GCMRegistrar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Button btnRegistrar;
	private Button btnDesRegistrar;
	private Button btnGuardarUsuario;
	private EditText txtUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnRegistrar = (Button)findViewById(R.id.btnRegGcm);
        btnDesRegistrar = (Button)findViewById(R.id.btnDesRegGcm);
        btnGuardarUsuario = (Button)findViewById(R.id.btnAceptar);
        txtUsuario = (EditText)findViewById(R.id.txtNombreUsuario);
        
        //Comprobamos si está todo en orden para utilizar GCM
        GCMRegistrar.checkDevice(MainActivity.this);
        GCMRegistrar.checkManifest(MainActivity.this);
        
        btnGuardarUsuario.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences prefs =
					     getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString("usuario", txtUsuario.getText().toString());
				editor.commit();
			}
		});
        
        btnRegistrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				//Si no estamos registrados --> Nos registramos en GCM
		        final String regId = GCMRegistrar.getRegistrationId(MainActivity.this);
		        if (regId.equals("")) {
		        	GCMRegistrar.register(MainActivity.this, "224338875065"); //Sender ID
		        } else {
		        	Log.v("GCMTest", "Ya registrado");
		        }
			}
		});
        
        btnDesRegistrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Si estamos registrados --> Nos des-registramos en GCM
		        final String regId = GCMRegistrar.getRegistrationId(MainActivity.this);
		        if (!regId.equals("")) {
		        	GCMRegistrar.unregister(MainActivity.this);
		        } else {
		        	Log.v("GCMTest", "Ya des-registrado");
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
