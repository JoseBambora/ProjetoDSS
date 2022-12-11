package PackageCarro;

public class C2Hibrido extends C2 {
	private MotorElétrico _motorEletrico;
	public C2Hibrido(MotorElétrico _motorEletrico)
	{
		super();
		this._motorEletrico = _motorEletrico;
	}
	public MotorElétrico get_motorEletrico()
	{
		return this._motorEletrico;
	}
}