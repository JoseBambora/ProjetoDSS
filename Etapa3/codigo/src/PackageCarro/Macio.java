package PackageCarro;

public class Macio extends Pneu {

    public Macio(int id, int capacidade) {
        super(id, capacidade);
    }

    @Override
    public String toString() {
        return "Pneu Macio " + super.toString();
    }
}