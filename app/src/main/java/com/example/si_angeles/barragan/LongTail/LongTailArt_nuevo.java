package com.example.si_angeles.barragan.LongTail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by avalladares on 22/06/2017.
 */

public class LongTailArt_nuevo {

    private String codigo;
    private String nombre;
    private String grupo;
    private String precio;
    private String urlFoto;
    private int id;



    public LongTailArt_nuevo(String codigo, String nombre, String grupo, String precio, String path, int id) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.precio = obtenerFecha(precio);


        String s = null;
        try {
            s = path + URLEncoder.encode(this.codigo,"UTF-8") + ".jpg";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.urlFoto = s;
        this.id = id;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }
    public String getPrecio() {
        return precio;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public String obtenerFecha(String f){

        SimpleDateFormat formatold = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatnew = new SimpleDateFormat("dd/MM/yyyy");

        String fecha = "";
        Date aux;

        try{
            aux = formatold.parse(f);
            fecha = formatnew.format(aux);
            //    this.fechaFactura = formatter.format(formatter.parse(fechaFactura));
        }
        catch(Exception e){

        }

        return fecha;

    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

}



