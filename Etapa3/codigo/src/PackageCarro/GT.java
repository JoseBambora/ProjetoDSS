package PackageCarro;

public class GT extends Categoria
{
    public GT(int _fiabilidade)
    {
        super(_fiabilidade);
    }

    @Override
    public int calculaFiabilidade(int aCilindrada) {
        return 0;

    }
}