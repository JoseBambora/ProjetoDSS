package PackageCarro;

public class Conservador extends ModoMotor {
    public Conservador(int id) {
        super(id);
    }

    @Override
    public String toString() {
       return "Motor "+this.getId()+" em modo Conservador: "+ super.toString();
    }
}