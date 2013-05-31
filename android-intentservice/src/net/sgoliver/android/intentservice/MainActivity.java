package net.sgoliver.android.intentservice;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btnEjecutar;
	private ProgressBar pbarProgreso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);
        
        btnEjecutar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent msgIntent = new Intent(MainActivity.this, MiIntentService.class);
				msgIntent.putExtra("iteraciones", 10);
				startService(msgIntent);
			}
		});
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(MiIntentService.ACTION_PROGRESO);
        filter.addAction(MiIntentService.ACTION_FIN);
        ProgressReceiver rcv = new ProgressReceiver();
        registerReceiver(rcv, filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ProgressReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(MiIntentService.ACTION_PROGRESO)) {
				int prog = intent.getIntExtra("progreso", 0);
				pbarProgreso.setProgress(prog);
			}
			else if(intent.getAction().equals(MiIntentService.ACTION_FIN)) {
				Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
