package com.example.labsoftware1.personamaterial;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarPersona extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private boolean guardado;
    private TextInputLayout icajaCedula;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        cajaCedula = (EditText)findViewById(R.id.txtCedula);
        cajaNombre= (EditText)findViewById(R.id.txtNombre);
        cajaApellido = (EditText)findViewById(R.id.txtApellido);

        icajaCedula = (TextInputLayout) findViewById(R.id.cedulaPersona);
        icajaNombre = (TextInputLayout)findViewById(R.id.nombrePersona);
        icajaApellido = (TextInputLayout)findViewById(R.id.apellidoPersona);
        guardado = false;

        cajaCedula.addTextChangedListener(new TextWatcherPersonalizado(icajaCedula,getResources().getString(R.string.mensaje_error_cedula)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });


        cajaNombre.addTextChangedListener(new TextWatcherPersonalizado(icajaNombre,getResources().getString(R.string.mensaje_error_nombre)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });


        cajaApellido.addTextChangedListener(new TextWatcherPersonalizado(icajaApellido,getResources().getString(R.string.mensaje_error_apellido)) {
            @Override
            public boolean estaVacio(Editable s) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

    }




    public int fotoAleatoria(){
        int fotos[] = {R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero = (int)(Math.random() * 3);
        return fotos[numero];
    }
    public void guardar(View v){
        String urlfoto,cedula,nombre,apellido,idfoto;
        Persona p;

        if(validarTodo()){
            cedula = cajaCedula.getText().toString();
            urlfoto = String.valueOf(fotoAleatoria());
            nombre = cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            idfoto="prueba";

            p = new Persona(urlfoto,cedula,nombre,apellido,idfoto);
            p.guardar(getApplicationContext());

            InputMethodManager imp =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imp.hideSoftInputFromWindow(cajaCedula.getWindowToken(),0);
            Snackbar.make(v,getResources().getString(R.string.mensaje_exitoso_guardar),Snackbar.LENGTH_SHORT).show();
            guardado= true;
            limpiar();


        }
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");

        cajaCedula.requestFocus();

        guardado = false;
    }
    public boolean validarTodo(){
        if(cajaCedula.getText().toString().isEmpty()){
            icajaCedula.setError(getResources().getString(R.string.mensaje_error_cedula));
            cajaCedula.requestFocus();
            return false;
        }
        if(cajaNombre.getText().toString().isEmpty()){
            icajaNombre.setError(getResources().getString(R.string.mensaje_error_nombre));
            cajaNombre.requestFocus();
            return false;
        }
        if(cajaApellido.getText().toString().isEmpty()){
            icajaApellido.setError(getResources().getString(R.string.mensaje_error_apellido));
            cajaApellido.requestFocus();
            return false;
        }


        return true;
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersona.this,Principal.class);
        startActivity(i);
    }
}
