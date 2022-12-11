package PackageCarro;

import DAOCONFIG.DAOconfig;
import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class CarroDAO implements Map<String,Carro> {
	private CarroDAO()
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "CREATE TABLE IF NOT EXISTS salas (" +
					"Num varchar(10) NOT NULL PRIMARY KEY," +
					"Edificio varchar(45) DEFAULT NULL," +
					"Capacidade int(4) DEFAULT 0)";
			stm.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS turmas (" +
					"Id varchar(10) NOT NULL PRIMARY KEY," +
					"Sala varchar(10) DEFAULT NULL," +
					"foreign key(Sala) references salas(Num))";
			stm.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS alunos (" +
					"Num varchar(10) NOT NULL PRIMARY KEY," +
					"Nome varchar(45) DEFAULT NULL," +
					"Email varchar(45) DEFAULT NULL," +
					"Turma varchar(10), foreign key(Turma) references turmas(Id))";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}
	/*
	"CREATE SCHEMA IF NOT EXISTS `simuladorDSS` ;
	USE `simuladorDSS` ;

	CREATE TABLE IF NOT EXISTS `simuladorDSS`.`ModoMotor` (
			`id` INT NOT NULL,
	PRIMARY KEY (`id`))
	ENGINE = InnoDB;

	CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Motor` (
			`id` INT NOT NULL,
			`potencia` INT NOT NULL,
			`capacidadeCombustivel` INT NOT NULL,
			`modo` INT NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `modo_idx` (`modo` ASC) VISIBLE,
	CONSTRAINT `modo`
	FOREIGN KEY (`modo`)
	REFERENCES `simuladorDSS`.`ModoMotor` (`id`)"
	 */
	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public Carro get(Object key) {
		return null;
	}

	@Override
	public Carro put(String key, Carro value) {
		return null;
	}

	@Override
	public Carro remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Carro> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<Carro> values() {
		return null;
	}

	@Override
	public Set<Entry<String, Carro>> entrySet() {
		return null;
	}
}