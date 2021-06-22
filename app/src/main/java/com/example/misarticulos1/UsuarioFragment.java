package com.example.misarticulos1;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState)
    {
        View vista = inflador.inflate(R.layout.fragment_usuario, contenedor, false);
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        TextView nombre = (TextView) vista.findViewById(R.id.nombre);
        nombre.setText(usuario.getDisplayName());
        TextView Email = (TextView) vista.findViewById(R.id.email);
        Email.setText(usuario.getEmail());
        TextView Proveedor = (TextView) vista.findViewById(R.id.proveedor);
        Proveedor.setText(usuario.getProviderId());



        RequestQueue colaPeticiones = Volley.newRequestQueue(getActivity() .getApplicationContext());
        ImageLoader lectorImagenes = new ImageLoader(colaPeticiones, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap)
            {
                cache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url)
            {
                return cache.get(url);
            }
        });

        // Foto de usuario
        Uri urlImagen = usuario.getPhotoUrl();
        if (urlImagen != null)
        {
            NetworkImageView fotoUsuario = (NetworkImageView) vista.findViewById(R.id.imagen);
            fotoUsuario.setImageUrl(urlImagen.toString(), lectorImagenes);
        }
        return vista;
    }
}