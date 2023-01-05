package PackageCarro;

public abstract class Pneu implements IConjuntoPneus
{
    private int id;
    private int capacidade;
    public Pneu(int id, int capacidade)
    {
        this.id = id;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    @Override
    public String toString() {
        return "Id -> "+ this.id + " Capacidade-> " + this.capacidade;
    }

    private void agressivo(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 0.3f;
        else if (aGdu == 1)
            capacidade -= 0.4f;
        else
            capacidade -= 0.5f;
    }
    private void normal(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 0.2f;
        else if (aGdu == 1)
            capacidade -= 0.3f;
        else
            capacidade -= 0.4f;
    }
    private void conservador(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 0.1f;
        else if (aGdu == 1)
            capacidade -= 0.2f;
        else
            capacidade -= 0.3f;
    }
    @Override
    public void reduzCapacidadePneu(String aDecisao, int aGdu) {
        switch (aDecisao)
        {
            case "Agressivo" -> this.agressivo(aGdu);
            case "Normal" -> this.normal(aGdu);
            case "Conservador" -> this.conservador(aGdu);
        };
    }
}
