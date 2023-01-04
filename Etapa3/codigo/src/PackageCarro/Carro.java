package PackageCarro;

import java.util.Objects;

public class Carro implements ICarro {
	private String _marca;
	private String _modelo;
	private float _pac;
	public Pneu _unnamed_IConjuntoPneus_;
	private MotorCombustao _motor;
	public Categoria _unnamed_Categoria_;

	public Carro(String _marca, String _modelo, float _pac, Pneu _unnamed_IConjuntoPneus_, MotorCombustao _motor, Categoria _unnamed_Categoria_) {
		this._marca = _marca;
		this._modelo = _modelo;
		this._pac = _pac;
		this._unnamed_IConjuntoPneus_ = _unnamed_IConjuntoPneus_;
		this._motor = _motor;
		this._unnamed_Categoria_ = _unnamed_Categoria_;
	}

	public String generateKey()
	{
		return this._marca + "," + this._modelo;
	}
	public static String[] getMarcaModelo(String key)
	{
		return key.split(",");
	}

	@Override
	public Boolean validarRegistoCarro(Integer aCilintrada, Integer potencia, Float aPac) {
		return aCilintrada > 0 && potencia > 0 && this.validaAfinacao(aPac);
	}

	public void recalculaFiabilidade(String aDecisão, int aGdu)
	{
		this._unnamed_Categoria_.recalculaFiabilidade(aDecisão,aGdu);
	}

	@Override
	public Boolean validaAfinacao(float aPAC) {
		return aPAC > 0 && aPAC < 1;
	}

	public void modoMotorNomal(Normal normal) {
		this._motor.modoMotorNormal(normal);
	}

	public void calculaFiabilidade(int cilindrada) {
		this._unnamed_Categoria_.calculaFiabilidade(cilindrada);
	}

	public void capacidadeComb100() {
		this._motor.capacidadeComb100();
		if(this._unnamed_Categoria_ instanceof C2Hibrido)
		{
			((C2Hibrido) this._unnamed_Categoria_).capacidadeComb100();
		}
		else if(this._unnamed_Categoria_ instanceof C1Hibrido)
		{
			((C1Hibrido) this._unnamed_Categoria_).capacidadeComb100();
		}
		else if(this._unnamed_Categoria_ instanceof GTHibrido_)
		{
			((GTHibrido_) this._unnamed_Categoria_).capacidadeComb100();
		}
	}

	public void reduzCapacidadeCombustivel(String aDecisao)
	{
		this._motor.reduzCapacidadeCombustivel(aDecisao);
		if(this._unnamed_Categoria_ instanceof C2Hibrido)
		{
			((C2Hibrido) this._unnamed_Categoria_).reduzCapacidadeCombustivel(aDecisao);
		}
		else if(this._unnamed_Categoria_ instanceof C1Hibrido)
		{
			((C1Hibrido) this._unnamed_Categoria_).reduzCapacidadeCombustivel(aDecisao);
		}
		else if(this._unnamed_Categoria_ instanceof GTHibrido_)
		{
			((GTHibrido_) this._unnamed_Categoria_).reduzCapacidadeCombustivel(aDecisao);
		}
	}

	public void reduzCapacidadePneu(String aDecisao, int aGdu) {
		this._unnamed_IConjuntoPneus_.reduzCapacidadePneu(aDecisao,aGdu);
	}

	public Pneu get_unnamed_IConjuntoPneus_() {
		return _unnamed_IConjuntoPneus_;
	}

	public String insertCommand()
	{
		return '\'' + this._marca + '\'' + "," +'\'' + this._modelo +'\'' + "," + this._pac + "," + this._motor.get_cilindrada();
	}

	public MotorCombustao get_motor() {
		return _motor;
	}

	public int get_cilindrada() {
		return _motor.get_cilindrada();
	}

	public Categoria get_unnamed_Categoria_() {
		return _unnamed_Categoria_;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Carro carro = (Carro) o;
		return Objects.equals(_marca, carro._marca) && Objects.equals(_modelo, carro._modelo);
	}


	@Override
	public String toString() {
		return this._marca + " " + this._modelo + " " + this._unnamed_IConjuntoPneus_ + " " + this._motor;
	}

	public String get_marca() {
		return _marca;
	}

	public void set_marca(String _marca) {
		this._marca = _marca;
	}

	public String get_modelo() {
		return _modelo;
	}

	public void set_modelo(String _modelo) {
		this._modelo = _modelo;
	}

	public float get_pac() {
		return _pac;
	}

	public void set_pac(float _pac) {
		this._pac = _pac;
	}

	public void set_unnamed_IConjuntoPneus_(Pneu _unnamed_IConjuntoPneus_) {
		this._unnamed_IConjuntoPneus_ = _unnamed_IConjuntoPneus_;
	}

	public void set_motor(MotorCombustao _motor) {
		this._motor = _motor;
	}

	public void set_unnamed_Categoria_(Categoria _unnamed_Categoria_) {
		this._unnamed_Categoria_ = _unnamed_Categoria_;
	}
}