public class SC extends Categoria {
}public class Simulador implements ISimulador {
	private UtilizadoresDAO _utilizadores;
	private CircuitoDAO _pistas;
	private PilotoDAO _pilotos;
	private CarroDAO _carros;
	public CampeonatoDAO _unnamed_CampeonatoDAO_;
	private CampeonatoProvaDAO _campeonatosRealizados;

	public void adicionaUtilizador(Utilizador aUtilizador) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarRegistoUser(String aNome) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Boolean> verificaExist�nciaJogadores(List<String> aNomes) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarDadosUser(String aNome, String aPass) {
		throw new UnsupportedOperationException();
	}

	public void adicionaPiloto(Piloto aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaPiloto(String aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaRegistoPiloto(String aNome, Float aCts, Float aSva) {
		throw new UnsupportedOperationException();
	}

	public void adicionaCarro(Carro aCarro) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaAfinacaoPAC(Float aPAC) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaModo(String aModo) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarRegistoCarro(String aMarca, String aModelo, Integer aPotenciac, Integer aPotenciae, Float aPac) {
		throw new UnsupportedOperationException();
	}

	public void modoMotorNomal(String aIdCarro) {
		throw new UnsupportedOperationException();
	}

	public void calculaFiabilidade(String aIdCarro) {
		throw new UnsupportedOperationException();
	}

	public void capacidadeComb100(Carro aIdCarro) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaCilindrada(Categoria aCategoria, Integer aCilindrada) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaPneus(String aPneus) {
		throw new UnsupportedOperationException();
	}

	public Boolean existeCircuitos(List<String> aPistas) {
		throw new UnsupportedOperationException();
	}

	public boolean existeCircuito(String aIdPista) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaRegistoCircuito(String aNome, map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Integer aVoltas) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getMelhoresVoltas(String aIdPista) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaMelhorVolta(String aPista, String aPiloto, Integer aTempo) {
		throw new UnsupportedOperationException();
	}

	public void atualizaMelhorVolta(String aIdPista, String aIdPiloto, Integer aTempo) {
		throw new UnsupportedOperationException();
	}

	public void validaRegistoCampeonato(String aNome, List<String> aNomescircuitos) {
		throw new UnsupportedOperationException();
	}

	public void adicionaCircuito(Circuito aPista) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaDadosCircuito(String aNome, List<Strings> aPistas) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarNumeroCurvasChicanes(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	public Integer calcularNumeroRetas(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	public Boolean validaGDUCarateristicas(map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaCarro(String aMarca, String aModelo) {
		throw new UnsupportedOperationException();
	}

	public void adicionaCampeonato(Campeonato aCampeonato) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarCampeonato(String aNome) {
		throw new UnsupportedOperationException();
	}

	public void alteraEstDisponivel(String aIdCampeonato) {
		throw new UnsupportedOperationException();
	}

	public void resgistaEscolhas(String aPiloto, String aCarro, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public void configuraCampeonato(String aCampnome, List<String> aJogadores, Map<String, String> aEscolhaPilotos, Map<String, String> aEscolhaCarros) {
		throw new UnsupportedOperationException();
	}

	public void guardaAfinacao(String aIdCampProva, Jogador aNome, float aPAC, Modo aModo, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public List<Jogadores> getClassificacoesGlobais() {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getClassificacoesCorrida(String aIdCampeonato, String aIdProva) {
		throw new UnsupportedOperationException();
	}

	public void atualizaPontuacaoGlobal(String aIdCampeonatoProva, String aNome) {
		throw new UnsupportedOperationException();
	}
}