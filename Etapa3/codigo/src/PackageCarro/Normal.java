package PackageCarro;

public class Normal extends ModoMotor {
    public Normal(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Motor "+this.getId()+" em modo Normal: "+ super.toString();
    }
}