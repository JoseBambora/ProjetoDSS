package PackageCarro;

public class GT extends Categoria
{
    public GT(int _fiabilidade)
    {
        super(_fiabilidade);
    }
    protected void agressivo(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.9f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-1.0f);
        else
            this.set_fiabilidade(fiabilidade-1.1f);
    }
    protected void normal(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.6f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.7f);
        else
            this.set_fiabilidade(fiabilidade-0.8f);
    }
    protected void conservador(int aGdu)
    {
        float fiabilidade = this.get_fiabilidade();
        if (aGdu == 0)
            this.set_fiabilidade(fiabilidade-0.3f);
        else if (aGdu == 1)
            this.set_fiabilidade(fiabilidade-0.4f);
        else
            this.set_fiabilidade(fiabilidade-0.5f);
    }

    @Override
    public void calculaFiabilidade(int aCilindrada)
    {
        float fiabilidade = 0f;
        this.set_fiabilidade(fiabilidade);
    }

    @Override
    public boolean validaCategoria(int cilindrada) {
        return cilindrada > 1999 && cilindrada < 4001;
    }

}