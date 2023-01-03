package PackageCarro;

public class Motor implements IMotor {
	private int id;
	private int _potencia;
	private int _capacidadeCombustivel;
	private ModoMotor _modo;

	public Motor(int id, int _potencia, int _capacidadeCombustivel, ModoMotor _modo)
	{
		this.id = id;
		this._potencia = _potencia;
		this._capacidadeCombustivel = _capacidadeCombustivel;
		this._modo = _modo;
	}

	public Motor(Motor motor)
	{
		this.id = motor.id;
		this._potencia = motor._potencia;
		this._capacidadeCombustivel = motor._capacidadeCombustivel;
		this._modo = motor._modo;
	}

	public int getId() {
		return id;
	}

	public ModoMotor get_modo()
	{
		return this._modo;
	}
	public String insertCommand()
	{
		return "" + this._potencia + ',' + this._capacidadeCombustivel;
	}

	public void modoMotorNormal(Normal modo) {
		this._modo = modo;
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

	public int get_potencia() {
		return _potencia;
	}

	public int get_capacidadeCombustivel() {
		return _capacidadeCombustivel;
	}

	@Override
	public String toString() {
		return "Motor " + this._potencia + " " + this._modo;
	}
}