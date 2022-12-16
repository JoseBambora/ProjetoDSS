package PackageCarro;

public interface ICategoria {

	public void calculaFiabilidade(int aCilindrada);

	public void recalculaFiabilidade(String aDecisão, int aGdu);

	public boolean validaCategoria(int cilindrada);
}