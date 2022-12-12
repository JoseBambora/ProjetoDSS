package PackageCarro;

public class GTHibrido_ extends GT {
	private MotorElétrico _motorEletrico;
	public GTHibrido_(int _fiabilidade,MotorElétrico _motorEletrico)
	{
		super(_fiabilidade);
		this._motorEletrico = _motorEletrico;
	}

	public MotorElétrico get_motorEletrico()
	{
		return this._motorEletrico;
	}
}