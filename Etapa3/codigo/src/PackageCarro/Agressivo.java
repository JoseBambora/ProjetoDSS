package PackageCarro;

public class Agressivo extends ModoMotor {
    public Agressivo(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Modo Agressivo " + super.toString();
    }
}