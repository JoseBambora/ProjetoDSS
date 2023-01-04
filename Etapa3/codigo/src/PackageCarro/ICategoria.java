package PackageCarro;

public interface ICategoria {

	public void calculaFiabilidade(int aCilindrada);

	public float recalculaFiabilidade(String aDecisão, int aGdu);

	public boolean validaCategoria(int cilindrada);
}