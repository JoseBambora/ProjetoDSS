package PackageCarro;

public class C1 extends Categoria {
	public C1(int _fiabilidade)
	{
		super(_fiabilidade);
	}

	@Override
	public int calculaFiabilidade(int aCilindrada) {
		return 0;
	}
}