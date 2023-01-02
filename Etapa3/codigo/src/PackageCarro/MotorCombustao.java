package PackageCarro;

public class MotorCombustao extends Motor {
	private int _cilindrada;
	public Carro _unnamed_Carro_;

	public MotorCombustao(int id,int _potencia, int _capacidadeCombustivel, ModoMotor _modo,int _cilindrada)
	{
		super(id,_potencia,_capacidadeCombustivel,_modo);
		this._cilindrada = _cilindrada;
	}
	public MotorCombustao(Motor motor ,int _cilindrada)
	{
		super(motor);
		this._cilindrada = _cilindrada;
	}

	public MotorCombustao(int id,int _potencia, int _capacidadeCombustivel) {
		super(id,_potencia, _capacidadeCombustivel,null);
	}

	public int get_cilindrada() {
		return _cilindrada;
	}
}