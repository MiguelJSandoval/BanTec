/*
 * Copyright (c) 2021, DevMJS. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Please contact DevMJS on contact.devmjs@gmail.com if you need
 * additional information or have any questions.
 */

package com.devmjs.bantec;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegistroNuevoUsuario extends AppCompatActivity
{
    private TextInputEditText usu, contra, confirm;
    private GestorBaseDatos bd;
    Metodos m = new Metodos();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_usuario);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Registro usuario");
        bd = new GestorBaseDatos(this);
        usu = (TextInputEditText) findViewById(R.id.nomusuRegsUsu);
        contra = (TextInputEditText) findViewById(R.id.contraRegisUsu);
        confirm = (TextInputEditText) findViewById(R.id.confirmContraRegisUsu);
        Button acept = (Button) findViewById(R.id.aceptRegsUsu);
        Button cancel = (Button) findViewById(R.id.cancelRegsUsu);
        acept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nC = m.generaNo();
                String nT = m.generaNoTarjeta();
                String nom = Objects.requireNonNull(usu.getText()).toString();
                String saldos = "100.0";
                String pass = Objects.requireNonNull(contra.getText()).toString();
                String passC = Objects.requireNonNull(confirm.getText()).toString();
                boolean ban = nC.length() != 0 && nom.length() != 0 && pass.length() != 0 && passC.length() != 0 && nT.length() != 0;
                if (ban)
                {
                    if (nom.length() >= 3 && nom.length() <= 12 && pass.length() >= 4 && pass.length() <= 16)
                    {
                        if (!nom.equals("admin"))
                        {
                            if (Double.parseDouble(saldos) > 0)
                            {
                                if (!bd.exists(nom))
                                {
                                    if (pass.equals(passC))
                                    {
                                        String cv2 = m.generaCV2();
                                        String[] arr = {nC, nom, saldos, pass, nT, cv2};
                                        if (bd.inserta(arr))
                                        {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(RegistroNuevoUsuario.this);
                                            alert.setTitle("¡Registro éxitoso!")
                                                    .setMessage("Gracias por elegir BanTec\nAl haber creado su cuenta, ha recibido $100.00 de regalo" +
                                                            "\n¡Disfrútelos!")
                                                    .setPositiveButton("ACEPTAR",
                                                            new DialogInterface.OnClickListener()
                                                            {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which)
                                                                {
                                                                    Intent ii = new Intent(RegistroNuevoUsuario.this, UsuActivity.class);
                                                                    ii.putExtra("user", usu.getText().toString());
                                                                    startActivity(ii);
                                                                    finish();
                                                                }
                                                            }).setCancelable(false);
                                            alert.create();
                                            alert.show();
                                        } else
                                        {
                                            Toast.makeText(RegistroNuevoUsuario.this, "¡Ocurrió un error inesperado!," +
                                                    "intente de nuevo", Toast.LENGTH_SHORT).show();
                                            usu.setText("");
                                            contra.setText("");
                                            confirm.setText("");
                                            usu.requestFocus();
                                        }
                                    } else
                                    {
                                        Toast.makeText(RegistroNuevoUsuario.this, "¡Las contraseñas " +
                                                        "no coinciden, verificalas!",
                                                Toast.LENGTH_LONG).show();
                                        contra.requestFocus();
                                    }
                                } else
                                {
                                    Toast.makeText(RegistroNuevoUsuario.this, "¡Error, usuario " +
                                            "repetido!", Toast.LENGTH_LONG).show();
                                    usu.requestFocus();
                                }
                            } else
                            {
                                Toast.makeText(RegistroNuevoUsuario.this, "El saldo debe de ser mayor a cero", Toast.LENGTH_LONG).show();
                            }
                        } else
                        {
                            Toast.makeText(RegistroNuevoUsuario.this, "¡Error, usuario " +
                                    "repetido!", Toast.LENGTH_LONG).show();
                            usu.requestFocus();
                        }
                    } else
                    {
                        Toast.makeText(RegistroNuevoUsuario.this, "El nombre debe tener entre 3 y 12 caracteres\n" +
                                "y la contraseña entre 4 y 16 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(RegistroNuevoUsuario.this, "Llena todos los campos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(RegistroNuevoUsuario.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}