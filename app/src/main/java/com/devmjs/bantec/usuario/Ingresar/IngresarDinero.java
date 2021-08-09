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

package com.devmjs.bantec.usuario.Ingresar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.devmjs.bantec.Confirmacion;
import com.devmjs.bantec.ConfirmarDeposito;
import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Registro;
import com.devmjs.bantec.UsuActivity;

import java.util.Date;

public class IngresarDinero extends Fragment implements Confirmacion
{
    private ImageButton cien, doscientos, quinientos, mil, cincomil, diezmil;
    private double seleccion;
    private Button continuar;
    private boolean ban = false;
    private final String df = "dd/MM/yyy";
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat date = new SimpleDateFormat(df);
    private final Date fecha = new Date();
    private final String fechaAct = date.format(fecha);
    private final String[] arr = new String[6];
    private Registro r;
    private Context con;
    private GestorBaseDatos bd;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ingresardinero, container, false);
        continuar = root.findViewById(R.id.continuar);
        continuar.setBackgroundResource(R.drawable.botongris);
        con = getContext();
        continuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ban)
                {
                    bd = new GestorBaseDatos(getContext());
                    r = bd.consultaRegistroUsuario(UsuActivity.nombre);
                    arr[0] = r.getNoCuenta();
                    arr[1] = r.getUsuario();
                    arr[2] = String.valueOf(r.getSaldo() + seleccion);
                    arr[3] = r.getContrasenia();
                    arr[4] = r.getNoTarjeta();
                    arr[5] = r.getCV2();

                    ConfirmarDeposito ccc = new ConfirmarDeposito(r);
                    ccc.setListener(IngresarDinero.this);
                    ccc.setCancelable(false);
                    ccc.show(requireActivity().getSupportFragmentManager(), "Confirmar deposito");
                } else
                {
                    Toast.makeText(getContext(), "Debe seleccionar alguna cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cien = root.findViewById(R.id.cien);
        doscientos = root.findViewById(R.id.doscientos);
        quinientos = root.findViewById(R.id.quinientos);
        mil = root.findViewById(R.id.mil);
        cincomil = root.findViewById(R.id.cincomil);
        diezmil = root.findViewById(R.id.diezmil);
        cien.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 100;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_selec);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            }
        });
        doscientos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 200;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_selec);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            }
        });
        quinientos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 500;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_selec);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            }
        });
        mil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 1000;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_selec);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            }
        });
        cincomil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 5000;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_selec);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            }
        });
        diezmil.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seleccion = 10000;
                ban = true;
                continuar.setBackgroundResource(R.drawable.botonrojo);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_selec);
            }
        });
        return root;
    }

    @Override
    public void enConfirmacion(boolean bans)
    {
        if (bans)
        {
            if (bd.modifica(arr, r.getUsuario()))
            {
                String[] tr = {"Deposito", "",
                        "", String.valueOf(seleccion),
                        r.getUsuario(), fechaAct};
                if (bd.insertaTransaccion(tr))
                {
                    Toast.makeText(con, "Ocurrió un error " +
                                    "al guardar el registro de la transaccion",
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(con, "Ingresó $" + seleccion, Toast.LENGTH_SHORT).show();
                continuar.setBackgroundResource(R.drawable.botongris);
                cien.setImageResource(R.drawable.cien_ini);
                doscientos.setImageResource(R.drawable.doscientos_ini);
                quinientos.setImageResource(R.drawable.quinientos_ini);
                mil.setImageResource(R.drawable.mil_ini);
                cincomil.setImageResource(R.drawable.cincomil_ini);
                diezmil.setImageResource(R.drawable.diezmil_ini);
            } else
            {
                Toast.makeText(con, "Ocurrió un error inesperado, intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        } else
        {
            Toast.makeText(con, "Deposito cancelado", Toast.LENGTH_SHORT).show();
            continuar.setBackgroundResource(R.drawable.botongris);
            cien.setImageResource(R.drawable.cien_ini);
            doscientos.setImageResource(R.drawable.doscientos_ini);
            quinientos.setImageResource(R.drawable.quinientos_ini);
            mil.setImageResource(R.drawable.mil_ini);
            cincomil.setImageResource(R.drawable.cincomil_ini);
            diezmil.setImageResource(R.drawable.diezmil_ini);
        }
        ban = false;
    }
}