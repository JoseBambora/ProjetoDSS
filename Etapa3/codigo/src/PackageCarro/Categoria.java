package PackageCarro;

public class Categoria implements ICategoria {
	private int _fiabilidade;

	public Categoria(int _fiabilidade)
	{
		this._fiabilidade = _fiabilidade;
	}

	public void calculaFiabilidade(int aCilindrada) {
		throw new UnsupportedOperationException();
	}

	public void validaCat(Integer aCilindrada, Integer aPotenciac, Integer aPotenciae) {
		throw new UnsupportedOperationException();
	}

	public String getValues()
	{
		return Integer.toString(this._fiabilidade);
	}

	public void recalculaFiabilidade(Integer aDecisão, int aGdu) {
		throw new UnsupportedOperationException();
	}
}