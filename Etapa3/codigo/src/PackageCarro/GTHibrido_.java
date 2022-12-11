package PackageCarro;

public class GTHibrido_ extends GT {
	private MotorElétrico _motorEletrico;
	public GTHibrido_(MotorElétrico _motorEletrico)
	{
		super();
		this._motorEletrico = _motorEletrico;
	}

	public MotorElétrico get_motorEletrico()
	{
		return this._motorEletrico;
	}
}