import PackageCarro.Carro;
import PackageCarro.CarroDAO;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CarroDAO carroDAO = CarroDAO.getInstace();
        carroDAO.generateData();
        Set<String> key = carroDAO.keySet();
        Collection<Carro> carros = carroDAO.values();
        Set<Map.Entry<String,Carro>> entrySet = carroDAO.entrySet();
        System.out.println("Values: ");
        carros.forEach(System.out::println);
        System.out.println("\nEntrey Set");
        entrySet.forEach(c -> System.out.println(c.getValue()));
        System.out.println("\nKeySet: ");
        key.forEach(System.out::println);
    }
}