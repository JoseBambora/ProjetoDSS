import PackageCarro.Carro;
import PackageCarro.CarroDAO;

public class Main {
    public static void main(String[] args) {
        CarroDAO carroDAO = CarroDAO.getInstace();
        carroDAO.generateData();
        //System.out.println(carro);
    }
}