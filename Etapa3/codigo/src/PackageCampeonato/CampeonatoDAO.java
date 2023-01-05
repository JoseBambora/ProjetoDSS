package PackageCampeonato;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import DAOCONFIG.DAOconfig;

public class CampeonatoDAO implements Map<String,Campeonato> {
	private static CampeonatoDAO instance = null;
	
	
	private CampeonatoDAO() throws NullPointerException{
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			Statement stm = conn.createStatement()){
				String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Campeonato` (" +
				"nome VARCHAR(75) NOT NULL," +
				"disponivel TINYINT,"+
				"PRIMARY KEY (`nome`))";
			stm.executeUpdate(sql);
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public static CampeonatoDAO getInstance(){
		if(instance == null)
			instance = new CampeonatoDAO();
		return instance;
	}

	@Override
	public int size() {
		int r = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
		Statement stm = conn.createStatement()){
			String sql = "SELECT COUNT(*) FROM Campeonato";
			ResultSet answerMessage = stm.executeQuery(sql);
			if(answerMessage.next())
				r = answerMessage.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return r;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String sql = "SELECT * FROM Campeonato WHERE nome = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,(String)key);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());

		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public Campeonato get(Object key) {
		String nome = (String) key;
		boolean disp = false;
		Campeonato r = null;
		boolean aux = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
			String sql = "SELECT * FROM Campeonato WHERE nome = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                disp = rs.getBoolean("disponivel");
				aux = true;
            }
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		if (aux)
			r = new Campeonato(nome, disp);
		return r;
	}

	public void insertCampeonato(Campeonato value) {
		String nome = value.get_nome();
		boolean disp = value.get_disponivel();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String sql = "INSERT INTO Campeonato VALUES ( ? , ? )";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1,nome);
			preparedStatement.setBoolean(2,disp);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public void generateData(){
		if(this.isEmpty())
		{
			List<Campeonato> campeonatos = new ArrayList<>();
			campeonatos.add(new Campeonato("campeonato1",true));
			campeonatos.add(new Campeonato("campeonato2",true));
			campeonatos.add(new Campeonato("campeonato3",true));
			campeonatos.add(new Campeonato("Campeonato nacional de rally",false));
			campeonatos.add(new Campeonato("Rampa da falperra",true));
			campeonatos.add(new Campeonato("Formula 1",true));
			campeonatos.forEach(this::insertCampeonato);
		}
	}

	public Campeonato put(String key, Campeonato value) {
		this.insertCampeonato(value);
        return null;
	}


	@Override
	public Campeonato remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Campeonato> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		Set<String> r = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Campeonato";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String nome = rs.getString("nome");
				r.add(nome);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return r;
	}

	@Override
	public Collection<Campeonato> values() {
		Collection<Campeonato> campeonatos = new ArrayList<>();
        Set<String> keyset = this.keySet();
        for (String key : keyset)
        {
            Campeonato campeonato = this.get(key);
            campeonatos.add(campeonato);
        }
        return campeonatos;
	}

	@Override
	public Set<Entry<String, Campeonato>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Campeonato>> result = new HashSet<>();
		for(String key : keyset)
		{
			Campeonato circ = this.get(key);
			result.add(Map.entry(key,circ));
		}
		return result;
	}
}