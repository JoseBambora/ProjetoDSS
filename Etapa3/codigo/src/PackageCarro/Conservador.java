package PackageCarro;

public class Conservador extends ModoMotor {
    public Conservador(int id) {
        super(id);
    }

    @Override
    public String toString() {
       return super.toString() +" - Conservador";
    }
}