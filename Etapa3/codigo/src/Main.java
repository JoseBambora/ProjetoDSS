import PackageCampeonato.CampeonatoDAO;
import PackageCarro.Carro;
import PackageCarro.CarroDAO;
import PackageCircuito.CircuitoDAO;
import PackagePiloto.Piloto;
import PackagePiloto.PilotoDAO;
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
        PilotoDAO pilotoDAO = PilotoDAO.getInstace();
        pilotoDAO.generateDataPiloto();
        CircuitoDAO circuitoDAO = CircuitoDAO.getInstace();
        circuitoDAO.generateDataCircuito();
        UtilizadoresDAO utilizadores = UtilizadoresDAO.getInstance();
        utilizadores.generateData();
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

         //Testado
        //System.out.println(utilizadores.get("Maria")); //Testado
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
        CampeonatoDAO campeonatoDAO = CampeonatoDAO.getInstance();
        //System.out.println(pilotoDAO.size());
        //System.out.println(pilotoDAO.values());
        //System.out.println(pilotoDAO.get("Alexander Hamilton"));
        //System.out.println(pilotoDAO.get("Gary Leclerc"));
        //System.out.println(circuitoDAO.values());
        //System.out.println(circuitoDAO.get("Lisboa"));
        //System.out.println(circuitoDAO.size());

    }
}