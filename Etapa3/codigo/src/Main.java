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
         //CarroDAO carroDAO = CarroDAO.getInstace();
         //carroDAO.generateData();
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
        //System.out.println(utilizadores.get("Maria")); //Testado
        //utilizadores.generateData(); //Testado
        // Set<String> key = utilizadores.keySet(); //Testado
        //Collection<Utilizador> users = utilizadores.values(); //Testado
        //Set<Map.Entry<String,Utilizador>> entrySet = utilizadores.entrySet(); //Testado
        //System.out.println(users);
        //System.out.println(entrySet);
        //System.out.println(key);
        //System.out.println(utilizadores.size()); //Testado
        //utilizadores.addPontuacao("Joao",10); //Testado
        //System.out.println(utilizadores.containsKey("Maria")); //Testado
        //System.out.println(utilizadores.containsValue(new Utilizador("Maria","Albertina")));//Testado
    }
}