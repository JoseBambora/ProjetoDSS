package PackageCarro;

public class C2 extends Categoria
{
    public C2(int _fiabilidade)
    {
        super(_fiabilidade);
    }

    @Override
    public int calculaFiabilidade(int aCilindrada) {
        return 0;
    }
}