package net.sgoliver.android.gplus;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.OnAccessRevokedListener;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

public class MainActivity extends Activity 
			implements ConnectionCallbacks, OnConnectionFailedListener, 
				PlusClient.OnPersonLoadedListener, PlusClient.OnPeopleLoadedListener
{
	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private ProgressDialog connectionProgressDialog;
	private PlusClient plusClient;
	private ConnectionResult connectionResult;
	
	private SignInButton btnSignIn;
	private TextView txtInfo;
	private TextView txtContactos;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtInfo = (TextView)findViewById(R.id.txtInfo);
		txtContactos = (TextView)findViewById(R.id.txtContactos);
		btnSignIn = (SignInButton)findViewById(R.id.sign_in_button);

		plusClient = new PlusClient.Builder(this, this, this)
			.setVisibleActivities(
					"http://schemas.google.com/AddActivity",
					"http://schemas.google.com/ListenActivity")
			.build();

		connectionProgressDialog = new ProgressDialog(this);
		connectionProgressDialog.setMessage("Conectando...");

		btnSignIn.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View view)
			{
				if (!plusClient.isConnected())
				{
					if (connectionResult == null)
					{
						connectionProgressDialog.show();
					}
					else
					{
						try
						{
							connectionResult.startResolutionForResult(
								MainActivity.this,
								REQUEST_CODE_RESOLVE_ERR);
						}
						catch (SendIntentException e)
						{
							connectionResult = null;
							plusClient.connect();
						}
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch (item.getItemId())
		{
			//Cerrar Sesión
			case R.id.action_sign_out:
				if (plusClient.isConnected())
				{
					plusClient.clearDefaultAccount();
					plusClient.disconnect();
					plusClient.connect();

					Toast.makeText(MainActivity.this, 
							"Sesión Cerrada.",
							Toast.LENGTH_LONG).show();
				}

				return true;
			//Revocar permisos a la aplicación
			case R.id.action_revoke_access:
				if (plusClient.isConnected())
				{
					plusClient.clearDefaultAccount();

					plusClient.revokeAccessAndDisconnect(
						new OnAccessRevokedListener() {
							@Override
							public void onAccessRevoked(ConnectionResult status)
							{
								Toast.makeText(MainActivity.this,
										"Acceso App Revocado",
										Toast.LENGTH_LONG).show();
							}
						});
				}

				return true;
			default:
				return super.onMenuItemSelected(featureId, item);
		}
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		plusClient.connect();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		plusClient.disconnect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result)
	{
		if (connectionProgressDialog.isShowing())
		{
			if (result.hasResolution())
			{
				try
				{
					result.startResolutionForResult(this,
							REQUEST_CODE_RESOLVE_ERR);
				}
				catch (SendIntentException e)
				{
					plusClient.connect();
				}
			}
		}

		connectionResult = result;
	}

	@Override
	public void onConnected(Bundle connectionHint)
	{
		connectionProgressDialog.dismiss();

		Toast.makeText(this, "Conectado!", 
				Toast.LENGTH_LONG).show();
		
		//Información del perfil del usuario logueado:
		plusClient.loadPerson(this, "me");
		
        //Personas en mis círculos visibles para la aplicación:
        plusClient.loadPeople(this, Person.Collection.VISIBLE);
        
        //Perfil de un contacto:
        //plusClient.loadPerson(this, "113735310430199015092");
	}
	
	@Override
	public void onPersonLoaded(ConnectionResult status, Person person) {
	    if (status.getErrorCode() == ConnectionResult.SUCCESS) 
	    {	  
	    	//Person: http://developer.android.com/reference/com/google/android/gms/plus/model/people/Person.html
	        txtInfo.setText(
	        	person.getId() + "\n" +
	        	person.getDisplayName() + "\n" +
	        	person.getPlacesLived().get(0).getValue() + "\n" +
	        	person.getOrganizations().get(1).getName() + "\n" +
	        	person.getUrls().get(0).getValue() + "\n" + 
	        	plusClient.getAccountName()
	        );
	    }
	}
	
	@Override
	public void onPeopleLoaded(ConnectionResult status, PersonBuffer personBuffer, String nextPageToken) {
	    if (status.getErrorCode() == ConnectionResult.SUCCESS) 
	    {
	        try 
	        {
	            int count = personBuffer.getCount();
	            StringBuffer contactos = new StringBuffer("");
	            
	            for (int i = 0; i < count; i++) 
	            {
	            	contactos.append(
	            			personBuffer.get(i).getId() + "|" + 
	            			personBuffer.get(i).getDisplayName() + "\n");
	            }
	            
	            txtContactos.setText(contactos);
	        } 
	        finally 
	        {
	            personBuffer.close();
	        }
	    } 
	}

	@Override
	public void onDisconnected()
	{
		Toast.makeText(this, "Desconectado!", 
				Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent)
	{
		if (requestCode == REQUEST_CODE_RESOLVE_ERR && 
		    responseCode == RESULT_OK)
		{
			connectionResult = null;
			plusClient.connect();
		}
	}
}
