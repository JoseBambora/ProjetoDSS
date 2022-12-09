public interface ICaracteristica {

	public Boolean validaGDU(Integer aGdu);
}public interface ICarro {

	public Boolean__ validarRegistoCarro(String aMarca, String aModelo, Categoria aCategoria, Integer aCilintrada, Integer aPotenciac, Integer aPotencia, Float aPac);

	public Integer calculaFiabilidadeCarro();

	public void recalculaFiabilidade(Integer aDecisão, int aGdu);

	public Boolean validaAfinacao(float aPAC, Modo aModo, String aPneus);

	public void modoMotorNomal();

	public void calculaFiabilidade();

	public void capacidadeComb100();

	public void reduzCapacidadeCombustivel(String aDecisao);

	public void reduzCapacidadePneu(String aDecisao, int aGdu);
}