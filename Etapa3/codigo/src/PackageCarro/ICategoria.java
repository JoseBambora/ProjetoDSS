package PackageCarro;

public interface ICategoria {

	public void calculaFiabilidade(int aCilindrada);

	public void validaCat(Integer aCilindrada, Integer aPotenciac, Integer aPotenciae);

	public void recalculaFiabilidade(Integer aDecisão, int aGdu);
}