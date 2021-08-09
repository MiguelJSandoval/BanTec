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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GestorBaseDatos
{
    public BaseDatos bd;
    private final Context con;

    public GestorBaseDatos(Context con)
    {
        this.con = con;
    }

    public boolean inserta(String[] arr)
    {
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("noCuenta", arr[0]);
            registro.put("username", arr[1]);
            registro.put("saldo", arr[2]);
            registro.put("password", arr[3]);
            registro.put("noTarjeta", arr[4]);
            registro.put("cv2", arr[5]);
            database.insert("registro", null, registro);
            bd.close();
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public boolean modifica(String[] arr, String nomAnt)
    {
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            String[] params = {nomAnt};
            ContentValues registro = new ContentValues();
            registro.put("noCuenta", arr[0]);
            registro.put("username", arr[1]);
            registro.put("saldo", arr[2]);
            registro.put("password", arr[3]);
            registro.put("noTarjeta", arr[4]);
            registro.put("cv2", arr[5]);
            if (database.update("registro", registro,
                    "username=?", params) == 1)
            {
                bd.close();
                return true;
            } else
            {
                bd.close();
                return false;
            }
        } catch (Exception e)
        {
            return false;
        }
    }

    public boolean eliminaRegistro(String name)
    {
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            String[] params = {name};
            return database.delete("registro", "username=?", params) == 1;
        } catch (Exception e)
        {
            return false;
        }
    }

    public ArrayList<Registro> consultaRegistros()
    {
        ArrayList<Registro> arr = new ArrayList<>();
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM registro ORDER BY username;", null);
            while (cursor.moveToNext())
            {
                Registro r = new Registro();
                r.setNoCuenta(cursor.getString(0));
                r.setUsuario(cursor.getString(1));
                r.setContrasenia(cursor.getString(2));
                r.setSaldo(cursor.getDouble(3));
                r.setNoTarjeta(cursor.getString(4));
                r.setCV2(cursor.getString(5));
                arr.add(r);
            }
            cursor.close();
            if (arr.size() != 0)
            {
                return arr;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public boolean exists(String name)
    {
        ArrayList<String> aux = registroUsuarios();
        if (aux != null)
        {
            for (int i = 0; i < aux.size(); i++)
            {
                if (aux.get(i).equals(name))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifUsuCont(String nom, String pass)
    {
        ArrayList<Registro> arr = consultaRegistros();
        if (arr != null)
        {
            for (int i = 0; i < arr.size(); i++)
            {
                if (arr.get(i).getUsuario().equals(nom) &&
                        arr.get(i).getContrasenia().equals(pass))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> registroUsuarios()
    {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Registro> aux = consultaRegistros();
        if (aux != null)
        {
            for (int i = 0; i < aux.size(); i++)
            {
                arr.add(aux.get(i).getUsuario());
            }
            return arr;
        } else
        {
            return null;
        }
    }

    public Registro consultaRegistroUsuario(String name)
    {
        ArrayList<Registro> aux = consultaRegistros();
        if (aux != null)
        {
            for (int i = 0; i < aux.size(); i++)
            {
                if (aux.get(i).getUsuario().equals(name))
                {
                    return aux.get(i);
                }
            }
        }
        return null;
    }

    public boolean insertaTransaccion(String[] arr)
    {
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("tipo", arr[0]);
            registro.put("nombreDestino", arr[1]);
            registro.put("noCDestino", arr[2]);
            registro.put("cantidad", arr[3]);
            registro.put("nombreRemi", arr[4]);
            registro.put("fecha", arr[5]);
            database.insert("Transacciones", null, registro);
            bd.close();
            return false;
        } catch (Exception e)
        {
            return true;
        }
    }

    public ArrayList<Transaccion> transacciones(String n)
    {
        ArrayList<Transaccion> arr = new ArrayList<>();
        String[] args = {n};
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM transacciones WHERE nombreRemi=?", args);
            while (cursor.moveToNext())
            {
                Transaccion t = new Transaccion();
                t.setTipo(cursor.getString(0));
                t.setNombreDesti(cursor.getString(1));
                t.setNoCDesti(cursor.getString(2));
                t.setCantidad(cursor.getDouble(3));
                t.setNomRemi(cursor.getString(4));
                t.setFecha(cursor.getString(5));
                arr.add(t);
            }
            cursor.close();
            if (arr.size() != 0)
            {
                return arr;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public void modificaTransaccion(String nomN, String nomAnt)
    {
        try
        {
            bd = new BaseDatos(con, "banco", null, 2);
            SQLiteDatabase database = bd.getReadableDatabase();
            String[] params = {nomAnt};
            ContentValues registro = new ContentValues();
            registro.put("nombreRemi", nomN);
            database.update("Transacciones", registro, "nombreRemi=?", params);
            bd.close();
            bd.close();
        } catch (Exception ignored)
        {
        }
    }
}