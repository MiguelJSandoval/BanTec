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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper
{
    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE `registro` (" +
                "`noCuenta` varchar(18) NOT NULL," +
                "`username` varchar(50) NOT NULL," +
                "`password` varchar(50) NOT NULL," +
                "`saldo` decimal(32,10) NOT NULL DEFAULT 0.0000000000," +
                "`noTarjeta` varchar(16) DEFAULT NULL, " +
                "cv2 varchar(3) NOT NULL)");
        db.execSQL("CREATE TABLE `transacciones` (" +
                "`tipo` varchar(15) NOT NULL," +
                "`nombreDestino` varchar(50) DEFAULT NULL," +
                "`noCDestino` varchar(18) DEFAULT NULL," +
                "`cantidad` decimal(32,10) NOT NULL DEFAULT 0.0000000000," +
                "`nombreRemi` varchar(50) DEFAULT NULL," +
                "`fecha` varchar(10) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS registro");
        db.execSQL("DROP TABLE IF EXISTS transacciones");
        onCreate(db);
    }
}