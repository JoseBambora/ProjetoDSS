package PackageCampeonato;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CampeonatoDAO implements Map<String,Campeonato> {
	
	
	private CampeonatoDAO(){
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			Statement stm = conn.createStatement()){
				String sql = "CREATE TABLE IF NOT EXISTTS `simuladorDSS`.`Campeonato` (" +
				"nome VARCHAR(75)," + 
				"PRIMARY KEY (`nome`))";
			stm.executeUpdate(sql);

			sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Campeonato` (
					`nome` VARCHAR(75)
					`disponivel` TINYINT
						PRIMARY KEY (`nome`),
					)
					""";
		}
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

	@Override
	public Campeonato get(Object key) {
		return null;
	}

	@Override
	public Campeonato put(String key, Campeonato value) {
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
		return null;
	}

	@Override
	public Collection<Campeonato> values() {
		return null;
	}

	@Override
	public Set<Entry<String, Campeonato>> entrySet() {
		return null;
	}
}