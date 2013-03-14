package net.sgoliver.android.actionbartabs;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.util.Log;

public class MiTabListener implements ActionBar.TabListener {

	private Fragment fragment;
	
	public MiTabListener(Fragment fg)
	{
		this.fragment = fg;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.i("ActionBar", tab.getText() + " reseleccionada.");
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Log.i("ActionBar", tab.getText() + " seleccionada.");
		ft.replace(R.id.contenedor, fragment);
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.i("ActionBar", tab.getText() + " deseleccionada.");
		ft.remove(fragment);
	}
}
