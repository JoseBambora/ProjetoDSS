package PackageCarro;

public abstract class Categoria implements ICategoria {
	private int _fiabilidade;

	public Categoria(int _fiabilidade)
	{
		this._fiabilidade = _fiabilidade;
	}

	public abstract int calculaFiabilidade(int aCilindrada);

	public String getValues()
	{
		return Integer.toString(this._fiabilidade);
	}

	public void recalculaFiabilidade(Integer aDecisão, int aGdu) {
		throw new UnsupportedOperationException();
	}

	public int get_fiabilidade() {
		return _fiabilidade;
	}
}