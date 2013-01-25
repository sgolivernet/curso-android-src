package net.sgoliver.android.fragments;

import net.sgoliver.android.fragments.FragmentListado.CorreosListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements CorreosListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentListado frgListado
			=(FragmentListado)getSupportFragmentManager()
				.findFragmentById(R.id.FrgListado);
		
		frgListado.setCorreosListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onCorreoSeleccionado(Correo c) {
		boolean hayDetalle = 
				(getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);
		
		if(hayDetalle) {
			((FragmentDetalle)getSupportFragmentManager()
				.findFragmentById(R.id.FrgDetalle)).mostrarDetalle(c.getTexto());
		}
		else {
			Intent i = new Intent(this, DetalleActivity.class);
			i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getTexto());
			startActivity(i);
		}
	}
}
