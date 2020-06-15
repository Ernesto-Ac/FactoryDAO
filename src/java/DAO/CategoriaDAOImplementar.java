package DAO;

import Model.Categoria;
import Factory.ConexionBD;
import Factory.FactoryConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImplementar implements CategoriaDAO {

    ConexionBD conn; //Crear el objeto tipo conexion

    public CategoriaDAOImplementar() {
        //Definir a la base de datos que se conectara por defecto

    }

    @Override
    public List<Categoria> Listar() {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
        StringBuilder miSQL = new StringBuilder();//Construir consulta
        miSQL.append("SELECT * FROM tb_categoria;");//Agregar consulta
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            //Se crear el objeto ResultSet implementando el metodo(consultaSQL) creado para la consulta
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while (resultadoSQL.next()) {
                Categoria categoria = new Categoria();//Declarar el objeto categoria
                //Asignar cada campo consultado al atributo en la clase
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
                lista.add(categoria);//Agregar al array cada registro encontrado
            }
        } catch (SQLException ex) {
        } finally {
            this.conn.cerrarConexion();//Para cerrar la conexion
        }
        return lista;
    }

    @Override
    public List<Categoria> Listar2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Categoria editarCat(int id_cat_edit) {
        
        Categoria categoria = new Categoria();//Objeto categoria para devolver datos
        StringBuilder miSQL = new StringBuilder();//Construir la consulta
        //Agregar la consulta
        miSQL.append("SELECT * FROM tb_categoria WHERE id_categoria =").append(id_cat_edit);
        try {
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while (resultadoSQL.next()) {
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
            }
        } catch (SQLException ex) {

        } finally {
            this.conn.cerrarConexion();//Cerrar conexion
        }
        return categoria;
    }

    @Override
    public boolean guardarCat(Categoria categoria) {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);//Hacer la conexion
        boolean guarda = false;//Bandeja de resultado
        try {
            if (categoria.getId_categoria() ==0) {//Para cuando es una nueva categoria
                StringBuilder miSQL = new StringBuilder();
                //Agregar la consulta SQL; el id es autoincrementable
                miSQL.append("INSERT INTO tb_categoria (nom_categoria, estado_categoria) VALUES('");
                miSQL.append(categoria.getNom_categoria() + "', ").append(categoria.getEstado_categoria());
                miSQL.append(");");
                //  Invocar metodo para ejecutar la consulta
                this.conn.ejecutarSQL(miSQL.toString());
            }else if(categoria.getId_categoria() > 0){ //Actualizar, id_categoria mayores a 0
                StringBuilder miSQL = new StringBuilder();
                miSQL.append("UPDATE tb_categoria SET id_categoria = ").append(categoria.getId_categoria());
                miSQL.append(", nom_categoria = '").append(categoria.getNom_categoria());
                miSQL.append("', estado_categoria = ").append(categoria.getEstado_categoria());
                miSQL.append(" WHERE id_categoria = ").append(categoria.getId_categoria()).append(";");
                //Invocar metodo para ejecutar la consulta
                this.conn.ejecutarSQL(miSQL.toString());
            }
            guarda = true;
        } catch (Exception e) {
        }finally{
            this.conn.cerrarConexion();
        }
        return guarda;
    }

    @Override
    public boolean borrarCat(int id_cat_borrar) {
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);//Hacer la conexion
        boolean borra = false; //Bandera de resultado
        try {
            StringBuilder miSQL = new StringBuilder();
            miSQL.append("DELETE FROM tb_categoria WHERE id_categoria = ").append(id_cat_borrar);
            this.conn.ejecutarSQL(miSQL.toString());
            borra = true;
        } catch (Exception e) {
        }finally{
            this.conn.cerrarConexion();//Cerrar la conexion
        }
        return borra;
    }

}
