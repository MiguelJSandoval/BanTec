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

package com.devmjs.bantec.ui.ModificaRegistro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Registro;

import java.util.ArrayList;
import java.util.Objects;

public class ModificaFragment extends Fragment
{
    private Spinner lista;
    private GestorBaseDatos bd;
    private TextView txtNoCuenta, txtNombre, txtSaldo, txtContra;
    private String nC, nom, saldos, pass, nT = "", cv2 = "", nomAnt;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState)
    {
        bd = new GestorBaseDatos(getContext());
        View root = inflater.inflate(R.layout.fragment_modifica, container, false);
        lista = root.findViewById(R.id.comboUsuMod);
        final ArrayList<String> usuarios = bd.registroUsuarios();
        final ArrayAdapter<CharSequence> usu = new ArrayAdapter(getContext(), R.layout.layout_disenio_spinner, usuarios);
        if (usuarios != null)
        {
            lista.setAdapter(usu);
        }
        final ArrayList<Registro> regs = bd.consultaRegistros();
        txtNoCuenta = root.findViewById(R.id.editNoCuenta);
        txtNoCuenta.setEnabled(false);
        txtNombre = root.findViewById(R.id.editNombre);
        txtSaldo = root.findViewById(R.id.editSaldo);
        txtContra = root.findViewById(R.id.editContrasena);
        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (Objects.requireNonNull(usuarios).size() > 0)
                {
                    String u = parent.getItemAtPosition(position).toString();
                    Registro r = bd.consultaRegistroUsuario(u);
                    nomAnt = parent.getItemAtPosition(position).toString();
                    txtNoCuenta.setText(r.getNoCuenta());
                    txtNombre.setText(r.getUsuario());
                    txtSaldo.setText(String.valueOf(r.getSaldo()));
                    txtContra.setText(r.getContrasenia());
                    txtNombre.requestFocus();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
        Button cancel = root.findViewById(R.id.btnCancelModifi);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position;
                try
                {
                    position = lista.getSelectedItemPosition();
                } catch (NullPointerException e)
                {
                    position = -1;
                }
                if (position >= 0)
                {
                    String u = lista.getItemAtPosition(position).toString();
                    Registro r = bd.consultaRegistroUsuario(u);
                    nomAnt = lista.getItemAtPosition(position).toString();
                    txtNoCuenta.setText(r.getNoCuenta());
                    txtNombre.setText(r.getUsuario());
                    txtSaldo.setText(String.valueOf(r.getSaldo()));
                    txtContra.setText(r.getContrasenia());
                    txtNombre.requestFocus();
                }
            }
        });
        Button acept = root.findViewById(R.id.btnAceptModifi);
        acept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final int position = lista.getSelectedItemPosition();
                nC = txtNoCuenta.getText().toString();
                nom = txtNombre.getText().toString();
                saldos = txtSaldo.getText().toString();
                pass = txtContra.getText().toString();
                if (regs != null)
                {
                    nT = regs.get(position).getNoTarjeta();
                    cv2 = regs.get(position).getCV2();
                }
                boolean ban = nC.length() != 0 && nom.length() != 0
                        && saldos.length() != 0 && pass.length() != 0
                        && nT.length() != 0;
                if (ban)
                {
                    if (nom.length() >= 3 && nom.length() <= 12 && pass.length() >= 4 && pass.length() <= 16)
                    {
                        if (!nom.equals("admin"))
                        {
                            if (!bd.exists(nom) || nom.equals(nomAnt))
                            {
                                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                                alert.setTitle("Confirmar modificación")
                                        .setMessage("¿Desea modificar este registro?")
                                        .setNegativeButton("CANCELAR",
                                                new DialogInterface.OnClickListener()
                                                {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which)
                                                    {
                                                        String u = lista.getItemAtPosition(position).toString();
                                                        Registro r = bd.consultaRegistroUsuario(u);
                                                        nomAnt = lista.getItemAtPosition(position).toString();
                                                        txtNoCuenta.setText(r.getNoCuenta());
                                                        txtNombre.setText(r.getUsuario());
                                                        txtSaldo.setText(String.valueOf(r.getSaldo()));
                                                        txtContra.setText(r.getContrasenia());
                                                        txtNombre.requestFocus();
                                                        Toast.makeText(getContext(), "Modificación cancelada", Toast.LENGTH_SHORT).show();
                                                    }
                                                })
                                        .setPositiveButton("ACEPTAR",
                                                new DialogInterface.OnClickListener()
                                                {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which)
                                                    {
                                                        String[] arr = {nC, nom, saldos, pass, nT, cv2};
                                                        if (bd.modifica(arr, nomAnt))
                                                        {
                                                            int s = lista.getSelectedItemPosition();
                                                            Objects.requireNonNull(usuarios).set(s, nom);
                                                            ArrayList<String> aux = bd.registroUsuarios();
                                                            for (int i = 0; i < aux.size(); i++)
                                                            {
                                                                usuarios.set(i, aux.get(i));
                                                            }
                                                            bd.modificaTransaccion(nom, nomAnt);
                                                            Toast.makeText(getContext(), "Registro modificado", Toast.LENGTH_SHORT).show();
                                                            usu.notifyDataSetChanged();
                                                            lista.setAdapter(usu);
                                                        } else
                                                        {
                                                            Toast.makeText(getContext(), "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }).setCancelable(false);
                                alert.create().show();
                            } else
                            {
                                Toast.makeText(getContext(), "¡Error, usuario " +
                                        "repetido!", Toast.LENGTH_LONG).show();
                                txtNombre.requestFocus();
                            }
                        } else
                        {
                            Toast.makeText(getContext(), "¡Error, usuario " +
                                    "repetido!", Toast.LENGTH_LONG).show();
                            txtNombre.requestFocus();
                        }
                    } else
                    {
                        Toast.makeText(getContext(), "El nombre debe tener entre 3 y 12 caracteres\n" +
                                "y la contraseña entre 4 y 16 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    if (regs != null)
                    {
                        Toast.makeText(getContext(), "No debe haber campos vacíos", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(getContext(), "No hay registros para modificar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }
}