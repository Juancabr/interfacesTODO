/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @class crea usuarios en nuestra base de datos o carga, muestra o banea a los
 * usuarios de nuestra base de datos
 * @author Rafael Vegas
 * @date 02/12/2018
 */
public class BaseDeDatos {

    //Atributos
    private Statement st;
    private ResultSet datos;
    private Connection c;
    private PreparedStatement ps;

    //Constructores
    public BaseDeDatos(Usuario usuario) {
    }
    
    public BaseDeDatos(Producto producto) {
        
    }

    public BaseDeDatos() {
    }

    /**
     * @function la base de datos de usuarios que está en MySQL
     * @return devuelve la conexión a la base de datos
     */
    private Statement cargarBD() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/usuarios", "root", "admin");
            st = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    /**
     * @function cierra el Statement y la conexión que esta abierta
     */
    private void finalizar() {
        try {
            st.close();
            c.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR cerrando la conexión de la base de datos");
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @function agrega usuarios a la base de datos
     * @param usuario el usuario al que se le van agregar los valores
     */
    public void InsertarUsuarios(Usuario usuario) {
        try {
            st = cargarBD();
            st.executeUpdate("INSERT INTO usuario(nombre,contrasena,email,banear) VALUES ('" + usuario.getNombre()
                    + "','" + usuario.getContrasena() + "','" + usuario.getEmail() + "'," + usuario.isBanear() + ")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
        } finally {
            finalizar();
        }
    }
    
    /*public void InsertarProductos(Producto producto) {
        try {
            st = cargarBD();
            st.executeUpdate("INSERT INTO usuario(nombre,contrasena,email,banear) VALUES ('" + usuario.getNombre()
                    + "','" + usuario.getContrasena() + "','" + usuario.getEmail() + "'," + usuario.isBanear() + ")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
        } finally {
            finalizar();
        }
    }*/

    /**
     * @function crea una tabla para mostrar todos los usuarios que hay en la
     * base de datos diciendole que datos tiene la tabla y que nombre le debe
     * dar a cada columna para identificar sus valores
     * @return devuelve la tabla que se ha creado
     */
    public DefaultTableModel mostrarUsuarios() {
        DefaultTableModel tabla = null;
        try {
            tabla = new DefaultTableModel();
            int numLineas = tabla.getRowCount();
            tabla.setNumRows(numLineas);
            tabla.setColumnIdentifiers(new Object[]{"Nombre", "Contraseña", "EMAIL", "Baneado"});
            try {
                st = cargarBD();
                datos = st.executeQuery("select nombre,email,contrasena,banear from usuario");
            } catch (SQLException ex) {
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (datos.next()) {
                tabla.addRow(new Object[]{datos.getString("nombre"), datos.getString("contrasena"),
                    datos.getString("email"), datos.getBoolean("banear")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            finalizar();
        }
        return tabla;
    }

    /**
     * @function comprueba si el correo que se le pasa por parámetros existe en
     * la base de datos de usuarios
     * @param correo el correo que debe comprobar para poder iniciar sesión
     * @param contrasena para comprobar si coincide con el correo en la base de
     * datos
     * @return true si el correo existe en la base de datos y false si no existe
     */
    public boolean iniciarUsuario(String correo, String contrasena) {
        try {
            st = cargarBD();
            datos = st.executeQuery("select email, contrasena from usuario");
            while (datos.next()) {
                if (correo.equals(datos.getString("email")) && contrasena.equals(datos.getString("contrasena"))) {
                    finalizar();
                    return true;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
        } finally {
            finalizar();
        }
        return false;
    }

    /**
     * @function comprueba si el correo está en la base de datos y si es así
     * banea al dicho usuario poniendo true en la columna de banear.
     * @param correo
     */
    public void banearUsuario(String correo) {
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/usuarios", "root", "admin");
            String modificar = "UPDATE usuario  set banear=? WHERE email=?";
            ps = c.prepareStatement(modificar);
            ps.setBoolean(1, true);
            ps.setString(2, correo);
            int num = ps.executeUpdate();
            if (num > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Baneado");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR, usuario no existe");
            }
        } catch (SQLException ex) {
            System.out.println("Error modificando");
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                System.out.println("ERROR cerrando");
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public DefaultTableModel mostrarProductos() {
        DefaultTableModel tabla = null;

        try {
            tabla = new DefaultTableModel();
            int nLineas = tabla.getRowCount();
            tabla.setNumRows(nLineas);//("Id", "Nombre", "Precio")
            tabla.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Precio"});
            st = cargarBD();
            datos = st.executeQuery("select * from producto");
            while (datos.next()) {
                tabla.addRow(new Object[]{datos.getInt("idProducto"), datos.getString("nombre"), datos.getDouble("precio")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            finalizar();
        }

        return tabla;
    }

    public void insertarCompra(int idProducto, String email) {
        int id = 0;
        LocalDateTime ldt = LocalDateTime.now();
        Timestamp t = Timestamp.valueOf(ldt);
        LocalDateTime ldt2 = t.toLocalDateTime();
        System.out.println(email);
        System.out.println(ldt);
        System.out.println(t);
        System.out.println(ldt2);
        try {
            st = cargarBD();
            st.executeUpdate("INSERT INTO compras VALUES ('" + id
                    + "','" + email + "','" + idProducto + "'," + ldt2 + ")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar compra");
        } finally {
            finalizar();
        }
    }
    
    public boolean comprobarProducto(String producto) {
        try {
            double precioProducto = 0;
            st = cargarBD();
            datos = st.executeQuery("select producto from usuario");
            while (datos.next()) {
                if (producto.equals(datos.getString("producto"))) {
                    finalizar();
                    precioProducto = datos.getDouble("precio");
                    return true;
                }
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
        } finally {
            finalizar();
        }
            return false;
        }
            
    public void aplicarOferta(String producto, double precioRebajado){
        try {
            c = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/usuarios", "root", "admin");
            String modificar = "UPDATE producto set precio=? WHERE nombre=?";
            ps = c.prepareStatement(modificar);
            ps.setDouble(1, precioRebajado);
            ps.setString(2, producto);
            int num = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            finalizar();
        }
    }
    
        //MÃ©todo para quitar el baneo de los usuarios:
      public void QuitarBaneoUsuario(String correo) {
        try {            
            c = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/usuarios", "root", "admin");
            String modificar = "UPDATE usuario  set banear=? WHERE email=?";
            ps = c.prepareStatement(modificar);
            //posible error
            ps.setBoolean(1, false);
            ps.setString(2, correo);
            int num = ps.executeUpdate();
            if(num>0) JOptionPane.showMessageDialog(null, "Usuario Desbaneado :D");
            else JOptionPane.showMessageDialog(null, "ERROR, usuario no existe");
        } catch (SQLException ex) {
            System.out.println("Error modificando");
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
                ps.close();
            } catch (SQLException ex) {
                System.out.println("ERROR cerrando");
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
