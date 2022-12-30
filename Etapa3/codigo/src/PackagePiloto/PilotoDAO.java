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

			String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Piloto` (" +
						"id INT NOT NULL AUTO_INCREMENT, " +
						"nome VARCHAR(50) NOT NULL, " +
						"sva INT NOT NULL, "+
						"cts INT NOT NULL, " +
						"PRIMARY KEY (`id`))";
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
		int res = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            String sql = "SELECT COUNT(*) FROM Piloto";
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next())
            {
                res = rs.getInt(1);
            }
        } catch (SQLException e) 
		{
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
        }
        return res;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}


	@Override
	public boolean containsKey(Object key) {
		boolean res = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM Piloto WHERE nome = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,(String)key);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res = rs.getInt(1) > 0;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());

		}
		return res;
	}

	@Override
	public boolean containsValue(Object value) {
		Piloto driver = (Piloto) value;
        boolean res = false;
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
        {
            String sql = "SELECT COUNT(*) FROM Piloto WHERE name = ? sva = ? AND cts = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, driver.get_nome());
            ps.setFloat(2,(driver.get_SVA()));
            ps.setFloat(3,(driver.get_CTS()));
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                res = true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());

        }
        return res;
	}


	@Override
	public Piloto get(Object key) {
		String []str = Piloto.getCondutor((String) key); // completar get
		String nome = str[0];
		Piloto r = null;
		float sva = 0;
		float cts =0;
		boolean aux = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Piloto WHERE nome = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				nome = rs.getString("nome");
				sva = rs.getFloat("sva");
				cts = rs.getFloat("cts");
				aux = true;
			}

		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		if (aux)
		{
			r = new Piloto(nome,sva,cts);
		}
		return r;
	}


	private void insertPiloto(Piloto piloto)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String nome = piloto.get_nome();
			Float sva = piloto.get_SVA();
			Float cts = piloto.get_CTS();
			String sql = "INSERT INTO Piloto (nome,sva,cts) VALUES ( ? , ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			preparedStatement.setFloat(2,sva);
			preparedStatement.setFloat(3,cts);
			preparedStatement.executeUpdate();
			System.out.println("Piloto: " + piloto + " adicionado");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}

	}



	public void generateDataPiloto()
	{
		if(this.isEmpty())
		{
			List<Piloto> pilotos = new ArrayList<>();
			pilotos.add(new Piloto("Alexander Hamilton", 0.5, 0.2));
			pilotos.add(new Piloto("Scaramouche", 0.1, 0.6));
			pilotos.add(new Piloto("Murphy Jurtrudes", 0.9, 0.1));
			pilotos.add(new Piloto("Raiden Russel", 0.2, 0.6));
			pilotos.add(new Piloto("Gary Leclerc", 0.4, 0.7));
			pilotos.add(new Piloto("Kiko Gasly", 0.7, 0.4));
			pilotos.forEach(this::insertPiloto);
		}
	}

	@Override
	public Piloto put(String key, Piloto value) {
		this.insertPiloto(value);
        return null;
	}

	public int getID(Piloto driver)
	{
		int res = 0;
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM Piloto WHERE name = ? sva = ? AND cts = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, driver.get_nome());
			ps.setFloat(2,(driver.get_SVA()));
			ps.setFloat(3,(driver.get_CTS()));
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res = rs.getInt("id");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return res;
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
		Set<String> result = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Piloto";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String nome = rs.getString("nome");
				result.add(nome);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Piloto> values() {
		Collection<Piloto> drivers = new ArrayList<>();
        Set<String> keyset = this.keySet();
        for (String key : keyset)
        {
            Piloto driver = this.get(key);
            drivers.add(driver);
        }
        return drivers;
	}

	@Override
	public Set<Entry<String, Piloto>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Piloto>> result = new HashSet<>();
		for(String key : keyset)
		{
			Piloto driver = this.get(key);
			result.add(Map.entry(key,driver));

		}
		return result;
	}
}