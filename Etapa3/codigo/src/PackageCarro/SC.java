package PackageCarro;

public class SC extends Categoria
{
    public SC(int _fiabilidade)
    {
        super(_fiabilidade);
    }

    @Override
    public int calculaFiabilidade(int aCilindrada) {
        return 0;
    }
}