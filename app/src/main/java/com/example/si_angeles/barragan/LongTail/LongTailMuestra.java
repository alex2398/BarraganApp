package com.example.si_angeles.barragan.LongTail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.si_angeles.barragan.Configuracion.ConfiguracionActivity;
import com.example.si_angeles.barragan.MainActivity;
import com.example.si_angeles.barragan.NovedadesArticulos.NovedadesArticulo;
import com.example.si_angeles.barragan.NovedadesArticulos.NovedadesMuestra;
import com.example.si_angeles.barragan.R;
import com.example.si_angeles.barragan.TouchImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by avalladares on 22/06/2017.
 */

public class LongTailMuestra extends AppCompatActivity {
    private TouchImageView imagen;
    private TextView codigo;
    private TextView nombre;
    private TextView grupo;
    private TextView precio;

    private LongTailArt_nuevo articulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longtail_nuevo_vista);

        String aux1;
        String aux2, aux3, aux4, aux5;
        int aux6;
        imagen = (TouchImageView) findViewById(R.id.imagen_longtail);

        codigo = (TextView) findViewById(R.id.detalle_longtail_codigo);
        nombre = (TextView) findViewById(R.id.detalle_longtail_nombre);
        grupo    = (TextView) findViewById(R.id.detalle_longtail_grupo);
        precio = (TextView) findViewById(R.id.detalle_longtail_precio);


        //aux1 = Integer.parseInt(getIntent().getExtras().getString("id"));
        aux1 = getIntent().getExtras().getString("codigo");
        aux2 = getIntent().getExtras().getString("nombre");
        aux3 = getIntent().getExtras().getString("grupo");
        aux4 = getIntent().getExtras().getString("precio");
        aux5 = getIntent().getExtras().getString("url");
        aux6 = getIntent().getExtras().getInt("id");

        articulo = new LongTailArt_nuevo (aux1, aux2, aux3, aux4, aux5, aux6);

        codigo.setText(articulo.getCodigo());
        nombre.setText(articulo.getNombre());
        grupo.setText(articulo.getGrupo());
        precio.setText(articulo.getPrecio());


        Picasso.with(imagen.getContext())
                .load(articulo.getUrlFoto())
                .into(imagen);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));

                finish();
                return true;
            case R.id.atras:
                finish();
                return true;
            case R.id.action_settings:
                Intent i = new Intent(LongTailMuestra.this, ConfiguracionActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}




