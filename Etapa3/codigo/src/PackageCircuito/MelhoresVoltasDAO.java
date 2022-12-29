package PackageCircuito;

import DAOCONFIG.DAOconfig;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class MelhoresVoltasDAO {
	public Circuito _unnamed_Circuito_;

	private static MelhoresVoltasDAO instance = null;

	private MelhoresVoltasDAO()
		{
			try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
				 Statement stm = conn.createStatement()) {
				String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`MelhoresVoltas` (" +
								"id INT NOT NULL AUTO_INCREMENT," +
								"PRIMARY KEY (`id`))";
				stm.executeUpdate(sql);

// MelhoresVoltas(id,circuito,piloto,tempo)

				sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`MelhoresVoltas` (
      					`id` INT NOT NULL AUTO_INCREMENT,
      					`circuito` VARCHAR(50),
      					`piloto` VARCHAR(50),
						`tempo` INT NOT NULL,
      						PRIMARY KEY (`id`),
							FOREIGN KEY (`id`)
      						REFERENCES `simuladorDSS`.`Circuito` (`id`))""";
			stm.executeUpdate(sql);
	
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NullPointerException(e.getMessage());
			}
		}

	
	public static MelhoresVoltasDAO getInstace()
	{
		if(instance == null)
			instance = new MelhoresVoltasDAO();
		return instance;
	}

}
