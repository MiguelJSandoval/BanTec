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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private TextInputEditText user, pass;
    public static String packagename;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio de sesión");
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        packagename = getPackageName();
        Button ingresa = findViewById(R.id.login);
        ingresa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String usuario = Objects.requireNonNull(user.getText()).toString();
                String contrasena = Objects.requireNonNull(pass.getText()).toString();
                if (usuario.length() != 0 && contrasena.length() != 0)
                {
                    if (usuario.equals("admin") && contrasena.equals("admin"))
                    {
                        Intent i = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(i);
                        finish();
                    } else
                    {
                        GestorBaseDatos bd = new GestorBaseDatos(getApplicationContext());
                        if (bd.verifUsuCont(usuario, contrasena))
                        {
                            Intent ac = new Intent(getApplicationContext(), UsuActivity.class);
                            ac.putExtra("user", usuario);
                            startActivity(ac);
                            finish();
                        } else
                        {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                            user.setText("");
                            pass.setText("");
                            user.requestFocus();
                        }
                    }
                } else
                {
                    Toast.makeText(getApplicationContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button crear = findViewById(R.id.crear);
        crear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), RegistroNuevoUsuario.class);
                startActivity(i);
                finish();
            }
        });
    }
}