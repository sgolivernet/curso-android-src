package net.sgoliver.android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetalle extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_detalle, container, false);
	}
	
	public void mostrarDetalle(String texto) {
		TextView txtDetalle = 
				(TextView)getView().findViewById(R.id.TxtDetalle);
		
		txtDetalle.setText(texto);
	}
}
