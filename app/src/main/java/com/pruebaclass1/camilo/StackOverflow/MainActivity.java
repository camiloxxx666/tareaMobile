package com.pruebaclass1.camilo.StackOverflow;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pruebaclass1.camilo.StackOverflow.modelo.Tema;

import java.io.InputStream;


public class MainActivity extends Activity
{
    public final static String ID_TEMA_PASAR = "com.pruebaclass1.camilo.pruebaclass1._id";
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Tema tema = new Tema();
            tema.setContext(this);
            ViewGroup cont = tema.renderizar();
            setContentView(cont);

        listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(onListClick);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Intent i = new Intent(MainActivity.this, UsuarioActivity.class);
                i.putExtra(ID_TEMA_PASAR, String.valueOf(id));
                startActivity(i);

                return true;
            }
        });

/*       try {
            HttpResponse<InputStream> response = Unirest.post("https://neutrinoapi-qr-code.p.mashape.com/qr-code")
                    .header("X-Mashape-Key", "j2l3mVOGr5mshF4TvvQgnGD2Vbbxp1w2BrFjsnYY2jGsY6Kcw0")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("bg-color", "#ffffff")
                    .field("content", "Damian")
                    .field("fg-color", "#000000")
                    .field("height", 400)
                    .field("width", 400)
                    .asBinary();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
*/

    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id )
        {
            Intent i = new Intent(MainActivity.this, ComentariosActivity.class);
            i.putExtra(ID_TEMA_PASAR, String.valueOf(id));
            startActivity(i);

        }

    };


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
