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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoDeposito extends DialogFragment
{
    Activity actividad;
    private final String id;
    private final String fecha;
    private final Double cant;

    public DialogoDeposito(String id, Double cant, String fecha)
    {
        this.id = id;
        this.cant = cant;
        this.fecha = fecha;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        return crearDialogo();
    }

    @SuppressLint("SetTextI18n")
    private AlertDialog crearDialogo()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_deposito, null);
        builder.setView(v);
        TextView txtId = v.findViewById(R.id.idDialogD);
        txtId.setText(id);
        TextView txtCant = v.findViewById(R.id.cantidadDialogD);
        txtCant.setText("$" + cant);
        TextView txtFecha = v.findViewById(R.id.fechaDialogD);
        txtFecha.setText(fecha);
        ImageButton cerrar = v.findViewById(R.id.cerrarDialogD);
        cerrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        if (context instanceof Activity)
        {
            this.actividad = (Activity) context;
        } else
        {
            throw new RuntimeException(context.toString());
        }
    }
}