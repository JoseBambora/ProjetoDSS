public class Circuito implements ICircuito {
	private String _nome;
	private int _distancia;
	private int _voltas;
	private Map<Strpublic class Circuito implements ICircuito {
	private String _nome;
	private int _distancia;
	private int _voltas;
	private Map<String, Integer> _melhoresVoltas;
	private Caracteristica _caracteristica;

	public Boolean validaRegistoCircuito(map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes, Integer aVoltas) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaNumeroCurvas(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	public void calculaNrRetas(Integer aNrretas, Integer aNrchicanes) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaMelhorVolta(String aPiloto, Integer aTempo) {
		throw new UnsupportedOperationException();
	}

	public void atualizaMelhoresVoltas(Map<String, Integer> aTempos) {
		throw new UnsupportedOperationException();
	}
}