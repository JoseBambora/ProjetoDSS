package PackageCarro;

public class MotorElétrico extends Motor {

	public MotorElétrico(int _potencia, int _capacidadeCombustivel, ModoMotor _modo) {
		super(_potencia, _capacidadeCombustivel, _modo);
	}

	public MotorElétrico(int _potencia, int _capacidadeCombustivel) {
		super(_potencia, _capacidadeCombustivel);
	}
	public MotorElétrico(Motor motor)
	{
		super(motor);
	}
}