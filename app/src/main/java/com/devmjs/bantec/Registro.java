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

public class Registro
{
    private String usuario;
    private String contrasenia;
    private String noCuenta;
    private double saldo;
    private String cv2;
    private String noTarjeta;

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public double getSaldo()
    {
        return saldo;
    }

    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }

    public String getNoCuenta()
    {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta)
    {
        this.noCuenta = noCuenta;
    }

    public String getCV2()
    {
        return cv2;
    }

    public void setCV2(String cv2)
    {
        this.cv2 = cv2;
    }

    public String getNoTarjeta()
    {
        return noTarjeta;
    }

    public void setNoTarjeta(String noTarjeta)
    {
        this.noTarjeta = noTarjeta;
    }
}
