package PackageCarro;

public abstract class Categoria implements ICategoria {
	private float _fiabilidade;

	public Categoria(float _fiabilidade)
	{
		this._fiabilidade = _fiabilidade;
	}

	public String getValues()
	{
		return Float.toString(this._fiabilidade);
	}

	public void recalculaFiabilidade(Integer aDecisão, int aGdu) {
		throw new UnsupportedOperationException();
	}

	public float get_fiabilidade() {
		return _fiabilidade;
	}

	protected void set_fiabilidade(float _fiabilidade) {
		this._fiabilidade = _fiabilidade;
	}
	protected abstract void agressivo(int aGdu);
	protected abstract void normal(int aGdu);
	protected abstract void conservador(int aGdu);
	@Override
	public void recalculaFiabilidade(String aDecisão, int aGdu)
	{
		switch (aDecisão)
		{
			case "Agressivo" -> this.agressivo(aGdu);
			case "Normal" -> this.normal(aGdu);
			case "Conservador" -> this.conservador(aGdu);
		};
	}
}