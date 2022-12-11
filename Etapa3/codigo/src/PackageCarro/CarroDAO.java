package PackageCarro;

import DAOCONFIG.DAOconfig;
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
			// Carro(marca,modelo,categoria,pac, cilindrada, motor,pneus)

			sql = """
				  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Carro` (
       				  `marca` VARCHAR(50) NOT NULL,
       				  `modelo` VARCHAR(50) NOT NULL,
       				  `pac` INT NOT NULL,
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

	public List<Motor> getMotor()
	{
		List<Motor> r = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM Carro";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next())
			{
				r.add(new Motor(rs.getInt(1),rs.getInt(2)));
			}
		} catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		return r;
	}
	private ResultSet makeQuery(String c, String filter, int id) throws SQLException
	{
		ResultSet r = null;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM "+ c + "  WHERE " + filter +" = " + id;
			r = stm.executeQuery(sql);
		}
		return r;
	}
	private Categoria getCategoria(int id) throws SQLException
	{
		Categoria r = null;
		String filter = "categoria";
		ResultSet rs1 = makeQuery("C1",filter,id);
		ResultSet rs2 = makeQuery("C2",filter,id);
		ResultSet rs3 = makeQuery("GT",filter,id);
		ResultSet rs4 = makeQuery("SC",filter,id);
		ResultSet rs5 = makeQuery("C1H",filter,id);
		ResultSet rs6 = makeQuery("C2H",filter,id);
		ResultSet rs7 = makeQuery("GTH",filter,id);
		if(rs1.next())
			r = new C1();
		else if(rs2.next())
			r = new C2();
		else if(rs3.next())
			r = new GT();
		else if(rs4.next())
			r = new SC();
		else if(rs5.next())
			r = new C1Hibrido(new MotorElétrico(getMotor(rs6.getInt(1))));
		else if(rs6.next())
			r = new C2Hibrido(new MotorElétrico(getMotor(rs6.getInt(1))));
		else if(rs7.next())
			r = new GTHibrido_(new MotorElétrico(getMotor(rs6.getInt(1))));
		return r;
	}

	private IConjuntoPneus getPneus(int id) throws SQLException
	{
		IConjuntoPneus r = null;
		String filter = "pneu";
		ResultSet rs1 = makeQuery("Macio",filter,id);
		ResultSet rs2 = makeQuery("Duro",filter,id);
		ResultSet rs3 = makeQuery("Chuva",filter,id);
		if(rs1.next())
			r = new Macio();
		else if(rs2.next())
			r = new Duro();
		else if(rs3.next())
			r = new Chuva();
		return r;
	}
	private ModoMotor getModo(int id) throws SQLException
	{
		ModoMotor r = null;
		String filter = "modo";
		ResultSet rs1 = makeQuery("Agressivo",filter,id);
		ResultSet rs2 = makeQuery("Conservador",filter,id);
		ResultSet rs3 = makeQuery("Normal",filter,id);
		if(rs1.next())
			r = new Agressivo();
		else if(rs2.next())
			r = new Conservador();
		else if(rs3.next())
			r = new Normal();
		return r;

	}
	private Motor getMotor(int id) throws SQLException
	{
		String filter = "id";
		ResultSet rs = makeQuery("Motor",filter,id);
		return new Motor(rs.getInt(1),rs.getInt(2),getModo(rs.getInt(3)));
	}
	@Override
	public Carro get(Object key) {
		String []str = Carro.getMarcaModelo((String) key);
		String marca = str[0];
		String modelo = str[1];
		Carro r = null;
		int cat = 0, pac =0, cil =0 , pneu = 0, eng = 0;
		boolean aux = false;
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM Carro WHERE marca = '" + marca + "' AND modelo = '" + modelo + "'";
			System.out.println(sql);
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next())
			{
				marca = rs.getString(1);
				modelo = rs.getString(2);
				cat = rs.getInt(3);
				pac = rs.getInt(4);
				cil = rs.getInt(5);
				pneu = rs.getInt(6);
				eng = rs.getInt(7);
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
				IConjuntoPneus pneus = getPneus(pneu);
				MotorCombustao motor = new MotorCombustao(eng,cil);
				r = new Carro(marca,modelo,pac,pneus,motor,this.getCategoria(cat));
			}catch (SQLException e) {
			// Erro a criar tabela...
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}
		}
		return r;
	}
	private int getLastId(Statement statement) throws SQLException
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
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO ModoMotor VALUES()";
			stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			id = this.getLastId(stm);
			if (modoMotor instanceof Agressivo)
				sql = "INSERT INTO Agressivo VALUES (" + id + ")";
			else if (modoMotor instanceof Conservador)
				sql = "INSERT INTO Conservador VALUES (" + id + ")";
			else
				sql = "INSERT INTO Normal VALUES (" + id + ")";
			stm.executeUpdate(sql);
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
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO Pneu VALUES()";
			stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			id = this.getLastId(stm);
			if (pneus instanceof Macio)
				sql = "INSERT INTO Macio VALUES (" + id + ")";
			else if (pneus instanceof Duro)
				sql = "INSERT INTO Duro VALUES (" + id + ")";
			else
				sql = "INSERT INTO Chuva VALUES (" + id + ")";
			stm.executeUpdate(sql);
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
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			int modo = this.insertModo(motor.get_modo());
			String sql = "INSERT INTO Motor (potencia, capacidadeCombustivel, modo) VALUES (" + motor.insertCommand() + ',' + modo + ")";
			stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			id = this.getLastId(stm);
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
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO Categoria VALUES()";
			stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			id = this.getLastId(stm);
			if (categoria instanceof C1)
			{
				sql = "INSERT INTO C1 VALUES (" + id + ")";
				stm.executeUpdate(sql);
				if (categoria instanceof C1Hibrido)
				{
					int car = this.insertMotor(((C1Hibrido) categoria).get_motorEletrico());
					sql = "INSERT INTO C1H VALUES (" + id + ','+ car + ")";
					stm.executeUpdate(sql);
				}
			}
			else if (categoria instanceof C2)
			{
				sql = "INSERT INTO C2 VALUES (" + id + ")";
				stm.executeUpdate(sql);
				if (categoria instanceof C2Hibrido)
				{
					int car = this.insertMotor(((C2Hibrido) categoria).get_motorEletrico());
					sql = "INSERT INTO C2H VALUES (" + id + ','+ car + ")";
					stm.executeUpdate(sql);
				}
			}
			else if (categoria instanceof GT)
			{
				sql = "INSERT INTO GT VALUES (" + id + ")";
				stm.executeUpdate(sql);
				if (categoria instanceof GTHibrido_)
				{
					int car = this.insertMotor(((GTHibrido_) categoria).get_motorEletrico());
					sql = "INSERT INTO GTH VALUES (" + id + ','+ car + ")";
					stm.executeUpdate(sql);
				}
			}
			else if (categoria instanceof SC)
			{
				sql = "INSERT INTO SC VALUES (" + id + ")";
				stm.executeUpdate(sql);
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
		try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
			 Statement stm = conn.createStatement())
		{
			int motor = this.insertMotor(carro.get_motor());
			int pneu = this.insertPneu(carro.get_unnamed_IConjuntoPneus_());
			int categoria = this.insertCategoria(carro.get_unnamed_Categoria_());
			String sql = "INSERT INTO Carro ()VALUES (" + carro.insertCommand() + "," + motor + "," + pneu + "," + categoria+ ")";
			stm.executeUpdate(sql);
			System.out.println("Carro: " + carro + " adicionado");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NullPointerException(e.getMessage());
		}

	}
	public void generateData()
	{
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
		List<IConjuntoPneus> pneus = new ArrayList<>();
		pneus.add(new Chuva());
		pneus.add(new Macio());
		pneus.add(new Duro());
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(new C1());
		categorias.add(new C2());
		categorias.add(new GT());
		categorias.add(new SC());
		categorias.add(new C1Hibrido((MotorElétrico) motores.get(4)));
		categorias.add(new C2Hibrido((MotorElétrico) motores.get(5)));
		categorias.add(new GTHibrido_((MotorElétrico) motores.get(4)));
		List<Carro> carros = new ArrayList<>();
		carros.add(new Carro("Porshe", "GT3RS",2,pneus.get(1),(MotorCombustao) motores.get(0),categorias.get(0)));
		carros.add(new Carro("Ferrari", "Enzo",2,pneus.get(0),(MotorCombustao) motores.get(1),categorias.get(1)));
		carros.add(new Carro("Audi", "R8",3,pneus.get(1),(MotorCombustao) motores.get(2),categorias.get(2)));
		carros.add(new Carro("Honda", "Type-R",4,pneus.get(2),(MotorCombustao) motores.get(3),categorias.get(3)));
		carros.add(new Carro("Toyota", "Supra",5,pneus.get(1),(MotorCombustao) motores.get(2),categorias.get(4)));
		carros.add(new Carro("Mercedes", "AMG",6,pneus.get(2),(MotorCombustao) motores.get(1),categorias.get(5)));
		carros.forEach(this::insertCarro);
	}

	@Override
	public Carro put(String key, Carro value) {
		return null;

	}

	@Override
	public Carro remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Carro> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<Carro> values() {
		return null;
	}

	@Override
	public Set<Entry<String, Carro>> entrySet() {
		return null;
	}
}