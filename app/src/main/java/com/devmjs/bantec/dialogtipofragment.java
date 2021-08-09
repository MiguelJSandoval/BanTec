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

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class dialogtipofragment extends DialogFragment
{
    Activity actividad;
    ImageButton cerrar, verNoVer;
    LinearLayout barraSuperior;
    TextView nombre, noCuenta, noTarjeta, saldo, contra;
    boolean ban = true;
    Registro r;

    public dialogtipofragment(Registro r)
    {
        this.r = r;
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
        View v = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(v);
        barraSuperior = v.findViewById(R.id.barraSuperiorDialog);
        cerrar = v.findViewById(R.id.botonAceptarDialog);
        cerrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        nombre = v.findViewById(R.id.nomUsuDialog);
        nombre.setText(r.getUsuario());
        noCuenta = v.findViewById(R.id.noCuentaDialog);
        noCuenta.setText(r.getNoCuenta());
        noTarjeta = v.findViewById(R.id.noTarjetaDialog);
        noTarjeta.setText(r.getNoTarjeta());
        saldo = v.findViewById(R.id.saldoDialog);
        saldo.setText("$" + r.getSaldo());
        contra = v.findViewById(R.id.contraDialog);
        contra.setText("**********");
        verNoVer = v.findViewById(R.id.verNoVerDialog);
        verNoVer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ban)
                {
                    ban = false;
                    contra.setText(r.getContrasenia());
                } else
                {
                    ban = true;
                    contra.setText("**********");
                }
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