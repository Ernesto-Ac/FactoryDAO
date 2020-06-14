package DAO;

import Model.Categoria;
import java.util.List;
import Factory.ConexionBD;
import Factory.FactoryConexionDB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImplementar implements CategoriaDAO{
    ConexionBD conn; //Crear el objeto tipo conexion
    
    public CategoriaDAOImplementar(){
        //Definir a la base de datos que se conectara por defecto
        this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
    }
    
    @Override
    public List<Categoria> Listar() {
        StringBuilder miSQL = new StringBuilder();//Construir consulta
        miSQL.append("SELECT * FROM tb_categoria;");//Agregar consulta
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            //Se crear el objeto ResultSet implementando el metodo(consultaSQL) creado para la consulta
            ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());
            while(resultadoSQL.next()){
                Categoria categoria = new Categoria();//Declarar el objeto categoria
                //Asignar cada campo consultado al atributo en la clase
                categoria.setId_categoria(resultadoSQL.getInt("id_categoria"));
                categoria.setNom_categoria(resultadoSQL.getString("nom_categoria"));
                categoria.setEstado_categoria(resultadoSQL.getInt("estado_categoria"));
                lista.add(categoria);//Agregar al array cada registro encontrado
            }
        } catch (Exception ex) {
        }finally{
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean guardarCat(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean borrarCat(int id_cat_borrar) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
