package com.example.misarticulos1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cerrarSesion = (Button) findViewById(R.id.btn_cerrar_sesion);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i = new Intent( MainActivity.this, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        MainActivity.this.finish();
                    }
                });
            }
        });
        Button datosUsuario = (Button) findViewById(R.id.btn_datos_usuario);
        datosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDatosUsuario();
            }
        });
        Button escribirBD = (Button) findViewById(R.id.btn_escribir_bd);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://misarticulos1-ef775-default-rtdb.firebaseio.com/");
        DatabaseReference myRef1 = database.getReference("mensaje");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mensaje = dataSnapshot.getValue(String.class);
                Log.d("Ejemplo Firebase: ", "Valor Mensaje1: " + mensaje);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Ejemplo Firebase:", "Error al leer", databaseError.toException());
            }
        });/*
        escribirBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef2 = database.getReference("mensaje2");

                myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String mensaje2 = dataSnapshot.getValue(String.class);
                        myRef.setValue(mensaje2);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("Ejemplo Firebase:", "Error al leer", databaseError.toException());
                    }
                });



            }
        });
        escribirBD.setOnClickListener(v->
        {
                //FirebaseDatabase database = FirebaseDatabase.getInstance("https://misarticulos1-ef775-default-rtdb.firebaseio.com/");
                DatabaseReference myRef = database.getReference("mensaje2");
                myRef.setValue("¡Hola mundo fuera de linea!");
            });*/
    }
    public void lanzarDatosUsuario(){
        Intent i = new Intent(this , UsuarioActivity.class);
        startActivity(i);
    }

}