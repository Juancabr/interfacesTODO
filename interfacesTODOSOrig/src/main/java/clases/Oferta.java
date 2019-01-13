/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.time.LocalDate;

/**
 *
 * @author 2DAM
 */
public class Oferta {
    private String producto;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private int porcentajeOferta;

    public Oferta(String producto, LocalDate fechaVencimiento, int porcentajeOferta) {
        this.producto = producto;
        this.fechaVencimiento = fechaVencimiento;
        this.porcentajeOferta = porcentajeOferta;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getPorcentajeOferta() {
        return porcentajeOferta;
    }

    public void setPorcentajeOferta(int porcentajeOferta) {
        this.porcentajeOferta = porcentajeOferta;
    }
    
    
}
