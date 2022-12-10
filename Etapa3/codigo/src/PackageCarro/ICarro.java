package PackageCarro;

public interface ICarro {

	public Boolean validarRegistoCarro(String aMarca, String aModelo, Categoria aCategoria, Integer aCilintrada, Integer aPotenciac, Integer aPotencia, Float aPac);

	public Integer calculaFiabilidadeCarro();

	public void recalculaFiabilidade(Integer aDecisão, int aGdu);

	public Boolean validaAfinacao(float aPAC, ModoMotor aModo, String aPneus);

	public void modoMotorNomal();

	public void calculaFiabilidade();

	public void capacidadeComb100();

	public void reduzCapacidadeCombustivel(String aDecisao);

	public void reduzCapacidadePneu(String aDecisao, int aGdu);
}