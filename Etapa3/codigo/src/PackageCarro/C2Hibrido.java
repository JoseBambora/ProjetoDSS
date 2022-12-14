package PackageCarro;

public class C2Hibrido extends C2 {
	private MotorElétrico _motorEletrico;
	public C2Hibrido(int _fiabilidade,MotorElétrico _motorEletrico)
	{
		super(_fiabilidade);
		this._motorEletrico = _motorEletrico;
	}
	public MotorElétrico get_motorEletrico()
	{
		return this._motorEletrico;
	}
	public void reduzCapacidadeCombustivel(String aDecisao)
	{
		this._motorEletrico.reduzCapacidadeCombustivel(aDecisao);
	}
	public void capacidadeComb100()
	{
		this._motorEletrico.capacidadeComb100();
	}

}