package PackageCampProva;

import DAOCONFIG.DAOconfig;
import PackageCampeonato.Campeonato;
import PackageCampeonato.CampeonatoDAO;
import PackageUtilizador.Utilizador;

import java.sql.*;
import java.util.*;

public class ClassificacoesDAO implements Map<String,Integer> {

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
						`nomeJogador` VARCHAR(75) NOT NULL,
						`pontuacao` INT NOT NULL,
						FOREIGN KEY (`campeonatoProva`)
						REFERENCES `simuladorDSS`.`CampeonatoProva` (`id`),
						PRIMARY KEY (`campeonatoProva`,`nomeJogador`))
					""";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public void addPontuacao(int id, String nomeJogador, int incremento){
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql= "UPDATE Classificacoes SET pontuacao = ? WHERE campeonatoProva = ? AND nomeJogador = ?";
			PreparedStatement ps;
			String pk = this.generateKey(id,nomeJogador);
			int pontos = this.get(pk);
			ps = conn.prepareStatement(sql);
			ps.setInt(1,incremento+pontos);
			ps.setInt(2,id);
			ps.setString(3,nomeJogador);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

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
		StringBuilder sb = new StringBuilder();
		sb.append(idCampProva).append(',').append(usernameJogador);
		return sb.toString();
	}

	public static String[] getCampProvaUsername(String key){
		return key.split(",");
	}

	@Override
	public boolean containsKey(Object key) {
		String[] idcpUser = getCampProvaUsername((String) key);
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
			String sql = "SELECT * FROM Classificacoes WHERE campeonatoProva = ? AND nomeJogador = ?";
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
		String[] pk = getCampProvaUsername(key);
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
			String sql = "DELETE FROM Classificacoes";
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
	public Map<String,Integer> getClassificacoes(int campeonato)
	{
		Map<String,Integer> res = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Classificacoes WHERE campeonatoProva = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,campeonato);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String jogador = rs.getString("nomeJogador");
				int pontuacao = rs.getInt("pontuacao");
				res.put(jogador,pontuacao);
			}
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return res;
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