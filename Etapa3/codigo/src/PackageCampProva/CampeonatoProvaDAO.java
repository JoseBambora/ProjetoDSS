package PackageCampProva;

import DAOCONFIG.*;
import PackageUtilizador.UtilizadoresDAO;

import java.sql.*;
import java.util.*;

public class CampeonatoProvaDAO implements Map<String,CampeonatoProva> {

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
						`id` INT NOT NULL,
						`campeonato` VARCHAR(75),
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
		CampeonatoProva campeonatoProva = (CampeonatoProva) value;
		boolean res = false;
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM Utilizador WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,(campeonatoProva.get_id()));
			ps.setString(2,(campeonatoProva.get));
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
	public CampeonatoProva get(Object key) {
		return null;
	}

	@Override
	public CampeonatoProva put(String key, CampeonatoProva value) {
		return null;
	}

	@Override
	public CampeonatoProva remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends CampeonatoProva> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<CampeonatoProva> values() {
		return null;
	}

	@Override
	public Set<Entry<String, CampeonatoProva>> entrySet() {
		return null;
	}
}