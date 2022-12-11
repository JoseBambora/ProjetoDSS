package DAOCONFIG;

public class DAOconfig {
    static final String USERNAME = "";                       // Actualizar
    static final String PASSWORD = "";                       // Actualizar
    private static final String DATABASE = "simuladorDSS";          // Actualizar
    //private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE+"?allowPublicKeyRetrieval=true&useSSL=false";
}
