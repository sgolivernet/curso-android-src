package net.sgoliver.android.listviewopt;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Titular[] datos = new Titular[25];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        for(int i=1; i<=25; i++)
        	datos[i-1] = new Titular("Título " + i, "Subtítulo largo " + i);
          
        AdaptadorTitulares adaptador = 
        	new AdaptadorTitulares(this);
        
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        
        lstOpciones.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
class AdaptadorTitulares extends ArrayAdapter<Titular> {
    	
    	Activity context;
    	
    	AdaptadorTitulares(Activity context) {
    		super(context, R.layout.listitem_titular, datos);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) 
    	{
			View item = convertView;
			ViewHolder holder;
    		
    		if(item == null)
    		{
    			LayoutInflater inflater = context.getLayoutInflater();
    			item = inflater.inflate(R.layout.listitem_titular, null);
    			
    			holder = new ViewHolder();
    			holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
    			holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
    			
    			item.setTag(holder);
    		}
    		else
    		{
    			holder = (ViewHolder)item.getTag();
    		}
			
			holder.titulo.setText(datos[position].getTitulo());
			holder.subtitulo.setText(datos[position].getSubtitulo());
			
			return(item);
		}
    }
    
    static class ViewHolder {
    	TextView titulo;
    	TextView subtitulo;
    }
}
