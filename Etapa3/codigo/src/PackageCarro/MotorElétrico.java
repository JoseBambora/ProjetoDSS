package PackageCarro;

public class MotorElétrico extends Motor {

	public MotorElétrico(int id,int _potencia, int _capacidadeCombustivel, ModoMotor _modo) {
		super(id,_potencia, _capacidadeCombustivel, _modo);
	}

	public MotorElétrico(int id,int _potencia, int _capacidadeCombustivel) {
		super(id,_potencia, _capacidadeCombustivel,null);
	}
	public MotorElétrico(Motor motor)
	{
		super(motor);
	}
}