/*
 * Copyright (c) 2020 Charlie Condorcet Engineer Student.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 */

package cl.ucn.disc.pdis.fivet.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *  The Persona class.
 *
 * @author Charlie Condorcet.
 */
@DatabaseTable(tableName = "persona")
public final class Persona {

    /**
     * The id: Primary Key and autoincrement.
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The Nombre.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The Apellido.
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * The Rut.
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

    /**
     * Empty contructor; Default visivility + empty body.
     */
     public Persona() {
        // nothing here.
    }

    /**
     * Principal Constructor
     * @param nombre
     * @param apellido
     * @param rut
     */
    public Persona(String nombre, String apellido, String rut) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    /**
     * Getter to Id.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter to Nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter to Apellido.
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Getter to Rut.
     * @return rut
     */
    public String getRut() {
        return rut;
    }

}
