package PackageCarro;

public class Duro extends Pneu{

    public Duro(int id, int capacidade) {
        super(id, capacidade);
    }

    @Override
    public String toString() {
        return "Pneu Duro " + super.toString();
    }
}