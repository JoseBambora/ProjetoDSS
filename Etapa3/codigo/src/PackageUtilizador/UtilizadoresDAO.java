package PackageUtilizador;

import DAOCONFIG.*;
import java.sql.*;
import java.util.*;

public class UtilizadoresDAO implements Map<String, Utilizador> {

    private static UtilizadoresDAO instance = null;

    private UtilizadoresDAO()
    {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Utilizador`("+
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "username VARCHAR(75)," +
                    "password VARCHAR(75)," +
                    "PRIMARY KEY (`id`))";
            stm.executeUpdate(sql);
            sql = """
                  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Admin`(
                     `idUser` INT NOT NULL,
                      FOREIGN KEY (`idUser`)
                      REFERENCES `simuladorDSS`.`Utilizador` (`id`),
                      PRIMARY KEY (`idUser`)
                     )
                  """;
            stm.executeUpdate(sql);
            sql = """
                  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Jogador`(
                    `idUser` INT NOT NULL,
                    `pontuacao` INT,
                        FOREIGN KEY (`idUser`)
                        REFERENCES `simuladorDSS`.`Utilizador` (`id`),
                        PRIMARY KEY (`idUser`)
                  )
                  """;
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadoresDAO getInstance(){
        if(instance == null)
            instance = new UtilizadoresDAO();
        return instance;
    }

    @Override
    public int size() {
        int res = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement())
        {
            String sql = "SELECT COUNT(*) FROM Utilizador";
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
            String sql = "SELECT COUNT(*) FROM Utilizador WHERE id = ?";
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
        Utilizador user = (Utilizador) value;
        boolean res = false;
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);)
        {
            String sql = "SELECT COUNT(*) FROM Utilizador WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,(user.get_username()));
            ps.setString(2,(user.get_password()));
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

    private int getLastId(PreparedStatement statement) throws SQLException
    {
        int id = 0;
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    //TESTADO
    @Override
    public Utilizador get(Object key) {
        String username = "";
        String password = "";
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
            String sql = "SELECT * FROM Utilizador WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int id = (Integer) key;
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                username = rs.getString("username");
                password = rs.getString("password");
            }
            sql = "SELECT * FROM Admin WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Admin(username,password);
            }
            sql = "SELECT * FROM Jogador WHERE idUser = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                int pontuacao = rs.getInt("pontuacao");
                return new Jogador(username,password,pontuacao);
            }
            return new Utilizador(username,password);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public void insertUser(Utilizador value){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
            String username = value.get_username();
            String password = value.get_password();
            String sql = "INSERT INTO Utilizador (username,password) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
            int id = this.getLastId(ps);
            if(value instanceof Jogador){
                int pontuacao = ((Jogador) value).get_pontuacaoTotal();
                sql = "INSERT INTO Jogador VALUES ("+id+",?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,pontuacao);
                ps.executeUpdate();
            }
            if(value instanceof Admin){
                sql = "INSERT INTO Admin VALUES ("+id+")";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Utilizador put(String key, Utilizador value) {
        this.insertUser(value);
        return null;
    }

    @Override
    public Utilizador remove(Object key) {return null;}


    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        Set<?extends String> keys = m.keySet();
        for(String key: keys){
            Utilizador user = m.get(key);
            this.put(key,user);
        }
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
            String sql = "DELETE * FROM Utilizador";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            sql = "DELETE * FROM Jogador";
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            sql = "DELETE * FROM Admin";
            ps = conn.prepareStatement(sql);
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
            String sql = "SELECT * FROM Utilizador";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                result.add(String.valueOf(id));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return result;
    }

    //TESTADO
    @Override
    public Collection<Utilizador> values() {
        Collection<Utilizador> users = new ArrayList<>();
        Set<String> keyset = this.keySet();
        for (String key : keyset)
        {
            Utilizador user = this.get(Integer.valueOf(key));
            users.add(user);
        }
        return users;
    }

    //TESTADO
    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        Set<String> keyset = this.keySet(); //[1,2,3]
        Set<Entry<String,Utilizador>> result = new HashSet<>();
        for(String key : keyset){
            Utilizador user = this.get(Integer.valueOf(key));
            result.add(Map.entry(key,user));
        }
        return result;
    }

    public void alteraPontuacao(int idJogador, int incremento){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
            Utilizador aux = this.get(idJogador);
            String sql= "UPDATE Jogador SET pontuacao = ? WHERE idUser = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1,aux.get_pontuacaoTotal()+incremento);
            ps.setInt(2,idJogador);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }



    public void generateData()
    {
        if(this.isEmpty())
        {
            System.out.println("A gerar dados para os users");
            List<Utilizador> users = new ArrayList<>();
            users.add(new Jogador("Joao","underskill",0));
            users.add(new Utilizador("Maria","Albertina"));
            users.add(new Admin("Deus","TodoPoderoso"));
            users.forEach(this::insertUser);
        }
    }
}
