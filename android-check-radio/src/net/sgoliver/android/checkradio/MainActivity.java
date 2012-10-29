package net.sgoliver.android.checkradio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private CheckBox cbMarcame;
	private TextView lblMensaje;
	private RadioGroup rgOpciones;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cbMarcame = (CheckBox)findViewById(R.id.ChkMarcame);

        cbMarcame.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	cbMarcame.setText("Checkbox marcado!");
		        }
		        else {
		        	cbMarcame.setText("Checkbox desmarcado!");
		        }
	        }
        });
        
        lblMensaje = (TextView)findViewById(R.id.LblSeleccion);
        rgOpciones = (RadioGroup)findViewById(R.id.gruporb);

        rgOpciones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	lblMensaje.setText("ID opción seleccionada: " + checkedId);
	        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
