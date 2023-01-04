package PackageCarro;

public interface ICarro {

	public Boolean validarRegistoCarro(Integer aCilintrada, Integer potencia, Float aPac);

	public void recalculaFiabilidade(String aDecisão, int aGdu);

	public Boolean validaAfinacao(float aPAC);

	public void modoMotorNomal(Normal normal);

	public void calculaFiabilidade(int cilindrada);

	public void capacidadeComb100();

	public void reduzCapacidadeCombustivel(String aDecisao);

	public void reduzCapacidadePneu(String aDecisao, int aGdu);
}