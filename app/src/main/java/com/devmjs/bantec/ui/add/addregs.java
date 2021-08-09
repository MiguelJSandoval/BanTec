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

package com.devmjs.bantec.ui.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.Metodos;
import com.devmjs.bantec.R;

public class addregs extends Fragment
{
    private EditText noCuenta, noTarjeta, username, saldo, contra, repeatContra;
    private GestorBaseDatos bd;
    Metodos m = new Metodos();

    public addregs()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        bd = new GestorBaseDatos(getContext());
        View root = inflater.inflate(R.layout.fragment_addregs, container, false);
        Button add = root.findViewById(R.id.btnAddRegs);
        noCuenta = root.findViewById(R.id.addNoCuenta);
        noCuenta.setText(m.generaNo());
        noTarjeta = root.findViewById(R.id.addNoTarjeta);
        noTarjeta.setText(m.generaNoTarjeta());
        username = root.findViewById(R.id.addNombre);
        saldo = root.findViewById(R.id.addSaldo);
        contra = root.findViewById(R.id.addContrasena);
        repeatContra = root.findViewById(R.id.addConfirmContra);
        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nC = noCuenta.getText().toString();
                String nT = noTarjeta.getText().toString();
                String nom = username.getText().toString();
                String saldos = saldo.getText().toString();
                String pass = contra.getText().toString();
                String passC = repeatContra.getText().toString();
                boolean ban = nC.length() != 0 && nom.length() != 0
                        && saldos.length() != 0 && pass.length() != 0
                        && passC.length() != 0 && nT.length() != 0;
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
                                            Toast.makeText(getContext(), "¡Registro éxitoso!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else
                                        {
                                            Toast.makeText(getContext(), "¡Ocurrió un error inesperado!," +
                                                    "intente de nuevo", Toast.LENGTH_SHORT).show();
                                        }
                                        noCuenta.setText(m.generaNo());
                                        noTarjeta.setText(m.generaNoTarjeta());
                                        username.setText("");
                                        saldo.setText("");
                                        contra.setText("");
                                        repeatContra.setText("");
                                        username.requestFocus();
                                    } else
                                    {
                                        Toast.makeText(getContext(), "¡Las contraseñas " +
                                                        "no coinciden, verificalas!",
                                                Toast.LENGTH_LONG).show();
                                        contra.requestFocus();
                                    }
                                } else
                                {
                                    Toast.makeText(getContext(), "¡Error, usuario " +
                                            "repetido!", Toast.LENGTH_LONG).show();
                                    username.requestFocus();
                                }
                            } else
                            {
                                Toast.makeText(getContext(), "El saldo debe de ser mayor a cero", Toast.LENGTH_LONG).show();
                            }
                        } else
                        {
                            Toast.makeText(getContext(), "¡Error, usuario " +
                                    "repetido!", Toast.LENGTH_LONG).show();
                        }
                    } else
                    {
                        Toast.makeText(getContext(), "El nombre debe tener entre 3 y 12 caracteres\n" +
                                "y la contraseña entre 4 y 16 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(getContext(), "Llena todos los campos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        Button cancelar = root.findViewById(R.id.btnCancelAdd);
        cancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                username.setText("");
                contra.setText("");
                saldo.setText("");
                repeatContra.setText("");
                username.requestFocus();
            }
        });
        return root;
    }
}