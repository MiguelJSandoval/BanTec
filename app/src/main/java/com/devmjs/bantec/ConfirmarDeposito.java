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

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmarDeposito extends DialogFragment
{
    private EditText usu, contra;
    private Confirmacion listener;
    Registro r;

    public ConfirmarDeposito(Registro r)
    {
        this.r = r;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        return crearDialogo();
    }

    private AlertDialog crearDialogo()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_confirmar_deposito, null);
        builder.setView(v);
        usu = v.findViewById(R.id.usuConfirmDepo);
        contra = v.findViewById(R.id.passConfirmDepo);
        Button acept = v.findViewById(R.id.btn_aceptar_depo);
        Button cancel = v.findViewById(R.id.btn_cancel_depo);
        acept.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                String u = usu.getText().toString();
                String c = contra.getText().toString();
                if (u.length() > 0 && c.length() > 0)
                {
                    if (c.equals(r.getContrasenia()) && u.equals(r.getUsuario()))
                    {
                        dismiss();
                        listener.enConfirmacion(true);
                    } else
                    {
                        Toast.makeText(getContext(), "Usuario o contraseña incorrectos, intente nuevamente", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                dismiss();
                listener.enConfirmacion(false);
            }
        });
        return builder.create();
    }

    public void setListener(Confirmacion confirmacion)
    {
        this.listener = confirmacion;
    }
}
