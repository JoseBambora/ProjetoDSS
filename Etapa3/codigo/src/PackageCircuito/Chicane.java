package PackageCircuito;

public class Chicane extends Caracteristica {
    public Chicane(int gdu) {
        super(gdu);
    }

    @Override
    public String toString() {
        return "Chicane " + this.get_gdu();
    }
}