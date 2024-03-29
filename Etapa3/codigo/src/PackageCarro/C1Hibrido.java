package PackageCarro;

public class C1Hibrido extends C1 {
	private MotorElétrico _motorEletrico;

	public C1Hibrido(int fiabilidade, MotorElétrico _motorEletrico) {
		super(fiabilidade);
		this._motorEletrico = _motorEletrico;
	}

	public MotorElétrico get_motorEletrico() {
		return this._motorEletrico;
	}

	public void reduzCapacidadeCombustivel(String aDecisao) {
		this._motorEletrico.reduzCapacidadeCombustivel(aDecisao);
	}

	public void capacidadeComb100() {
		this._motorEletrico.capacidadeComb100();
	}

}