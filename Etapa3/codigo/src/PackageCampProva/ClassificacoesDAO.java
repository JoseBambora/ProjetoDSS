package PackageCampProva;

import DAOCONFIG.DAOconfig;
import PackageCampeonato.Campeonato;
import PackageCampeonato.CampeonatoDAO;

import java.sql.*;
import java.util.*;

public class ClassificacoesDAO implements Map<String,Integer> {
	public CampeonatoProva _unnamed_CampeonatoProva_;

	private static ClassificacoesDAO instance = null;

	public static ClassificacoesDAO getInstance(){
		if (instance == null)
			instance = new ClassificacoesDAO();
		return instance;
	}

	private ClassificacoesDAO(){
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()){
			String sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Classificacoes`(
						`campeonatoProva` INT NOT NULL,
						`nomeJogador` VARCHAR(75),
						`pontuacao` INT,
						FOREIGN KEY (`campeonatoProva`)
						REFERENCES `simuladorDSS`.`CampeonatoProva` (`id`),
						FOREIGN KEY (`nomeJogador`)
						REFERENCES `simuladorDSS`.`Jogador` (`username`),
						PRIMARY KEY (`campeonatoProva`,`nomeJogador`))
					""";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}


//Classificacoes(campeonatoProva, nomeJogador, pontuacao)
	public void atualizaClassificacoes(Map<String, Integer> aClassificacoes) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		int res = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String sql = "SELECT COUNT(*) FROM Classificacoes";
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

	public String generateKey(int idCampProva, String usernameJogador){
		return idCampProva +','+ usernameJogador;
	}

	public static String[] getCampProvaUsername(String key){
		return key.split(",");
	}

	@Override
	public boolean containsKey(Object key) {
		String[] idcpUser = ClassificacoesDAO.getCampProvaUsername((String) key);
		boolean res = false;
		try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
		{
			String sql = "SELECT COUNT(*) FROM Classificacoes WHERE campeonatoProva = ? AND nomeJogador = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(idcpUser[0]));
			ps.setString(2,idcpUser[1]);
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
	public Integer get(Object key) {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			int res = 0;
			String[] pk = ClassificacoesDAO.getCampProvaUsername((String) key);
			String sql = "SELECT COUNT(*) FROM Classificacoes WHERE campeonatoProva = ? AND nomeJogador = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(pk[0]));
			ps.setString(2,pk[1]);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res = rs.getInt("pontuacao");
			}

			return res;
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public void insertClassificacao(String key, Integer value) {
		String[] pk = ClassificacoesDAO.getCampProvaUsername(key);
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "INSERT INTO Classificacoes (campeonatoProva,nomeJogador,pontuacao) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(pk[0]));
			ps.setString(2, pk[1]);
			ps.setInt(3, value);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}
	@Override
	public Integer put(String key, Integer value) {
		 this.insertClassificacao(key,value);
		return value;
	}

	@Override
	public Integer remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		Set<?extends String> keys = m.keySet();
		for(String key: keys){
			this.put(key,m.get(key));
		}
	}

	@Override
	public void clear() {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql = "DELETE * FROM Classificacoes";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public Set<String> keySet() {
		Set<String> result = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql = "SELECT * FROM Classificacoes";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				int campeonatoProva = rs.getInt("campeonatoProva");
				String idUser = rs.getString("nomeJogador");
				String pk = generateKey(campeonatoProva,idUser);
				result.add(pk);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Integer> values() {
		Collection<Integer> classificacoes = new ArrayList<>();
		Set<String> keyset = this.keySet();
		for (String key : keyset)
		{
			classificacoes.add(this.get(key));
		}
		return classificacoes;
	}

	@Override
	public Set<Entry<String, Integer>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Integer>> result = new HashSet<>();
		for(String key : keyset){
			result.add(Map.entry(key,this.get(key)));
		}
		return result;
	}
}