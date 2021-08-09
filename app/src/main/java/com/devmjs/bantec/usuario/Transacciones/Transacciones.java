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

package com.devmjs.bantec.usuario.Transacciones;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.devmjs.bantec.DialogoDeposito;
import com.devmjs.bantec.DialogoTransaccion;
import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Transaccion;
import com.devmjs.bantec.UsuActivity;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class Transacciones extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_transacciones, container, false);
        GestorBaseDatos bd = new GestorBaseDatos(getContext());
        final ArrayList<Transaccion> regs = bd.transacciones(UsuActivity.nombre);
        ArrayList<String> listRegs = new ArrayList<>();
        ListView listaRegs = (ListView) root.findViewById(R.id.listaTransacciones);
        if (regs != null)
        {
            for (int i = 0; i < regs.size(); i++)
            {
                String title = (i + 1) + " - " + regs.get(i).getTipo();
                listRegs.add(title);
            }
            ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.layout_item_listview, listRegs);
            listaRegs.setAdapter(adapter);
        }
        listaRegs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String type = Objects.requireNonNull(regs).get(position).getTipo();
                if (type.equals("Transferencia"))
                {
                    String ids = String.valueOf((position + 1));
                    String nom = regs.get(position).getNombreDesti();
                    String noC = regs.get(position).getNoCDesti();
                    Double cant = regs.get(position).getCantidad();
                    String fech = regs.get(position).getFecha();
                    DialogoTransaccion dt = new DialogoTransaccion(ids, nom, noC, cant, fech);
                    dt.setCancelable(false);
                    dt.show(requireActivity().getSupportFragmentManager(), "Dialogo transferencia");
                } else
                {
                    String ids = String.valueOf((position + 1));
                    Double cant = regs.get(position).getCantidad();
                    String fech = regs.get(position).getFecha();
                    DialogoDeposito dt = new DialogoDeposito(ids, cant, fech);
                    dt.setCancelable(false);
                    dt.show(requireActivity().getSupportFragmentManager(), "Dialogo deposito");
                }
            }
        });
        return root;
    }
}