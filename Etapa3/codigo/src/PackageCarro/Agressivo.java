package PackageCarro;

public class Agressivo extends ModoMotor {
    public Agressivo(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString() + " - Agressivo";
    }
}