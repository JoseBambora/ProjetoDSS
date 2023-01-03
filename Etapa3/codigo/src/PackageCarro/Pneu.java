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
        return this.id + " " + this.capacidade;
    }

    private void agressivo(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 3;
        else if (aGdu == 1)
            capacidade -= 4;
        else
            capacidade -= 5;
    }
    private void normal(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 2;
        else if (aGdu == 1)
            capacidade -= 3;
        else
            capacidade -= 4;
    }
    private void conservador(int aGdu)
    {
        if (aGdu == 0)
            capacidade -= 1;
        else if (aGdu == 1)
            capacidade -= 2;
        else
            capacidade -= 3;
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
