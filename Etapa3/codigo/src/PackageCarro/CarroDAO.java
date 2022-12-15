package PackageCarro;

import DAOCONFIG.DAOconfig;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;


public class CarroDAO implements Map<String,Carro> {
	private static CarroDAO instance = null;
	private CarroDAO()
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`ModoMotor` (" +
					"id INT NOT NULL AUTO_INCREMENT," +
					"PRIMARY KEY (`id`))";
			stm.executeUpdate(sql);
			sql = """
				CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Motor` (
      				`id` INT NOT NULL AUTO_INCREMENT,
      				`potencia` INT NOT NULL,
      				`capacidadeCombustivel` INT NOT NULL,
      				`modo` INT NOT NULL,
      					PRIMARY KEY (`id`),
      					FOREIGN KEY (`modo`)
      					REFERENCES `simuladorDSS`.`ModoMotor` (`id`))""";
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Normal` (
       				  `modo` INT NOT NULL,
             			  PRIMARY KEY (`modo`),
             			  FOREIGN KEY (`modo`)
             			  REFERENCES `simuladorDSS`.`ModoMotor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Conservador` (
       				  `modo` INT NOT NULL,
             			  PRIMARY KEY (`modo`),
             			  FOREIGN KEY (`modo`)
             			  REFERENCES `simuladorDSS`.`ModoMotor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Agressivo` (
       				  `modo` INT NOT NULL,
             			  PRIMARY KEY (`modo`),
             			  FOREIGN KEY (`modo`)
             			  REFERENCES `simuladorDSS`.`ModoMotor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Categoria` (
       				  `id` INT NOT NULL AUTO_INCREMENT,
       				  `fiabilidade` INT NOT NULL,
             			  PRIMARY KEY (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`C1` (
       				  `categoria` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`Categoria` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`C2` (
       				  `categoria` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`Categoria` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`GT` (
       				  `categoria` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`Categoria` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`SC` (
       				  `categoria` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`Categoria` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`C1H` (
       				  `categoria` INT NOT NULL,
       				  `motor` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`C1` (`categoria`),
             			  FOREIGN KEY (`motor`)
             			  REFERENCES `simuladorDSS`.`Motor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`C2H` (
       				  `categoria` INT NOT NULL,
       				  `motor` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`C2` (`categoria`),
             			  FOREIGN KEY (`motor`)
             			  REFERENCES `simuladorDSS`.`Motor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`GTH` (
       				  `categoria` INT NOT NULL,
       				  `motor` INT NOT NULL,
             			  PRIMARY KEY (`categoria`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`GT` (`categoria`),
             			  FOREIGN KEY (`motor`)
             			  REFERENCES `simuladorDSS`.`Motor` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Pneu` (
       				  `id` INT NOT NULL AUTO_INCREMENT,
             			  PRIMARY KEY (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Duro` (
       				  `pneu` INT NOT NULL,
             			  PRIMARY KEY (`pneu`),
             			  FOREIGN KEY (`pneu`)
             			  REFERENCES `simuladorDSS`.`Pneu` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Chuva` (
       				  `pneu` INT NOT NULL,
             			  PRIMARY KEY (`pneu`),
             			  FOREIGN KEY (`pneu`)
             			  REFERENCES `simuladorDSS`.`Pneu` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Macio` (
       				  `pneu` INT NOT NULL,
             			  PRIMARY KEY (`pneu`),
             			  FOREIGN KEY (`pneu`)
             			  REFERENCES `simuladorDSS`.`Pneu` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Carro` (
       				  `marca` VARCHAR(50) NOT NULL,
       				  `modelo` VARCHAR(50) NOT NULL,
       				  `pac` FLOAT NOT NULL,
       				  `cilindrada` INT NOT NULL,
       				  `motor` INT NOT NULL,
       				  `pneu` INT NOT NULL,
       				  `categoria` INT NOT NULL,
             			  PRIMARY KEY (`marca`,`modelo`),
             			  FOREIGN KEY (`motor`)
             			  REFERENCES `simuladorDSS`.`Motor` (`id`),
             			  FOREIGN KEY (`pneu`)
             			  REFERENCES `simuladorDSS`.`Pneu` (`id`),
             			  FOREIGN KEY (`categoria`)
             			  REFERENCES `simuladorDSS`.`Categoria` (`id`)
       				  )
				  """;
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
	}
	public static CarroDAO getInstace()
	{
		if(instance == null)
			instance = new CarroDAO();
		return instance;
	}
	@Override
	public int size() {
		int res = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			String sql = "SELECT COUNT(*) FROM Carro";
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
	public boolean containsKey(Object key)
	{
		boolean res = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			String []keys = Carro.getMarcaModelo((String) key);
			String marca = keys[0];
			String modelo = keys[1];
			String sql = "SELECT COUNT(*) FROM Carro WHERE marca = ? AND modelo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,marca);
			ps.setString(2,modelo);
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
	public boolean containsValue(Object value)
	{
		Carro carro = (Carro) value;
		return this.containsKey(carro.generateKey());
	}

	private Categoria getCategoria(int id) throws SQLException
	{
		Categoria r = null;
		List<String> list = new ArrayList<>();
		list.add("C1");
		list.add("C2");
		list.add("GT");
		list.add("SC");
		list.add("C1H");
		list.add("C2H");
		list.add("GTH");
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			int i = 0;
			for (String cat : list)
			{
				String sql = "SELECT * FROM " + cat  + " WHERE categoria = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					switch (i)
					{
						case 0 -> r = new C1(rs.getInt("categoria"));
						case 1 -> r = new C2(rs.getInt("categoria"));
						case 2 -> r = new GT(rs.getInt("categoria"));
						case 3 -> r = new SC(rs.getInt("categoria"));
						case 4 -> r = new C1Hibrido(rs.getInt("categoria"), new MotorElétrico(getMotor(rs.getInt("motor"))));
						case 5 -> r = new C2Hibrido(rs.getInt("categoria"), new MotorElétrico(getMotor(rs.getInt("motor"))));
						case 6 -> r = new GTHibrido_(rs.getInt("categoria"), new MotorElétrico(getMotor(rs.getInt("motor"))));
					}
					break;
				}
				i++;
			}
		}
		return r;
	}

	private Pneu getPneus(int id) throws SQLException
	{
		Pneu r = null;
		List<String> list = new ArrayList<>();
		list.add("Macio");
		list.add("Duro");
		list.add("Chuva");
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			int i = 0;
			for (String cat : list)
			{
				String sql = "SELECT * FROM " + cat + " WHERE pneu = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,cat);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					switch (i)
					{
						case 0 -> r = new Macio();
						case 1 -> r = new Duro();
						case 2 -> r = new Chuva();
					}
					break;
				}
				i++;
			}
		}
		return r;
	}
	private ModoMotor getModo(int id) throws SQLException
	{
		ModoMotor r = null;
		List<String> list = new ArrayList<>();
		list.add("Agressivo");
		list.add("Conservador");
		list.add("Normal");;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			int i = 0;
			for (String cat : list)
			{
				String sql = "SELECT * FROM " + cat + " WHERE modo = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					switch (i)
					{
						case 0 -> r = new Agressivo();
						case 1 -> r = new Conservador();
						case 2 -> r = new Normal();
					}
					break;
				}
				i++;
			}
		}
		return r;

	}
	private Motor getMotor(int id) throws SQLException
	{
		Motor r = null;
		String sql = "SELECT * FROM Motor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ps.setInt(1,id);
			 ResultSet rs = ps.executeQuery();
			 int potencia = rs.getInt("potencia");
			 int capacidade = rs.getInt("capacidadeCombustivel");
			 int modo = rs.getInt("modo");
			 ModoMotor modoMotor = this.getModo(modo);
			 r = new Motor(potencia,capacidade,modoMotor);
		}
		return r;
	}
	@Override
	public Carro get(Object key) {
		String []str = Carro.getMarcaModelo((String) key);
		String marca = str[0];
		String modelo = str[1];
		Carro r = null;
		int cat = 0, cil =0 , pneu = 0, eng = 0;
		float pac = 0;
		boolean aux = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {

			String sql = "SELECT * FROM Carro WHERE marca = ? AND modelo = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,marca);
			ps.setString(2,modelo);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				marca = rs.getString("marca");
				modelo = rs.getString("modelo");
				cat = rs.getInt("categoria");
				pac = rs.getFloat("pac");
				cil = rs.getInt("cilindrada");
				pneu = rs.getInt("pneu");
				eng = rs.getInt("motor");
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
				Pneu pneus = getPneus(pneu);
				MotorCombustao motor = new MotorCombustao(eng,cil);
				Categoria c = this.getCategoria(cat);
				r = new Carro(marca,modelo,pac,pneus,motor,c);
			}catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		}
		return r;
	}
	private int getLastId(PreparedStatement statement) throws SQLException
	{
		int id = 0;
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next())
			id=rs.getInt(1);
		return id;
	}
	private int insertModo(ModoMotor modoMotor)
	{
		int id = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)){
			String sql = "INSERT INTO ModoMotor VALUES()";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			id = this.getLastId(ps);
			if (modoMotor instanceof Agressivo)
				sql = "INSERT INTO Agressivo VALUES (" + id + ")";
			else if (modoMotor instanceof Conservador)
				sql = "INSERT INTO Conservador VALUES (" + id + ")";
			else
				sql = "INSERT INTO Normal VALUES (" + id + ")";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return id;
	}

	private int insertPneu(IConjuntoPneus pneus)
	{
		int id = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "INSERT INTO Pneu VALUES()";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			id = this.getLastId(ps);
			if (pneus instanceof Macio)
				sql = "INSERT INTO Macio VALUES (" + id + ")";
			else if (pneus instanceof Duro)
				sql = "INSERT INTO Duro VALUES (" + id + ")";
			else
				sql = "INSERT INTO Chuva VALUES (" + id + ")";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return id;
	}

	private int insertMotor(Motor motor)
	{
		int id = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
			int modo = this.insertModo(motor.get_modo());
			String sql = "INSERT INTO Motor (potencia, capacidadeCombustivel, modo) VALUES ( ?, ? , ?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,motor.get_potencia());
			ps.setInt(2,motor.get_capacidadeCombustivel());
			ps.setInt(3,modo);
			ps.executeUpdate();
			id = this.getLastId(ps);
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return id;
	}

	private int insertCategoria(Categoria categoria)
	{
		int id = 0;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "INSERT INTO Categoria (fiabilidade) VALUES(?)";
			PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,categoria.get_fiabilidade());
			ps.executeUpdate();
			id = this.getLastId(ps);
			if (categoria instanceof C1)
			{
				sql = "INSERT INTO C1 VALUES (" + id + ")";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				if (categoria instanceof C1Hibrido)
				{
					int car = this.insertMotor(((C1Hibrido) categoria).get_motorEletrico());
					sql = "INSERT INTO C1H VALUES (" + id + ','+ car + ")";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
			else if (categoria instanceof C2)
			{
				sql = "INSERT INTO C2 VALUES (" + id + ")";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				if (categoria instanceof C2Hibrido)
				{
					int car = this.insertMotor(((C2Hibrido) categoria).get_motorEletrico());
					sql = "INSERT INTO C2H VALUES (" + id + ','+ car + ")";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
			else if (categoria instanceof GT)
			{
				sql = "INSERT INTO GT VALUES (" + id + ")";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				if (categoria instanceof GTHibrido_)
				{
					int car = this.insertMotor(((GTHibrido_) categoria).get_motorEletrico());
					sql = "INSERT INTO GTH VALUES (" + id + ','+ car + ")";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
			else if (categoria instanceof SC)
			{
				sql = "INSERT INTO SC VALUES (" + id + ")";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return id;
	}

	private void insertCarro(Carro carro)
	{
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);)
		{
			int motor = this.insertMotor(carro.get_motor());
			int pneu = this.insertPneu(carro.get_unnamed_IConjuntoPneus_());
			int categoria = this.insertCategoria(carro.get_unnamed_Categoria_());
			String sql = "INSERT INTO Carro ()VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,carro.get_marca());
			ps.setString(2,carro.get_modelo());
			ps.setFloat(3,carro.get_pac());
			ps.setInt(4,carro.get_cilindrada());
			ps.setInt(5,motor);
			ps.setInt(6,pneu);
			ps.setInt(7,categoria);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}

	}
	public void generateData()
	{
		if(this.isEmpty())
		{
			System.out.println("entrou");
			List<ModoMotor> modos = new ArrayList<>();
			modos.add(new Agressivo());
			modos.add(new Normal());
			modos.add(new Normal());
			modos.add(new Conservador());
			List<Motor> motores = new ArrayList<>();
			motores.add(new MotorCombustao(525,60,modos.get(0),3000));
			motores.add(new MotorCombustao(600,60,modos.get(2),3000));
			motores.add(new MotorCombustao(400,60,modos.get(3),3000));
			motores.add(new MotorCombustao(300,60,modos.get(1),3000));
			motores.add(new MotorElétrico(300,60,modos.get(2)));
			motores.add(new MotorElétrico(300,60,modos.get(1)));
			List<Pneu> pneus = new ArrayList<>();
			pneus.add(new Chuva());
			pneus.add(new Macio());
			pneus.add(new Duro());
			List<Categoria> categorias = new ArrayList<>();
			categorias.add(new C1(95));
			categorias.add(new C2(99));
			categorias.add(new GT(88));
			categorias.add(new SC(79));
			categorias.add(new C1Hibrido(80,(MotorElétrico) motores.get(4)));
			categorias.add(new C2Hibrido(85,(MotorElétrico) motores.get(5)));
			categorias.add(new GTHibrido_(90,(MotorElétrico) motores.get(4)));
			List<Carro> carros = new ArrayList<>();
			carros.add(new Carro("Porshe", "GT3RS",2,pneus.get(1),(MotorCombustao) motores.get(0),categorias.get(0)));
			carros.add(new Carro("Ferrari", "Enzo",2,pneus.get(0),(MotorCombustao) motores.get(1),categorias.get(1)));
			carros.add(new Carro("Audi", "R8",3,pneus.get(1),(MotorCombustao) motores.get(2),categorias.get(2)));
			carros.add(new Carro("Honda", "Type-R",4,pneus.get(2),(MotorCombustao) motores.get(3),categorias.get(3)));
			carros.add(new Carro("Toyota", "Supra",5,pneus.get(1),(MotorCombustao) motores.get(2),categorias.get(4)));
			carros.add(new Carro("Mercedes", "AMG",6,pneus.get(2),(MotorCombustao) motores.get(1),categorias.get(5)));
			carros.add(new Carro("BMW","M4",0.5f,pneus.get(0),(MotorCombustao) motores.get(3),categorias.get(3)));
			carros.add(new Carro("Ford","GT",0.5f,pneus.get(0),(MotorCombustao) motores.get(3),categorias.get(3)));
			carros.forEach(this::insertCarro);
		}
	}

	@Override
	public Carro put(String key, Carro value)
	{
		this.insertCarro(value);
		return value;
	}

	@Override
	public Carro remove(Object key)
	{
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Carro> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet()
	{
		Set<String> result = new HashSet<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
			String sql = "SELECT * FROM Carro";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				result.add(marca + "," + modelo);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Carro> values() {
		Collection<Carro> result = new ArrayList<>();
		Set<String> keyset = this.keySet();
		for (String key : keyset)
		{
			Carro carro = this.get(key);
			result.add(carro);
		}
		return result;
	}

	@Override
	public Set<Entry<String, Carro>> entrySet() {
		Set<String> keyset = this.keySet();
		Set<Entry<String,Carro>> result = new HashSet<>();
		for(String key : keyset)
		{
			Carro carro = this.get(key);
			result.add(Map.entry(key,carro));

		}
		return result;
	}
}