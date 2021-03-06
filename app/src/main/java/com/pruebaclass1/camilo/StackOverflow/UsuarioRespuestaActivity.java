package com.pruebaclass1.camilo.StackOverflow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class UsuarioRespuestaActivity extends Activity {

    String valorRecibido;
    private TextView nombre;
    private TextView fecha;
    DataBaseComentariosManager manager;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuariorespuesta);

        manager = new DataBaseComentariosManager(this);
        valorRecibido=getIntent().getStringExtra(ComentariosActivity.ID_COMENTARIO_PASAR);

        //Toast.makeText(getApplicationContext(), "Has seleccionado: "+ valorRecibido, Toast.LENGTH_LONG).show();

        cursor = manager.traerUsuarioRespuesta(valorRecibido);

        cursor.moveToPosition(0);//Linea vital
        String name = cursor.getString(cursor.getColumnIndex("nombrePropietario"));
        String fe = cursor.getString(cursor.getColumnIndex("fechaCreado"));

        nombre = (TextView) findViewById(R.id.preguntaTextView);
        nombre.setText("Nombre del usuario: "+name);

        fecha = (TextView) findViewById(R.id.textView2);
        fecha.setText("Fecha respuesta: "+fe);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_nuevo:
                Intent i = new Intent(getApplicationContext(), NuevoActivity.class);
                startActivity(i);
                return true;

            case R.id.action_buscar:
                Intent i4 = new Intent(getApplicationContext(), BuscarActivity.class);
                startActivity(i4);
                return true;

            case R.id.action_tutorial:
                Intent i2 = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(i2);
                return true;

            case R.id.action_ayuda:
                Intent i3 = new Intent(getApplicationContext(), AyudaActivity.class);
                startActivity(i3);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
