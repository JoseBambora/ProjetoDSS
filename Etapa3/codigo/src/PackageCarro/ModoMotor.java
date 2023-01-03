package PackageCarro;

public class ModoMotor
{
    private int id;
    public ModoMotor(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}