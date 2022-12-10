package PackageCarro;

public class Carro implements ICarro {
	private String _marca;
	private String _modelo;
	private int _pac;
	private String _id;
	public IConjuntoPneus _unnamed_IConjuntoPneus_;
	private MotorCombustao _motor;
	public Categoria _unnamed_Categoria_;

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
}