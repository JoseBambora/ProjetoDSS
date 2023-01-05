package PackageCarro;

public class Agressivo extends ModoMotor {
    public Agressivo(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Motor "+this.getId()+" em modo Agressivo: "+ super.toString();
    }
}