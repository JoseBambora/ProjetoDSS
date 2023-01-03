package PackageCircuito;

public class Curva extends Caracteristica {
    public Curva(int gdu) {
        super(gdu);
    }

    @Override
    public String toString() {
        return "Curva " + this.get_gdu();
    }
}