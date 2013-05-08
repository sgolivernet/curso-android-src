package net.sgoliver.android.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

public class ClientesProvider extends ContentProvider {
	
	//Definición del CONTENT_URI
	private static final String uri = 
		"content://net.sgoliver.android.contentproviders/clientes";
	
	public static final Uri CONTENT_URI = Uri.parse(uri);
	
	//Necesario para UriMatcher
	private static final int CLIENTES = 1;
	private static final int CLIENTES_ID = 2;
	private static final UriMatcher uriMatcher;
	
	//Clase interna para declarar las constantes de columna
	public static final class Clientes implements BaseColumns
	{
		private Clientes() {}
	
		//Nombres de columnas
		public static final String COL_NOMBRE = "nombre";
		public static final String COL_TELEFONO = "telefono";
		public static final String COL_EMAIL = "email";
	}
	
	//Base de datos
	private ClientesSqliteHelper clidbh;
	private static final String BD_NOMBRE = "DBClientes";
	private static final int BD_VERSION = 1;
	private static final String TABLA_CLIENTES = "Clientes";
	
	//Inicializamos el UriMatcher
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("net.sgoliver.android.contentproviders", "clientes", CLIENTES);
		uriMatcher.addURI("net.sgoliver.android.contentproviders", "clientes/#", CLIENTES_ID);
	}
	
	@Override
	public boolean onCreate() {
		
		clidbh = new ClientesSqliteHelper(
						getContext(), BD_NOMBRE, null, BD_VERSION);

		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection,
		String selection, String[] selectionArgs, String sortOrder) {
		
		//Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }

		SQLiteDatabase db = clidbh.getWritableDatabase();
		
		Cursor c = db.query(TABLA_CLIENTES, projection, where, 
				            selectionArgs, null, null, sortOrder);
		
		return c;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		long regId = 1; 
		
		SQLiteDatabase db = clidbh.getWritableDatabase();
		
		regId = db.insert(TABLA_CLIENTES, null, values);
		
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
		
		return newUri;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, 
			          String selection, String[] selectionArgs) {
		
		int cont;
		
		//Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }
		
		SQLiteDatabase db = clidbh.getWritableDatabase();
		
		cont = db.update(TABLA_CLIENTES, values, where, selectionArgs);
		
		return cont;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int cont;
		
		//Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if(uriMatcher.match(uri) == CLIENTES_ID){
            where = "_id=" + uri.getLastPathSegment();
        }
		
		SQLiteDatabase db = clidbh.getWritableDatabase();
		
		cont = db.delete(TABLA_CLIENTES, where, selectionArgs);
		
		return cont;
	}
	
	@Override
	public String getType(Uri uri) {
		
		int match = uriMatcher.match(uri);
		
		switch (match) 
		{
			case CLIENTES: 
				return "vnd.android.cursor.dir/vnd.sgoliver.cliente";
			case CLIENTES_ID: 
				return "vnd.android.cursor.item/vnd.sgoliver.cliente";
			default: 
				return null;
		}
	}
}
