package Factory;

import  java.sql.*;

public abstract class ConexionBD {
    protected String[] parametros;//Arreglo que recibe los parametros de la conexion
    protected Connection conexion;
    //El siguiente metodo abstracto no se implementa solamente se declara, 
    //Implementera en la subclase
    abstract Connection open();//Metodo abstracto que devuelve un objeto connection
    
    //Metodo para las consultas
public ResultSet consultaSQL(String consulta){
    Statement st;
    ResultSet rs = null;//Tabla temporal donde se almacenan los datos
    try {
        st = conexion.createStatement();
        rs = st.executeQuery(consulta);//Se ejecuta la consulta
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
 return rs;
}

//Metodo ejecutar
public boolean ejecutarSQL(String consulta){
    Statement st;//Objeto Statement es el encargado de ejecutar las consultas
    boolean guardar = true;
    try {
        st = conexion.createStatement();
        st.executeUpdate(consulta);//se ejecuta la consulta
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return guardar;
}

//Cerrar la conexion
public boolean cerrarConexion(){
    boolean ok = true;
    try {
        conexion.close();
    } catch (SQLException ex) {
      ex.printStackTrace();  
    }
    return ok;
}

}


