package net.sgoliver.android.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class DialogoSeleccion extends DialogFragment {
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

		final String[] items = {"Español", "Inglés", "Francés"};
		
        AlertDialog.Builder builder = 
        		new AlertDialog.Builder(getActivity());
        
        builder.setTitle("Selección")
        .setItems(items, new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int item) {
	    	        Log.i("Dialogos", "Opción elegida: " + items[item]);
	    	    }
	    	});
        
//        builder.setTitle("Selección")
//        .setMultiChoiceItems(items, null, 
//        		new DialogInterface.OnMultiChoiceClickListener() {
//        	public void onClick(DialogInterface dialog, int item, boolean isChecked) {
//                Log.i("Dialogos", "Opción elegida: " + items[item]);
//            }
//	    });
        
//        builder.setTitle("Selección")
//           .setSingleChoiceItems(items, -1, 
//        		   new DialogInterface.OnClickListener() {
//	    	    public void onClick(DialogInterface dialog, int item) {
//	    	        Log.i("Dialogos", "Opción elegida: " + items[item]);
//	    	    }
//	    	});

        return builder.create();
    }
}
