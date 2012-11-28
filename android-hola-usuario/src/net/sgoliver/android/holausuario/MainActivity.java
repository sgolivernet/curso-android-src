package net.sgoliver.android.holausuario;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Obtenemos una referencia a los controles de la interfaz
        final EditText txtNombre = (EditText)findViewById(R.id.TxtNombre);
        final Button btnHola = (Button)findViewById(R.id.BtnHola);
        
        //Implementamos el evento “click” del botón
        btnHola.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Creamos el Intent
            	Intent intent = new Intent(MainActivity.this, FrmSaludo.class);
            	
            	//Creamos la información a pasar entre actividades
            	Bundle b = new Bundle(); 
            	b.putString("NOMBRE", txtNombre.getText().toString());
            	
            	//Añadimos la información al intent
            	intent.putExtras(b);
            	
            	//Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
