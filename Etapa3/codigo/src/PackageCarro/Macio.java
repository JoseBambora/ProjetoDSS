package PackageCarro;

public class Macio extends Pneu {

    public Macio(int id, int capacidade) {
        super(id, capacidade);
    }

    @Override
    public String toString() {
        return this.getId()+" - Pneus Macios com capacidade = " + this.getCapacidade();
    }
}