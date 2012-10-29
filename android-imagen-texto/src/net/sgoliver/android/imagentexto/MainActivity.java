package net.sgoliver.android.imagentexto;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText txtTexto;
	private Button btnNegrita;
	private Button btnSetText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtTexto = (EditText)findViewById(R.id.TxtTexto);
        
        btnNegrita = (Button)findViewById(R.id.BtnNegrita);
        btnNegrita.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0)
			{
				Spannable texto = txtTexto.getText();
			
				int ini = txtTexto.getSelectionStart();
				int fin = txtTexto.getSelectionEnd();
				
				texto.setSpan(
						new StyleSpan(android.graphics.Typeface.BOLD), 
						ini, fin, 
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		});
        
        btnSetText = (Button)findViewById(R.id.BtnSetText);
        btnSetText.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0)
			{
				Editable str = Editable.Factory.getInstance().newEditable("Esto es un simulacro.");
				str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 11, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				txtTexto.setText(str);
				
				//Nueva cadena de texto
				//String nuevoTexto = "<p>Otro <b>texto</b> de ejemplo.</p>";
				
				//Asigna texto sin formato (incluirá todas las etiquetas HTML)
				//txtTexto.setText(nuevoTexto);
				
				//Asigna texto con formato HTML
				//txtTexto.setText(Html.fromHtml(nuevoTexto),BufferType.SPANNABLE);
				
				//Obtiene el texto SIN etiquetas de formato HTML
				//String aux1 = txtTexto.getText().toString();
				
				//Obtiene el texto CON etiquetas de formato HTML
				//String aux2 = Html.toHtml(txtTexto.getText());
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
