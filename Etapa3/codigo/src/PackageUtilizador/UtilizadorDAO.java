package PackageUtilizador;

import DAOCONFIG.*;
import PackageCarro.Carro;
import PackageUtilizador.Utilizador;
import jdk.jshell.execution.Util;
import javax.swing.plaf.nimbus.State;
import java.security.Key;
import java.sql.*;
import java.util.*;

public class UtilizadorDAO implements Map<String, Utilizador> {

    private static UtilizadorDAO instance = null;

    private UtilizadorDAO()
    {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL,DAOconfig.USERNAME,DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Utilizador`("+
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "username VARCHAR(75)," +
                    "palavra_passe VARCHAR(75)," +
                    "PRIMARY KEY (`id`))";
            stm.executeUpdate(sql);
            sql = """
                  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Admin`(
                     `idUser` INT NOT NULL AUTO_INCREMENT,
                      FOREIGN KEY (`idUser`)
                      REFERENCES `simuladorDSS`.`Utilizador` (`id`)
                      PRIMARY KEY (`idUser`)
                     )
                  """;
            stm.executeUpdate(sql);
            sql = """
                  CREATE TABLE IF NOT EXISTS `simuladorDSS`.`Jogador`(
                    `idUser` INT NOT NULL AUTO_INCREMENT,
                    `pontuacao` INT,
                        FOREIGN KEY (`idUser`)
                        REFERENCES `simuladorDSS`.`Utilizador` (`id`)
                        PRIMARY KEY (`idUser`)
                  )
                  """;
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static UtilizadorDAO getInstance(){
        if(instance == null)
            instance = new UtilizadorDAO();
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
            String sql = "SELECT COUNT (*) FROM Utilizador WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,(Integer)key);
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
            String sql = "SELECT COUNT (*) FROM Utilizador WHERE username = ? AND password = ?";
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


    public Collection<Utilizador> getUtilizadores(){
        Collection <Utilizador> r = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
            Statement stm = conn.createStatement()){
            String sql = "SELECT * FROM Utilizador";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next())
            {
                r.add(new Utilizador(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public Utilizador get(Object key) {
        String username = "";
        String password = "";
        Utilizador user = new Utilizador();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);) {
            String sql = "SELECT * FROM Utilizador WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,(int) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                username = rs.getString("username");
                password = rs.getString("password");
                user.set_username(username);
                user.set_password(password);
            }
            sql = "SELECT * FROM Admin WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(int) key);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Admin(user.get_username(),user.get_password());
            }
            sql = "SELECT * FROM Jogador WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,(int) key);
            rs = ps.executeQuery();
            if(rs.next()){
                int pontuacao = rs.getInt("pontuacao");
                return new Jogador(user.get_username(),user.get_password(),pontuacao);
            }

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return null;
    }

    public void insertUser(Utilizador value){
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);){
            String username = value.get_username();
            String password = value.get_password();
            int id = 0;
            String sql = "INSERT INTO Utilizador VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,username);
            ps.setString(3,password);
            if(value instanceof Jogador){
                int pontuacao = ((Jogador) value).get_pontuacaoTotal();
                sql = "INSERT INTO Jogador VALUES (?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.setInt(2,pontuacao);
            }
            if(value instanceof Admin){
                sql = "INSERT INTO Admin VALUES (?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }


    //SELECT FROM Utilizador where key == ao id
    @Override
    public Utilizador put(String key, Utilizador value) {
        this.insertUser(value);
        return null;
    }

    @Override
    public Utilizador remove(Object key) {return null;}
    //DELETE FROM table_name WHERE condition;


    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> m) {
        Set<?extends String> keys = m.keySet();
        for(String key: keys){
            Utilizador user = m.get(key);
            this.put(key,user);
        }
    }

    //ciclo para cada elemento do map fazer insert na base de dados

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

    //Fazer set com as PKs da tabela
    @Override
    public Collection<Utilizador> values() {
        Collection<Utilizador> users = new ArrayList<>();
        Set<String> keyset = this.keySet();
        for (String key : keyset)
        {
            Utilizador user = this.get(key);
            users.add(user);
        }
        return users;
    }

    //ir buscar todos os users
    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        Set<String> keyset = this.keySet();
        Set<Entry<String,Utilizador>> result = new HashSet<>();
        for(String key : keyset){
           Utilizador user = this.get(key);
           result.add(Map.entry(key,user));
        }
        return result;
    }
}
