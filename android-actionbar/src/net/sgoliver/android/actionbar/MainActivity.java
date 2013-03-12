package net.sgoliver.android.actionbar;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	        case R.id.menu_new:
	            Log.i("ActionBar", "Nuevo!");
	            return true;
	        case R.id.menu_save:
	            Log.i("ActionBar", "Guardar!");;
	            return true;
	        case R.id.menu_settings:
	            Log.i("ActionBar", "Settings!");;
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}
}
