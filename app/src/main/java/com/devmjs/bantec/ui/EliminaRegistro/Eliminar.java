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

package com.devmjs.bantec.ui.EliminaRegistro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;

import java.util.ArrayList;

public class Eliminar extends Fragment
{

    private Spinner seleccion;
    private GestorBaseDatos bd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_eliminaregistro, container, false);
        bd = new GestorBaseDatos(getContext());
        seleccion = root.findViewById(R.id.seleccionElimina);
        final ArrayList<String> usuarios = bd.registroUsuarios();
        final ArrayAdapter<CharSequence> usu = new ArrayAdapter(getContext(), R.layout.layout_disenio_spinner, usuarios);
        if (usuarios != null)
        {
            seleccion.setAdapter(usu);
        }
        Button elimina = root.findViewById(R.id.adminElimina);
        elimina.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nom = "";
                try
                {
                    nom = seleccion.getSelectedItem().toString();
                } catch (NullPointerException ignored)
                {
                }
                if (nom != null && !nom.equals(""))
                {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("Confirmar eliminación")
                            .setMessage("¿Desea eliminar este registro?")
                            .setNegativeButton("CANCELAR",
                                    new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            Toast.makeText(getContext(), "CANCELADO", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .setPositiveButton("ACEPTAR",
                                    new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            String names = "";
                                            try
                                            {
                                                names = seleccion.getSelectedItem().toString();
                                            } catch (NullPointerException ignored)
                                            {
                                            }
                                            if (names != null && !names.equals(""))
                                            {
                                                if (bd.eliminaRegistro(names))
                                                {
                                                    assert usuarios != null;
                                                    usuarios.remove(names);
                                                    usu.notifyDataSetChanged();
                                                    seleccion.setAdapter(usu);
                                                    Toast.makeText(getContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }).setCancelable(false);
                    alert.create().show();
                } else
                {
                    Toast.makeText(getContext(), "No hay usuarios por eliminar", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
}
