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

package com.devmjs.bantec.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.devmjs.bantec.GestorBaseDatos;
import com.devmjs.bantec.R;
import com.devmjs.bantec.Registro;
import com.devmjs.bantec.*;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment
{
    public ListView listaRegs;

    public HomeFragment()
    {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        GestorBaseDatos bd = new GestorBaseDatos(getContext());
        ArrayList<String> arr = bd.registroUsuarios();
        final ArrayList<Registro> regs = bd.consultaRegistros();
        listaRegs = root.findViewById(R.id.listaRegs);
        if (arr != null)
        {
            ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.layout_item_listview, arr);
            listaRegs.setAdapter(adapter);
        }

        listaRegs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                dialogtipofragment dd = new dialogtipofragment(regs.get(position));
                dd.setCancelable(false);
                dd.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "Dialogo tipo fragment");
            }
        });
        return root;
    }
}