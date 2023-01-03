package PackageCarro;

public class Normal extends ModoMotor {
    public Normal(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Modo Normal " + super.toString();
    }
}