package net.sgoliver.android.holausuario;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FrmSaludo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saludo);
		
		//Localizar los controles
        TextView txtSaludo = (TextView)findViewById(R.id.TxtSaludo);
        
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        
        //Construimos el mensaje a mostrar
        txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));
	}
}
