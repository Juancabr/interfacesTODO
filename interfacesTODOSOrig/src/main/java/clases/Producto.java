/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author 2DAM
 */
public class Producto {
    
    private Usuario usuario;
    private int idProducto;
    private String nombre;
    private double precio;
   
//CONSTRUCTORRES
    
 public Producto(int idProducto, String nombre, double precio, Usuario usuario){
     
     this.usuario=usuario;
    
     this.idProducto=idProducto;
     this.nombre=nombre;
     this.precio=precio;
     
     
     
 }
    public Producto(){
        
    }
 
  //GETTERS Y SETTERS 

    /**
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
   
  
    
}
