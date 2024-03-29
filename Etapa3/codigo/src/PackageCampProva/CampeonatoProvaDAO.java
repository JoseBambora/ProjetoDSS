package PackageCampProva;

import DAOCONFIG.*;
import PackageCampeonato.Campeonato;
import PackageCampeonato.CampeonatoDAO;
import PackageUtilizador.Utilizador;
import PackageUtilizador.UtilizadoresDAO;

import java.sql.*;
import java.util.*;

public class CampeonatoProvaDAO implements Map<Integer,CampeonatoProva> {

	private static CampeonatoProvaDAO instance = null;

	public static CampeonatoProvaDAO getInstance(){
		if(instance == null)
			instance = new CampeonatoProvaDAO();
		return instance;
	}

	private CampeonatoProvaDAO() {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()){
			 String sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`CampeonatoProva`(
						`id` INT NOT NULL AUTO_INCREMENT,
						`campeonato` VARCHAR(75) NOT NULL,
						FOREIGN KEY (`campeonato`)
						REFERENCES `simuladorDSS`.`Campeonato` (`nome`),
						PRIMARY KEY (`id`))
					""";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public int size() {
		int res = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String sql = "SELECT COUNT(*) FROM CampeonatoProva";
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next())
			{
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM CampeonatoProva WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,(Integer) key);
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
		return this.containsKey(value);
	}

	@Override
	public CampeonatoProva get(Object key)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String nome = "";
			String sql = "SELECT * FROM CampeonatoProva WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int id = (Integer) key;
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				nome =  rs.getString("campeonato");
			}
			Campeonato c = CampeonatoDAO.getInstance().get(nome);
			return new CampeonatoProva(id,c);
		} catch (SQLException e) {
				// Erro a criar tabela...
				e.printStackTrace();
				throw new NullPointerException(e.getMessage());
		}
	}

	public int insertCampeonatoProva(CampeonatoProva value){
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String campeonato =value.getCampeonato().get_nome();
			String sql = "INSERT INTO CampeonatoProva (campeonato) VALUES (?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,campeonato);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
			value.set_id(id);
			return id;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public CampeonatoProva put(Integer key, CampeonatoProva value) {
		this.insertCampeonatoProva(value);
		return value;
	}

	@Override
	public CampeonatoProva remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends CampeonatoProva> m) {
		Set<?extends Integer> keys = m.keySet();
		for(Integer key: keys){
			CampeonatoProva campeonatoProva = m.get(key);
			this.put(key,campeonatoProva);
		}
	}

	@Override
	public void clear() {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql = "DELETE FROM CampeonatoProva";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public Set<Integer> keySet() {
		Set<Integer> result = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql = "SELECT * FROM CampeonatoProva";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int id = rs.getInt("id");
				result.add(id);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<CampeonatoProva> values() {
		Collection<CampeonatoProva> campeonatoProvas = new ArrayList<>();
		Set<Integer> keyset = this.keySet();
		for (Integer key : keyset)
		{
			CampeonatoProva campeonatoProva = this.get(Integer.valueOf(key));
			campeonatoProvas.add(campeonatoProva);
		}
		return campeonatoProvas;
	}

	@Override
	public Set<Entry<Integer, CampeonatoProva>> entrySet() {
		Set<Integer> keyset = this.keySet();
		Set<Entry<Integer,CampeonatoProva>> result = new HashSet<>();
		for(Integer key : keyset){
			CampeonatoProva campeonatoProva = this.get(Integer.valueOf(key));
			result.add(Map.entry(key,campeonatoProva));
		}
		return result;
	}
}