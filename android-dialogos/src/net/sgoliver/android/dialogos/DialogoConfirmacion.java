package net.sgoliver.android.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class DialogoConfirmacion extends DialogFragment {
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = 
        		new AlertDialog.Builder(getActivity());
        
        builder.setMessage("¿Confirma la acción seleccionada?")
        	   .setTitle("Confirmacion")
               .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   	Log.i("Dialogos", "Confirmacion Aceptada.");
       					dialog.cancel();
                   }
               })
               .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   	Log.i("Dialogos", "Confirmacion Cancelada.");
       					dialog.cancel();
                   }
               });

        return builder.create();
    }
}
