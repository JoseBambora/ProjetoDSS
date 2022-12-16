package PackageCarro;

public class SC extends Categoria
{
    public SC(int _fiabilidade)
    {
        super(_fiabilidade);
    }
    protected void agressivo(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-1.1f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-1.2f);
        else
            this.set_fiabilidade(fiabilidade-1.3f);
    }
    protected void normal(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.8f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.9f);
        else
            this.set_fiabilidade(fiabilidade-1f);
    }
    protected void conservador(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.5f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.6f);
        else
            this.set_fiabilidade(fiabilidade-0.7f);
    }
    @Override
    public void calculaFiabilidade(int aCilindrada)
    {
        float fiabilidade = 0f;
        this.set_fiabilidade(fiabilidade);
    }

    @Override
    public boolean validaCategoria(int cilindrada) {
        return cilindrada == 2500;
    }
}