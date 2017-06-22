package com.example.si_angeles.barragan.LongTail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.si_angeles.barragan.NovedadesArticulos.NovedadesActivity;
import com.example.si_angeles.barragan.NovedadesArticulos.NovedadesArticulo;
import com.example.si_angeles.barragan.R;

/**
 * Created by avalladares on 22/06/2017.
 */

public class LongTailAdaptador extends BaseAdapter {

    private Context context;
    ImageView imagen;
    TextView nombre;

    public LongTailAdaptador(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return LongTailActivity_nuevo.longtails.size();
    }
    public LongTailArt_nuevo getItem(int posicion) {
        return LongTailActivity_nuevo.longtails.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return LongTailActivity_nuevo.longtails.get(posicion).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        imagen = (ImageView) view.findViewById(R.id.imagen);
        nombre = (TextView) view.findViewById(R.id.nombre);

        final LongTailArt_nuevo item = getItem(position);
        //imagen.setImageBitmap(BitmapFactory.decodeFile(item.getUrlFoto()));
        String url = item.getUrlFoto();
        Glide.with(imagen.getContext()).load(item.getUrlFoto()).into(imagen);
        /*Glide.with(imagen.getContext())
                .load(item.getUrlFoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(0)
                .listener((RequestListener<? super String, GlideDrawable>) this)
                .into(imagen);
*/
        nombre.setText(item.getNombre());

        return view;
    }



}

