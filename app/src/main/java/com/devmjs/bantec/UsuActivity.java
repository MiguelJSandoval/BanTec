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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UsuActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private int cont = 0;
    public static String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu);
        Toolbar toolbar = findViewById(R.id.toolbarusu);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout_usu);
        NavigationView navigationView = findViewById(R.id.nav_view_usu);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_usu, R.id.ingresardinero, R.id.transferirdinero,
                R.id.infoUsu, R.id.nav_tarjeta)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_usu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        nombre = getIntent().getStringExtra("user");
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int op = item.getItemId();
        if (op == R.id.cerrarapp)
        {
            Toast.makeText(this, "¡Hasta pronto!", Toast.LENGTH_SHORT).show();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_usu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs)
    {
        try
        {
            assert parent != null;
            TextView usuario = (TextView) parent.findViewById(R.id.txtUsuario);
            usuario.setText(nombre);
        } catch (Exception ignored)
        {
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
            cont = 0;
        } else
        {
            if (cont == 0)
            {
                Toast.makeText(this, "Presione nuevamente para salir", Toast.LENGTH_LONG).show();
                cont++;
            } else
            {
                System.exit(0);
            }
            new CountDownTimer(3000, 1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                }

                @Override
                public void onFinish()
                {
                    cont = 0;
                }
            }.start();
        }
    }
}
