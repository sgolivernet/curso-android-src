package net.sgoliver.android.menus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	
	private static final int SMNU_OPC1 = 31;
	private static final int SMNU_OPC2 = 32;
	
	private TextView lblMensaje;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		lblMensaje = (TextView)findViewById(R.id.LblMensaje);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//Alternativa 1
		
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
    	//Alternativa 2
        
        //menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1")
		//		.setIcon(android.R.drawable.ic_menu_preferences);
		//menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2")
		//		.setIcon(android.R.drawable.ic_menu_compass);
        
		//SubMenu smnu = menu.
		//		addSubMenu(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion3")
		//			.setIcon(android.R.drawable.ic_menu_agenda);
		//smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
		//smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");
		
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.MnuOpc1:
            lblMensaje.setText("Opcion 1 pulsada!");
            return true;
        case R.id.MnuOpc2:
        	lblMensaje.setText("Opcion 2 pulsada!");;
            return true;
        case R.id.MnuOpc3:
        	lblMensaje.setText("Opcion 3 pulsada!");;
            return true;
        case R.id.SubMnuOpc1:
        	lblMensaje.setText("Opcion 3.1 pulsada!");;
            return true;
        case R.id.SubMnuOpc2:
        	lblMensaje.setText("Opcion 3.2 pulsada!");;
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
