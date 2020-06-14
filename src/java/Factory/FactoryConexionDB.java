package Factory;


public class FactoryConexionDB {
    //podeos definir la configuracion para las diferentes BD
    public static final int MySQL =1; //Para conectar a MySQL
    public static String[] confiMySQL = {"bd_inventario" , "root", "LOSPERICOS2018"};
    
    public static ConexionBD open(int tipoBD){
        switch(tipoBD){
            case FactoryConexionDB.MySQL:
            return new MySQLConexionFactory(confiMySQL);
            default:
                return null;
        }
    }
}
