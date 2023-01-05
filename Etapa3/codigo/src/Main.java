import PackageCampeonato.CampeonatoDAO;
import PackageCarro.Carro;
import PackageCarro.CarroDAO;
import PackageCircuito.CircuitoDAO;
import PackageIO.TextUI;
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
        CampeonatoDAO campeonatoDAO = CampeonatoDAO.getInstance();
        campeonatoDAO.generateData();
        CircuitoDAO circuitoDAO = CircuitoDAO.getInstace();
        circuitoDAO.generateDataCircuito();
        UtilizadoresDAO utilizadores = UtilizadoresDAO.getInstance();
        utilizadores.generateData();
        TextUI menu = new TextUI();
        menu.run();
    }
}
