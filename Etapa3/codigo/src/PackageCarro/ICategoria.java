package PackageCarro;

public interface ICategoria {

	public int calculaFiabilidade(int aCilindrada);

	public void recalculaFiabilidade(String aDecisão, int aGdu);
}