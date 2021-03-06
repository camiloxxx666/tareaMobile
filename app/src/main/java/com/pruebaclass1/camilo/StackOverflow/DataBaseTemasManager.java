package com.pruebaclass1.camilo.StackOverflow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Damian on 02/11/2014.
 */
public class DataBaseTemasManager
{

    public static final String TABLE_NAME_TEMAS = "temas";

    public static final String ID_TEMAS = "_id";

    public static final String CN_TITULO_TEMAS = "titulo";

    public static final String CN_TEXTO_TEMAS = "texto";

    public static final String CN_NOMBRE_PROPIETARIO_TEMAS = "nombreCreador";

    public static final String CN_EMAIL_TEMAS = "email";

    public static final String CN_FECHA_TEMAS = "fechaCreado";

    public static final String CREATE_TABLE_TEMAS = " create table " + TABLE_NAME_TEMAS + " ( "
                                                    + ID_TEMAS + " integer primary key autoincrement, "
                                                    + CN_TITULO_TEMAS + " text NOT NULL, "
                                                    + CN_NOMBRE_PROPIETARIO_TEMAS + " text NOT NULL, "
                                                    + CN_TEXTO_TEMAS + " text NOT NULL, "
                                                    + CN_EMAIL_TEMAS +  " text NOT NULL, "
                                                    + CN_FECHA_TEMAS + " text NOT NULL); ";

    private DbHelper helper;

    private SQLiteDatabase db;

    public DataBaseTemasManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar_tema(String titulo, String nombrePropietario, String texto, String email, String fecha )
    {
        ContentValues valores = new ContentValues();
        valores.put(CN_TITULO_TEMAS, titulo);
        valores.put(CN_NOMBRE_PROPIETARIO_TEMAS, nombrePropietario);
        valores.put(CN_TEXTO_TEMAS, texto);
        valores.put(CN_EMAIL_TEMAS, email);
        valores.put(CN_FECHA_TEMAS, fecha);


        db.insert(TABLE_NAME_TEMAS, null, valores);
    }

    public Cursor getTemas()
    {
        String[] columnas = new String[]{ID_TEMAS, CN_TITULO_TEMAS ,CN_NOMBRE_PROPIETARIO_TEMAS, CN_TEXTO_TEMAS, CN_EMAIL_TEMAS};

        return db.query(TABLE_NAME_TEMAS, columnas, null,null,null,null,null,null);
    }

    public Cursor traerUsuarioTema(String idTema)
    {
        String[] columnas = new String[]{CN_NOMBRE_PROPIETARIO_TEMAS, CN_FECHA_TEMAS};
        return db.query(TABLE_NAME_TEMAS,columnas,ID_TEMAS + "=?",new String[]{idTema},null,null,null);
    }

    public Cursor buscarTemas(String clave)
    {
        String[] columnas = new String[]{ID_TEMAS,CN_TITULO_TEMAS, CN_TEXTO_TEMAS};
        Cursor cur1 = db.query(TABLE_NAME_TEMAS,columnas,CN_NOMBRE_PROPIETARIO_TEMAS + "=?",new String[]{clave},null,null,null);

        String[] columnas2 = new String[]{ID_TEMAS,CN_TITULO_TEMAS, CN_TEXTO_TEMAS};
        Cursor cur2 = db.query(TABLE_NAME_TEMAS,columnas2,CN_FECHA_TEMAS + "=?",new String[]{clave},null,null,null);

        Cursor[] cursores = new Cursor[]{cur1, cur2};
        MergeCursor mc = new MergeCursor(cursores);
        return mc;

    }

    public String getTexto(String clave)
    {
        String[] columnas = new String[]{ID_TEMAS, CN_TEXTO_TEMAS};

        Cursor cursor =  db.query(TABLE_NAME_TEMAS,columnas,ID_TEMAS + "=?",new String[]{clave},null,null,null);
        cursor.moveToPosition(0);
        return cursor.getString(cursor.getColumnIndex("texto"));
    }
}
