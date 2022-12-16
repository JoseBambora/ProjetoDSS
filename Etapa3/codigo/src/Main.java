import PackageCarro.Carro;
import PackageCarro.CarroDAO;
import PackageUtilizador.Jogador;
import PackageUtilizador.Utilizador;
import PackageUtilizador.UtilizadoresDAO;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
         CarroDAO carroDAO = CarroDAO.getInstace();
         carroDAO.generateData();
        // Set<String> key = carroDAO.keySet();
        // Collection<Carro> carros = carroDAO.values();
        // Set<Map.Entry<String,Carro>> entrySet = carroDAO.entrySet();
        // System.out.println("Values: ");
        // carros.forEach(System.out::println);
        // System.out.println("\nEntrey Set");
        // entrySet.forEach(c -> System.out.println(c.getValue()));
        // System.out.println("\nKeySet: ");
        // key.forEach(System.out::println);
        //System.out.println(carroDAO.size());
        //System.out.println(carroDAO.containsKey("Porshe,GT3RS"));
        //System.out.println(carroDAO.containsKey("Porshe,GT2RS"));

        UtilizadoresDAO utilizadores = UtilizadoresDAO.getInstance();
        //System.out.println(utilizadores.get(1));
        utilizadores.generateData();
        //Set<String> key = utilizadores.keySet();
        //Collection<Utilizador> users = utilizadores.values();
        //Set<Map.Entry<String,Utilizador>> entrySet = utilizadores.entrySet();
        //System.out.println("Values: ");
        //users.forEach(System.out::println);
        //System.out.println("\nEntry Set");
        //entrySet.forEach(c -> System.out.println(c.getValue()));
        //System.out.println("\nKeySet: ");
        //.out.println(key);
        //System.out.println(utilizadores.size());
        //utilizadores.alteraPontuacao(1,10);
        //System.out.println(utilizadores.containsKey(1));
        //System.out.println(utilizadores.containsValue(new Jogador("Joao","underskill",0)));
    }
}