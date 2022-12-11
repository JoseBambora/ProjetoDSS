package PackageCarro;

import javax.swing.plaf.PanelUI;

public class Carro implements ICarro {
	private String _marca;
	private String _modelo;
	private int _pac;
	public IConjuntoPneus _unnamed_IConjuntoPneus_;
	private MotorCombustao _motor;
	public Categoria _unnamed_Categoria_;

	public Carro(String _marca, String _modelo, int _pac, IConjuntoPneus _unnamed_IConjuntoPneus_, MotorCombustao _motor, Categoria _unnamed_Categoria_) {
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
	public Boolean validarRegistoCarro(String aMarca, String aModelo, Categoria aCategoria, Integer aCilintrada, Integer aPotenciac, Integer aPotencia, Float aPac) {
		return null;
	}

	public Integer calculaFiabilidadeCarro() {
		throw new UnsupportedOperationException();
	}

	public void recalculaFiabilidade(Integer aDecisão, int aGdu) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean validaAfinacao(float aPAC, ModoMotor aModo, String aPneus) {
		return null;
	}

	public void modoMotorNomal() {
		throw new UnsupportedOperationException();
	}

	public void calculaFiabilidade() {
		throw new UnsupportedOperationException();
	}

	public void capacidadeComb100() {
		throw new UnsupportedOperationException();
	}

	public void reduzCapacidadeCombustivel(String aDecisao) {
		throw new UnsupportedOperationException();
	}

	public void reduzCapacidadePneu(String aDecisao, int aGdu) {
		throw new UnsupportedOperationException();
	}

	public IConjuntoPneus get_unnamed_IConjuntoPneus_() {
		return _unnamed_IConjuntoPneus_;
	}

	public String insertCommand()
	{
		return '\'' + this._marca + '\'' + "," +'\'' + this._modelo +'\'' + "," + this._pac + "," + this._motor.get_cilindrada();
	}

	public MotorCombustao get_motor() {
		return _motor;
	}

	public Categoria get_unnamed_Categoria_() {
		return _unnamed_Categoria_;
	}

	@Override
	public String toString() {
		return this._marca + " " + this._modelo;
	}
}