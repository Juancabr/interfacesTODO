/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 * @class crea o carga los datos de un usuario en nuestra base de datos
 * @author Rafael Vegas
 * @date 02/12/2018
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private String email;
    private boolean banear=false;
    private boolean empresa;

   
    

    //CONSTRUCTORES
    public Usuario(String nombre, String contrasena, String email, boolean emp) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;   
        this.empresa=emp;
    }

    public Usuario() {
    }

    //TODO corregir, no se puede dejar sin implementar.
    public Usuario(String toString, String clave, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //GETTERS Y SETTERS    
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
     * @param contrasena the contrasena to set
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the banear
     */
    public boolean isBanear() {
        return banear;
    }

    /**
     * @param banear the banear to set
     */
    public void setBanear(boolean banear) {
        this.banear = banear;
    }
    
     public boolean isEmpresa() {
        return empresa;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    } 
}
