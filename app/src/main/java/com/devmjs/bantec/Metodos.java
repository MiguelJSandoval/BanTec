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

public class Metodos
{

    public String generaNo()
    {
        StringBuilder no = new StringBuilder("060");
        for (int i = 0; i < 15; i++)
        {
            int n = (int) (Math.random() * 10);
            String nn = String.valueOf(n);
            no.append(nn);
        }
        return no.toString();
    }

    public String generaNoTarjeta()
    {
        StringBuilder no = new StringBuilder("4000");
        for (int i = 0; i < 12; i++)
        {
            int n = (int) (Math.random() * 10);
            String nn = String.valueOf(n);
            no.append(nn);
        }
        return no.toString();
    }

    public String generaCV2()
    {
        StringBuilder no = new StringBuilder();
        for (int i = 0; i < 3; i++)
        {
            int n = (int) (Math.random() * 10);
            String nn = String.valueOf(n);
            no.append(nn);
        }
        return no.toString();
    }
}
