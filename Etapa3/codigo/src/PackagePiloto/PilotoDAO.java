package PackagePiloto;

import DAOCONFIG.DAOconfig;
import java.sql.*;
import java.util.*;

public class PilotoDAO implements Map<String,Piloto> {

	private static PilotoDAO instance = null;
	private PilotoDAO()
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {

			String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`ModoMotor` (" +
						"id INT NOT NULL AUTO_INCREMENT," +
						"PRIMARY KEY (`id`))";
			stm.executeUpdate(sql);

			// Piloto(nome,sva,cts)
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Piloto` (
       				  `nome` VARCHAR(50) NOT NULL,
       				  `sva` INT NOT NULL,
       				  `cts` INT NOT NULL,
       				  )
				  """;
			stm.executeUpdate(sql);


		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public static PilotoDAO getInstace()
	{
		if(instance == null)
			instance = new PilotoDAO();
		return instance;
	}

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

	// Carro(marca,modelo,pac, cilindrada)



	@Override
	public Piloto get(Object key) {
		String []str = Piloto.getCondutor((String) key); // completar get
		String nome = str[0];
		Piloto r = null;
		float sva = 0;
		float cts =0;
		boolean aux = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {

			String sql = "SELECT * FROM Piloto WHERE nome = '" + nome +  "'";
			System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);

			if(rs.next())
			{
				nome = rs.getString(1);
				sva = rs.getFloat(2);
				cts = rs.getFloat(3);
				aux = true;
			}

		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		if (aux)
		{
			try {
				r = new Piloto(nome,sva,cts);
			}catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
			}
		}
		return r;
	}

 /* 
	private Nome getNome(String nome) throws SQLException
	{
		String filter = "nome";
		ResultSet rs = makeQuery("Nome",filter,nome);
		return new Nome(rs.getString(1));
	}
*/

	private void insertPiloto(Piloto piloto)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String nome = this.set_Nome(piloto.get_nome());
			String sql = "INSERT INTO Piloto ()VALUES (" + piloto.insertCommandPiloto() + ")";
			stm.executeUpdate(sql);
			System.out.println("Piloto: " + piloto + " adicionado");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}

	}



	public void generateDataPiloto()
	{
		List<Piloto> pilotos = new ArrayList<>();
		pilotos.add(new Piloto("Alexander Hamilton", 0.5, 0.2));
		pilotos.add(new Piloto("Scaramouche", 0.1, 0.6));
		pilotos.add(new Piloto("Sergio Perez", 0.9, 0.1));
		pilotos.add(new Piloto("George Russel", 0.2, 0.6));
		pilotos.add(new Piloto("Charles Leclerc", 0.4, 0.7));
		pilotos.add(new Piloto("Pierre Gasly", 0.7, 0.4));
		pilotos.forEach(this::insertPiloto);
	}

	@Override
	public Piloto put(String key, Piloto value) {
		return null;
	}

	@Override
	public Piloto remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Piloto> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<Piloto> values() {
		return null;
	}

	@Override
	public Set<Entry<String, Piloto>> entrySet() {
		return null;
	}
}
