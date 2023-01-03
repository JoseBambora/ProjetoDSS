package PackageCarro;

public class Chuva extends Pneu{
    public Chuva(int id, int capacidade) {
        super(id, capacidade);
    }

    @Override
    public String toString() {
        return "Pneu Chuva ";
    }
}