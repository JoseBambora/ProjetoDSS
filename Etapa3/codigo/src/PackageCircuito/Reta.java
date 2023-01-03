package PackageCircuito;

public class Reta extends Caracteristica {
    public Reta(int gdu) {
        super(gdu);
    }

    @Override
    public String toString() {
        return "Reta " + this.get_gdu();
    }
}