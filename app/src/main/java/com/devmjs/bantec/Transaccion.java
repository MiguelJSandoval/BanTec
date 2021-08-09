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

public class Transaccion
{
    private String tipo, nombreDesti, noCDesti, nomRemi, fecha;
    private Double cantidad;

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getNombreDesti()
    {
        return nombreDesti;
    }

    public void setNombreDesti(String nombreDesti)
    {
        this.nombreDesti = nombreDesti;
    }

    public String getNoCDesti()
    {
        return noCDesti;
    }

    public void setNoCDesti(String noCDesti)
    {
        this.noCDesti = noCDesti;
    }

    public void setNomRemi(String nomRemi)
    {
        this.nomRemi = nomRemi;
    }

    public Double getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(Double cantidad)
    {
        this.cantidad = cantidad;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }
}