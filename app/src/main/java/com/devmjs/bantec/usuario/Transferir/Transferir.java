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

package com.devmjs.bantec.usuario.Transferir;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.devmjs.bantec.Confirmacion;
import com.devmjs.bantec.ConfirmarTransferencia;
import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Registro;
import com.devmjs.bantec.UsuActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.Objects;

public class Transferir extends Fragment implements Confirmacion
{
    public TextInputEditText nombre, noCuenta, cantidad;
    public Context con;
    String df = "dd/MM/yyy";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat date = new SimpleDateFormat(df);
    Date fecha = new Date();
    String fechaAct = date.format(fecha);
    private Double cantATransfer = 0D, nuevoSaldo;
    private Registro r;
    private final String[] arr = new String[6];
    private final String[] tr = new String[6];
    private String nV;
    private GestorBaseDatos bd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_transferir, container, false);
        nombre = root.findViewById(R.id.txtNomDestin);
        noCuenta = root.findViewById(R.id.txtNoCuenDestin);
        cantidad = root.findViewById(R.id.txtCantidadDestin);
        Button transferir = root.findViewById(R.id.btn_transferir);
        con = getContext();
        transferir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean ban = false;
                String nomDestin = "";
                String noCDestin = "";
                try
                {
                    nomDestin = Objects.requireNonNull(nombre.getText()).toString();
                    noCDestin = Objects.requireNonNull(noCuenta.getText()).toString();
                    cantATransfer = Double.parseDouble(Objects.requireNonNull(cantidad.getText()).toString());
                    ban = nomDestin.length() != 0 && noCDestin.length() != 0
                            && cantidad.getText().toString().length() != 0;
                } catch (Exception ignored)
                {
                }
                if (ban)
                {
                    if (noCDestin.length() == 18 && nomDestin.length() >= 3 && nomDestin.length() <= 12)
                    {
                        if (cantATransfer > 0)
                        {
                            bd = new GestorBaseDatos(getContext());
                            r = bd.consultaRegistroUsuario(UsuActivity.nombre);
                            if (r.getSaldo() >= cantATransfer)
                            {
                                nuevoSaldo = r.getSaldo() - cantATransfer;
                                nV = String.valueOf(nuevoSaldo);
                                arr[0] = r.getNoCuenta();
                                arr[1] = r.getUsuario();
                                arr[2] = nV;
                                arr[3] = r.getContrasenia();
                                arr[4] = r.getNoTarjeta();
                                arr[5] = r.getCV2();
                                tr[0] = "Transferencia";
                                tr[1] = nomDestin;
                                tr[2] = noCDestin;
                                tr[3] = cantidad.getText().toString();
                                tr[4] = r.getUsuario();
                                tr[5] = fechaAct;
                                ConfirmarTransferencia ccc = new ConfirmarTransferencia(r);
                                ccc.setListener(Transferir.this);
                                ccc.setCancelable(false);
                                ccc.show(requireActivity().getSupportFragmentManager(), "Confirmar transferencia");
                            } else
                            {
                                Toast.makeText(getContext(), "No cuentas con saldo suficiente",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else
                        {
                            Toast.makeText(getContext(), "La cantidad a Transferir debe ser mayor a $0.00",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else
                    {
                        Toast.makeText(getContext(), "El número de cuenta debe tener 18 dígitos\n" +
                                        "y el nombre de usuario entre 3 y 12 caracteres",
                                Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(getContext(), "Debe llenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void enConfirmacion(boolean ban)
    {
        if (ban)
        {
            if (bd.modifica(arr, r.getUsuario()))
            {
                Toast.makeText(con, "Transferencia éxitosa", Toast.LENGTH_SHORT).show();
                if (bd.insertaTransaccion(tr))
                {
                    Toast.makeText(con, "Ocurrió un error " +
                                    "al guardar el registro de la transaccion",
                            Toast.LENGTH_SHORT).show();
                }
            } else
            {
                Toast.makeText(con, "No se pudo realiar la transferencia",
                        Toast.LENGTH_SHORT).show();
            }
        } else
        {
            Toast.makeText(con, "Transferencia cancelada", Toast.LENGTH_SHORT).show();
        }
        nombre.setText("");
        noCuenta.setText("");
        cantidad.setText("");
        nombre.requestFocus();
    }
}