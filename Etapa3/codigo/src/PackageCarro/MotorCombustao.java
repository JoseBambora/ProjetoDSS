package PackageCarro;

public class MotorCombustao extends Motor {
	private int _cilindrada;
	public Carro _unnamed_Carro_;

	public MotorCombustao(int _potencia, int _capacidadeCombustivel, ModoMotor _modo,int _cilindrada)
	{
		super(_potencia,_capacidadeCombustivel,_modo);
		this._cilindrada = _cilindrada;
	}
	public MotorCombustao(Motor motor ,int _cilindrada)
	{
		super(motor);
		this._cilindrada = _cilindrada;
	}

	public MotorCombustao(int _potencia, int _capacidadeCombustivel) {
		super(_potencia, _capacidadeCombustivel);
	}

	public MotorCombustao(int _potencia, int _capacidadeCombustivel, ModoMotor _modo) {
		super(_potencia, _capacidadeCombustivel, _modo);
	}

	public int get_cilindrada() {
		return _cilindrada;
	}
}