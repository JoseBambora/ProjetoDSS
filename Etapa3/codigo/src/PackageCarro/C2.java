package PackageCarro;

public class C2 extends Categoria
{
    public C2(int _fiabilidade)
    {
        super(_fiabilidade);
    }

    protected void agressivo(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.7f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.8f);
        else
            this.set_fiabilidade(fiabilidade-0.9f);
    }
    protected void normal(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.4f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.5f);
        else
            this.set_fiabilidade(fiabilidade-0.6f);
    }
    protected void conservador(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.1f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.2f);
        else
            this.set_fiabilidade(fiabilidade-0.3f);
    }
    @Override
    public void calculaFiabilidade(int aCilindrada)
    {
        float fiabilidade = 0f;
        this.set_fiabilidade(fiabilidade);
    }

    @Override
    public boolean validaCategoria(int cilindrada) {
        return cilindrada > 2999 && cilindrada < 5001;
    }
}