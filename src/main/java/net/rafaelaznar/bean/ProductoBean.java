/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.rafaelaznar.bean;

/**
 *
 * @author Pedro Benito
 */
public class ProductoBean {
    private int id;
    private String codigo, descripcion, precio;
    private int id_tipoproducto;

    public ProductoBean() {

    }

    public ProductoBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getId_tipoproducto() {
        return id_tipoproducto;
    }

    public void setId_tipoproducto(int id_tipoproducto) {
        this.id_tipoproducto = id_tipoproducto;
    }
}
