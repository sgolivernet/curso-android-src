package net.sgoliver.android.actionbartabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1Fragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, container, false);
	}
}
