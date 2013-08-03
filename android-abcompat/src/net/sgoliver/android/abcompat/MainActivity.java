package net.sgoliver.android.abcompat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.action_settings:
				Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();;
				break;
			case R.id.action_search:
				Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		
		return true;
	}
}
