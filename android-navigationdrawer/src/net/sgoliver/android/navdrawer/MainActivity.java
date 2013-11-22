package net.sgoliver.android.navdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
	
	private String[] opcionesMenu;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    
    private CharSequence tituloSeccion;  
    private CharSequence tituloApp;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		opcionesMenu = new String[] {"Opción 1", "Opción 2", "Opción 3"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(
        		getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opcionesMenu));
        
		drawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Fragment fragment = null;

				switch (position) {
					case 0:
						fragment = new Fragment1();
						break;
					case 1:
						fragment = new Fragment2();
						break;
					case 2:
						fragment = new Fragment3();
						break;
				}

				FragmentManager fragmentManager = 
						getSupportFragmentManager();
				
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.commit();

				drawerList.setItemChecked(position, true);

				tituloSeccion = opcionesMenu[position];
				getSupportActionBar().setTitle(tituloSeccion);

				drawerLayout.closeDrawer(drawerList);
			}
		});
		
		tituloSeccion = getTitle();
		tituloApp = getTitle();
		
		drawerToggle = new ActionBarDrawerToggle(this, 
				drawerLayout,
				R.drawable.ic_navigation_drawer, 
				R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(tituloSeccion);
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(tituloApp);
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
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
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		boolean menuAbierto = drawerLayout.isDrawerOpen(drawerList);
		
		if(menuAbierto)
			menu.findItem(R.id.action_search).setVisible(false);
		else
			menu.findItem(R.id.action_search).setVisible(true);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
}
