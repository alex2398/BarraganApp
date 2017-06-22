package com.example.si_angeles.barragan.LongTail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.si_angeles.barragan.Configuracion.Configuracion;
import com.example.si_angeles.barragan.Configuracion.ConfiguracionActivity;
import com.example.si_angeles.barragan.Funcionalidad.Funcionalidad;
import com.example.si_angeles.barragan.MainActivity;
import com.example.si_angeles.barragan.LongTail.LongTailAdaptador;
import com.example.si_angeles.barragan.LongTail.LongTailArt_nuevo;
import com.example.si_angeles.barragan.LongTail.LongTailMuestra;
import com.example.si_angeles.barragan.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by avalladares on 22/06/2017.
 */

public class LongTailActivity_nuevo extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private LongTailAdaptador adaptador;

    Boolean finalizado;

    TextView texto1;

    public static ArrayList<LongTailArt_nuevo> longtails;
    private static String xml;

    private static final String NAMESPACE = "http://barragan.org/";
    private String METHOD_NAME = "GetArtLongTail";
    private static final String SOAP_ACTION = "http://barragan.org/GetArtLongTail";
    private static String URL; // = "http://213.98.61.41:9080/ABE/WebServiceBarragan.asmx";
    //private static final String URL1 = "http://192.168.0.220/ABE/WebServiceBarragan.asmx";

    private static String URLfotos;// = "http://213.98.61.41:9080/ABE/";

    private Configuracion c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longtail_nuevo_muestra);

        c = new Configuracion();
        c.inicializar(this);

        URL = c.obtenerURL();
        URLfotos = c.obtenerURLFotosNovedades();

        finalizado = false;

        texto1 = (TextView) findViewById(R.id.cab_longtail);

        gridView = (GridView) findViewById(R.id.grid_longtail);
        adaptador = new LongTailAdaptador(this);
        //   gridView.setAdapter(adaptador);
        // gridView.setOnItemClickListener(this);


        com.example.si_angeles.barragan.LongTail.LongTailActivity_nuevo.TareaWSLongtail tarea = new com.example.si_angeles.barragan.LongTail.LongTailActivity_nuevo.TareaWSLongtail();
        tarea.execute();

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
                Intent i = new Intent(com.example.si_angeles.barragan.LongTail.LongTailActivity_nuevo.this, ConfiguracionActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void imprimirTabla() throws UnsupportedEncodingException {

        Funcionalidad f = new Funcionalidad();

        if (xml.indexOf("SINDATOS") != -1) {
            texto1.setText("SIN DATOS PARA ESTA CONSULTA.");

        } else {
            longtails = f.parsearLongTailNuevos(xml, URLfotos);


            gridView.setAdapter(adaptador);
            gridView.setOnItemClickListener(com.example.si_angeles.barragan.LongTail.LongTailActivity_nuevo.this);



        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LongTailArt_nuevo item = (LongTailArt_nuevo) parent.getItemAtPosition(position);



        Intent intent = new Intent(this, LongTailMuestra.class);

        intent.putExtra("codigo", item.getCodigo());
        intent.putExtra("nombre", item.getNombre());
        intent.putExtra("grupo", item.getGrupo());
        intent.putExtra("precio", item.getPrecio());
        intent.putExtra("url", URLfotos);
        startActivity(intent);

    }



    private class TareaWSLongtail extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            boolean resultado = true;

            HttpTransportSE transporte;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("code", "1535");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            try {
                transporte = new HttpTransportSE(URL);
                transporte.debug = true;

                transporte.call(SOAP_ACTION, envelope);
                xml = transporte.responseDump;
                finalizado = true;

            } catch (Exception e) {

                // e.printStackTrace();
                resultado = false;
            }

            return resultado;
        }


        protected void onPostExecute(Boolean result) {

            if (result) {

                try {
                    imprimirTabla();
                }catch (Exception ex){
                    Log.e("URL_ERROR",ex.getMessage());
                }


            } else {
                Log.v("10", "error");


                Toast toast = Toast.makeText(getParent().getBaseContext(), "Error, revise configuración de red", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                finish();


            }
        }
    }


}



