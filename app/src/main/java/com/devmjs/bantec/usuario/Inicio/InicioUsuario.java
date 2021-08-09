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

package com.devmjs.bantec.usuario.Inicio;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Registro;
import com.devmjs.bantec.UsuActivity;

public class InicioUsuario extends Fragment
{
    private TextView txtSaldo;
    private TextView txtNoTarjeta;
    private TextView txtCV2;
    private ImageButton imageTarjeta;
    private ImageButton anverRever;
    private boolean ban = true, ban2 = true;
    private String dinero;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_iniciousuario, container, false);
        String nombre = UsuActivity.nombre;
        GestorBaseDatos bd = new GestorBaseDatos(getContext());
        Registro usuarioRegistro = bd.consultaRegistroUsuario(nombre);
        TextView txtUsuario = root.findViewById(R.id.txt_usu_ini);
        txtUsuario.setText("Su banca: " + nombre);
        TextView txtNoCuenta = root.findViewById(R.id.txt_NoCuenta);
        txtNoCuenta.setText("No. Cuenta: " + usuarioRegistro.getNoCuenta());
        txtSaldo = root.findViewById(R.id.textSaldo);
        ImageButton ver = root.findViewById(R.id.verSaldo);
        dinero = "$" + usuarioRegistro.getSaldo();
        txtSaldo.setText(dinero);
        ver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ban)
                {
                    String ast = "**************";
                    txtSaldo.setText(ast);
                    ban = false;
                } else
                {
                    txtSaldo.setText(dinero);
                    ban = true;
                }
            }
        });
        imageTarjeta = root.findViewById(R.id.tarjeta);
        anverRever = root.findViewById(R.id.reversoAnver);
        txtNoTarjeta = root.findViewById(R.id.txtNoTarjet);
        String t = usuarioRegistro.getNoTarjeta();
        String nnn = "" + t.charAt(0) + t.charAt(1) + t.charAt(2) + t.charAt(3) + "  "
                + t.charAt(4) + t.charAt(5) + t.charAt(6) + t.charAt(7) + "  "
                + t.charAt(8) + t.charAt(9) + t.charAt(10) + t.charAt(11) + "  "
                + t.charAt(12) + t.charAt(13) + t.charAt(14) + t.charAt(15);
        txtNoTarjeta.setText(nnn);
        txtCV2 = root.findViewById(R.id.txtCV2);
        txtCV2.setText(usuarioRegistro.getCV2());
        txtCV2.setVisibility(View.INVISIBLE);
        anverRever.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ban2)
                {
                    ban2 = false;
                    imageTarjeta.setImageResource(R.drawable.tarjreverso);
                    txtCV2.setVisibility(View.VISIBLE);
                    txtNoTarjeta.setVisibility(View.INVISIBLE);
                    anverRever.setImageResource(R.drawable.veranverso);
                } else
                {
                    ban2 = true;
                    imageTarjeta.setImageResource(R.drawable.image1);
                    txtCV2.setVisibility(View.INVISIBLE);
                    txtNoTarjeta.setVisibility(View.VISIBLE);
                    anverRever.setImageResource(R.drawable.verreverso);
                }
            }
        });
        return root;
    }
}