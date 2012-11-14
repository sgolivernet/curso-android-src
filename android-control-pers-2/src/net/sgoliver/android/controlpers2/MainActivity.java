package net.sgoliver.android.controlpers2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private ControlLogin ctlLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ctlLogin = (ControlLogin)findViewById(R.id.CtlLogin);
        
        ctlLogin.setOnLoginListener(new OnLoginListener() 
        {
			public void onLogin(String usuario, String password) 
			{
				//Validamos el usuario y la contraseña
				if (usuario.equals("demo") && password.equals("demo"))
					ctlLogin.setMensaje("Login correcto!");
				else
					ctlLogin.setMensaje("Vuelva a intentarlo.");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
