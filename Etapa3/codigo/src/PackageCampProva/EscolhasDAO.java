package PackageCampProva;

import DAOCONFIG.DAOconfig;
import PackageCarro.Carro;
import PackageCarro.CarroDAO;
import PackageCarro.IConjuntoPneus;
import PackageCarro.ModoMotor;
import PackagePiloto.PilotoDAO;
import PackageUtilizador.Jogador;
import PackageUtilizador.Utilizador;
import PackageUtilizador.UtilizadoresDAO;

import java.sql.*;
import java.util.*;

public class EscolhasDAO implements Map<String,Escolha> {

	private static EscolhasDAO instance = null;

	public static EscolhasDAO getInstance(){
		if (instance == null)
			instance = new EscolhasDAO();
		return instance;
	}

	private EscolhasDAO(){
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()){
			String sql = """
					CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Escolhas`(
						`campeonatoProva` INT NOT NULL,
						`nomeJogador` VARCHAR(75) NOT NULL,
						`piloto` INT NOT NULL,
						`marca` VARCHAR(50) NOT NULL,
						`modelo` VARCHAR(50) NOT NULL,
						`pac` FLOAT NOT NULL,
						`pneus` INT NOT NULL,
						`modo` INT NOT NULL,
						FOREIGN KEY (`campeonatoProva`)
						REFERENCES `simuladorDSS`.`CampeonatoProva` (`id`),
						FOREIGN KEY (`piloto`)
						REFERENCES `simuladorDSS`.`Piloto` (`id`),
						FOREIGN KEY (marca,modelo)
					    REFERENCES simuladorDSS.Carro (marca,modelo),
						FOREIGN KEY (`pneus`)
						REFERENCES `simuladorDSS`.`Pneu` (`id`),
						FOREIGN KEY (`modo`)
						REFERENCES `simuladorDSS`.`ModoMotor` (`id`),
						PRIMARY KEY (`campeonatoProva`,`nomeJogador`))
					""";
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public void guardaAfinacao(String aJogador, float aPac, ModoMotor aModo, IConjuntoPneus aPneus) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		int res = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String sql = "SELECT COUNT(*) FROM Escolhas";
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
		return this.size()==0;
	}

	public String generateKey(int idCampProva, String usernameJogador){
		StringBuilder sb = new StringBuilder();
		sb.append(idCampProva).append(",").append(usernameJogador);
		return sb.toString();
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
			String sql = "SELECT COUNT(*) FROM Escolhas WHERE campeonatoProva = ? AND nomeJogador = ?";
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
	public List<String> initSimulacao(int camp)
	{
		List<String>  init = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Escolhas WHERE campeonatoProva = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,camp);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				String nome = rs.getString("nomeJogador");
				init.add(nome);
			}
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return init;
	}
	@Override
	public boolean containsValue(Object value) {
		return this.containsKey(value);
	}

	@Override
	public Escolha get(Object key) {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			Escolha res = new Escolha();
			String[] pk = ClassificacoesDAO.getCampProvaUsername((String) key);
			String sql = "SELECT * FROM Escolhas WHERE campeonatoProva = ? AND nomeJogador = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(pk[0]));
			ps.setString(2,pk[1]);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res.set_jogador(pk[1]);
				res.set_piloto(PilotoDAO.getInstace().get(rs.getInt("piloto")));
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				res.set_carro(CarroDAO.getInstace().get(Carro.getMarcaModelo(marca+modelo)));
				res.set_pac(rs.getFloat("pac"));
				res.set_pneu(CarroDAO.getInstace().getPneus(rs.getInt("pneus")));
				res.set_modo(CarroDAO.getInstace().getModo(rs.getInt("modo")));
			}
			return res;
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	public void insertEscolha(String key, Escolha value) {
		String[] pk = getCampProvaUsername(key);
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "INSERT INTO Escolhas (campeonatoProva,nomeJogador,piloto,marca,modelo,pac,pneus,modo) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(pk[0]));
			ps.setString(2, pk[1]);
			ps.setInt(3,PilotoDAO.getInstace().getID(value.get_piloto()));
			ps.setString(4,value.get_carro().get_marca());
			ps.setString(5,value.get_carro().get_modelo());
			ps.setFloat(6,value.get_pac());
			ps.setInt(7,CarroDAO.getInstace().getID(value.get_pneu()));
			ps.setInt(8,CarroDAO.getInstace().getID(value.get_modo()));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public Escolha put(String key, Escolha value) {
		this.insertEscolha(key,value);
		return value;
	}

	@Override
	public Escolha remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Escolha> m) {
		Set<?extends String> keys = m.keySet();
		for(String key: keys){
			this.put(key,m.get(key));
		}
	}

	@Override
	public void clear() {
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
			String sql = "DELETE * FROM Escolhas";
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
			String sql = "SELECT * FROM Escolhas";
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
	public Collection<Escolha> values() {
		Collection<Escolha> classificacoes = new ArrayList<>();
		Set<String> keyset = this.keySet();
		for (String key : keyset)
		{
			classificacoes.add(this.get(key));
		}
		return classificacoes;
	}

	@Override
	public Set<Entry<String, Escolha>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Escolha>> result = new HashSet<>();
		for(String key : keyset){
			result.add(Map.entry(key,this.get(key)));
		}
		return result;
	}
}