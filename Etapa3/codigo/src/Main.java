import PackageCarro.Carro;
import PackageCarro.CarroDAO;

public class Main {
    public static void main(String[] args) {
        CarroDAO carroDAO = CarroDAO.getInstace();
        //carroDAO.generateData();
        Carro carro1 = carroDAO.get("Porshe,GT3RS");
        Carro carro2 = carroDAO.get("Ferrari,Enzo");
        Carro carro3 = carroDAO.get("Audi,R8");
        Carro carro4 = carroDAO.get("Honda,Type-R");
        Carro carro5 = carroDAO.get("Toyota,Supra");
        Carro carro6 = carroDAO.get("Mercedes,AMG");
        System.out.println(carro1);
        System.out.println(carro2);
        System.out.println(carro3);
        System.out.println(carro4);
        System.out.println(carro5);
        System.out.println(carro6);
    }
}