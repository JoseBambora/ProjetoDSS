package PackageCircuito;

import PackageCampeonato.Campeonato;
import DAOCONFIG.DAOconfig;
import PackageCarro.Chuva;
import PackageCarro.Duro;
import PackageCarro.Macio;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class CircuitoDAO implements Map<String,Circuito> {
	private static CircuitoDAO instance = null;
	public Campeonato _unnamed_Campeonato_;



	private CircuitoDAO()
		{
			try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 	 Statement stm = conn.createStatement()) {

// Caracteristica(id,circuito,gdu)
				String sql = """
				  	CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Circuito` (
					`nome` VARCHAR(50) NOT NULL,
					`distancia` INT NOT NULL,
					`voltas` INT NOT NULL,
					`campeonato` VARCHAR(75),
						PRIMARY KEY (`nome`),
						FOREIGN KEY (`campeonato`)
						REFERENCES `simuladorDSS`.`Campeonato` (nome))""";
				stm.executeUpdate(sql);
				sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Caracteristica` (
      					`id` INT NOT NULL AUTO_INCREMENT,
      					`circuito` VARCHAR(50) NOT NULL,
      					`gdu` INT NOT NULL,
      						PRIMARY KEY (`id`),
							FOREIGN KEY (`circuito`)
      						REFERENCES `simuladorDSS`.`Circuito` (nome))""";
				stm.executeUpdate(sql);
				sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Chicane` (
      					`id` INT NOT NULL ,
      						PRIMARY KEY (`id`),
							FOREIGN KEY (`id`)
      						REFERENCES `simuladorDSS`.`Caracteristica` (id))""";
				stm.executeUpdate(sql);
				sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Reta` (
      					`id` INT NOT NULL ,
      						PRIMARY KEY (`id`),
							FOREIGN KEY (`id`)
      						REFERENCES `simuladorDSS`.`Caracteristica` (id))""";
				stm.executeUpdate(sql);
				sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Curva` (
      					`id` INT NOT NULL ,
      						PRIMARY KEY (`id`),
							FOREIGN KEY (`id`)
      						REFERENCES `simuladorDSS`.`Caracteristica` (id))""";
				stm.executeUpdate(sql);
// Circuito(nome,distancia,voltas, campeonato)


// Chicane, Reta e Curva?
	
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NullPointerException(e.getMessage());
			}
		}

	
	public static CircuitoDAO getInstace()
	{
		if(instance == null)
			instance = new CircuitoDAO();
		return instance;
	}

	public void atualizaCircuito(Circuito aProva) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		int res = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String sql = "SELECT COUNT(*) FROM Circuito";
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next())
			{
				res = rs.getInt(1);
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
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		boolean res = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM Circuito WHERE nome = ?";
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

// Circuito(nome,distancia,voltas, campeonato)

	@Override
	public boolean containsValue(Object value) {
		Circuito circ = (Circuito) value;
        boolean res = false;
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
        {
            String sql = "SELECT COUNT(*) FROM Piloto WHERE nome = ? and sva = ? AND cts = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,circ.get_Dist());
            ps.setInt(2,circ.get_Voltas());
			ps.setString(3,circ.get_Campeonato());
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
	public Caracteristica getCaracteristica(int id, int gdu) {
		Caracteristica r = null;
		List<String> list = new ArrayList<>();
		list.add("Reta");
		list.add("Chicane");
		list.add("Curva");
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			int i = 0;
			for (String cat : list)
			{
				String sql = "SELECT * FROM " + cat + " WHERE id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					switch (i)
					{
						case 0 -> r = new Reta(gdu);
						case 1 -> r = new Chicane(gdu);
						case 2 -> r = new Curva(gdu);
					}
					break;
				}
				i++;
			}
		}
		catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return r;
	}
	public List<Caracteristica> getCaracteristicas(String name)
	{
		List<Caracteristica> res = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); ){
			String sql = "SELECT * FROM Caracteristica WHERE circuito = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				res.add(getCaracteristica(rs.getInt("id"),rs.getInt("gdu")));
			}
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return res;
	}

	@Override
	public Circuito get(Object key) {
		String nome = (String) key;
		int distancia =  0;
        int voltas = 0;
		Circuito r = null;
		String campeonato = "";
		boolean aux = false;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD); ){
			String sql = "SELECT * FROM Circuito WHERE nome = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                distancia = rs.getInt("distancia");
				voltas = rs.getInt("voltas");
                campeonato = rs.getString("campeonato");
				aux = true;
            }
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		if (aux)
		{
			r = new Circuito(nome,distancia,voltas,campeonato,getCaracteristicas(nome));
		}
		return r;
	}


	private void insertCaracteristica(Caracteristica caracteristica, String circuito)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "INSERT INTO Caracteristica (circuito,gdu) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,circuito);
			ps.setInt(2,caracteristica.get_gdu());
			ps.executeUpdate();
			int id = 0;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next())
				id=rs.getInt(1);
			if(caracteristica instanceof Reta)
				sql = "INSERT INTO Reta VALUES (" + id + ")";
			else if(caracteristica instanceof Curva)
				sql = "INSERT INTO Curva VALUES (" + id + ")";
			else if(caracteristica instanceof Chicane)
				sql = "INSERT INTO Chicane VALUES (" + id + ")";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}
	private void insertCircuito(Circuito circ)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String nome = circ.get_nome();
			int dist = circ.get_Dist();
			int voltas = circ.get_Voltas();
			String camp = circ.get_Campeonato();
			String sql = "INSERT INTO Circuito VALUES ( ? , ?, ?, ? )";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			preparedStatement.setInt(2,dist);
			preparedStatement.setInt(3,voltas);
			preparedStatement.setString(4,camp);
			preparedStatement.executeUpdate();
			circ.get_caracteristica().forEach(c -> insertCaracteristica(c,circ.get_nome()));
			System.out.println("Circuito: " + circ + " adicionado");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}

	}


	public void generateDataCircuito()
	{
		if(this.isEmpty())
		{
			List<Caracteristica> list = new ArrayList<>();
			list.add(new Reta(3));
			list.add(new Curva(1));
			list.add(new Reta(1));
			list.add(new Curva(2));
			list.add(new Reta(1));
			list.add(new Chicane(3));
			list.add(new Reta(1));
			list.add(new Curva(2));
			list.add(new Reta(1));
			List<Circuito> circuitos = new ArrayList<>();
			circuitos.add(new Circuito("Braga", 500 , 2, "campeonato1",list)); // isto tá mal
			circuitos.add(new Circuito("Lisboa", 730 , 5, "campeonato3",list)); // isto tá mal
			circuitos.add(new Circuito("Porto", 670 , 3, "campeonato1",list)); // isto tá mal
			circuitos.add(new Circuito("Portugal",2400,5,"Formula 1",list));
			circuitos.add(new Circuito("Espanha",1800,8,"Formula 1",list));
			circuitos.add(new Circuito("França",1900,6,"Formula 1",list));
			circuitos.add(new Circuito("Inglaterra",2300,7,"Formula 1",list));
			circuitos.add(new Circuito("China",2700,3,"Formula 1",list));
			circuitos.forEach(this::insertCircuito);
		}
	}

	public List<String> getCircuitosCampeonato(String campeonato)
	{
		Collection<Circuito> circuitos = this.values();
		return circuitos.stream().filter(c -> c.get_Campeonato().equals(campeonato)).map(Circuito::get_nome).toList();
	}

	@Override
	public Circuito put(String key, Circuito value) {
		this.insertCircuito(value);
        return null;
	}

	@Override
	public Circuito remove(Object key) {
		return null;
	}


	@Override
	public void putAll(Map<? extends String, ? extends Circuito> m) {

	}

	@Override
	public void clear() {

	}


	@Override
	public Set<String> keySet() {
		Set<String> result = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Circuito";
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
	public Collection<Circuito> values() {
		Collection<Circuito> circuitos = new ArrayList<>();
        Set<String> keyset = this.keySet();
        for (String key : keyset)
        {
            Circuito circ = this.get(key);
            circuitos.add(circ);
        }
        return circuitos;
	}

	@Override
	public Set<Entry<String, Circuito>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Circuito>> result = new HashSet<>();
		for(String key : keyset)
		{
			Circuito circ = this.get(key);
			result.add(Map.entry(key,circ));

		}
		return result;
	}
}
