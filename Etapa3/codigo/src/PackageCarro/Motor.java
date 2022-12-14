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
		this._modo = new Normal();
	}

	public void capacidadeComb100() {
		this._capacidadeCombustivel=100;
	}
	public void reduzCapacidadeCombustivel(String aDecisao)
	{
		switch (aDecisao)
		{
			case "Agressivo" -> this._capacidadeCombustivel -= 3;
			case "Normal" -> this._capacidadeCombustivel-=2;
			case "Conservador" -> this._capacidadeCombustivel-=1;
		}
	}
}