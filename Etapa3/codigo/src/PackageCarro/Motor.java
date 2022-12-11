package PackageCarro;

public class Motor implements IMotor {
	private int _potencia;
	private int _capacidadeCombustivel;
	private ModoMotor _modo;

	public Motor(int _potencia, int _capacidadeCombustivel, ModoMotor _modo)
	{
		this._potencia = _potencia;
		this._capacidadeCombustivel = _capacidadeCombustivel;
		this._modo = _modo;
	}

	public Motor(Motor motor)
	{
		this._potencia = motor._potencia;
		this._capacidadeCombustivel = motor._capacidadeCombustivel;
		this._modo = motor._modo;
	}

	public Motor(int _potencia, int _capacidadeCombustivel)
	{
		this._potencia = _potencia;
		this._capacidadeCombustivel = _capacidadeCombustivel;
		this._modo = new ModoMotor();
	}
	public ModoMotor get_modo()
	{
		return this._modo;
	}
	public String insertCommand()
	{
		return "" + this._potencia + ',' + this._capacidadeCombustivel;
	}

	public void modoMotorNormal() {
		throw new UnsupportedOperationException();
	}

	public void capacidadeComb100() {
		throw new UnsupportedOperationException();
	}
}