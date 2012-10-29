package net.sgoliver.android.dialogos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	private Button btnAlerta = null;
	private Button btnConfirmacion = null;
	private Button btnSeleccion = null;
	private Button btnPersonalizado = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnAlerta = (Button)findViewById(R.id.BtnAlerta);
        btnConfirmacion = (Button)findViewById(R.id.BtnConfirmacion);
        btnSeleccion = (Button)findViewById(R.id.BtnSeleccion);
        btnPersonalizado = (Button)findViewById(R.id.BtnPersonalizado);
        
        btnAlerta.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoAlerta dialogo = new DialogoAlerta();
			    dialogo.show(fragmentManager, "tagAlerta");
			}
		});
        
        btnConfirmacion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoConfirmacion dialogo = new DialogoConfirmacion();
			    dialogo.show(fragmentManager, "tagConfirmacion");
			}
		});
        
        btnSeleccion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoSeleccion dialogo = new DialogoSeleccion();
			    dialogo.show(fragmentManager, "tagSeleccion");
			}
		});
        
        btnPersonalizado.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
			    DialogoPersonalizado dialogo = new DialogoPersonalizado();
			    dialogo.show(fragmentManager, "tagPersonalizado");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
