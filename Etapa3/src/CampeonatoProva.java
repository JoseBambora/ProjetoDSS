public class CampeonatoDAO {
	public Simulador _unnamed_Simulador_;
}public class CampeonatoProva {
	private String _id;
	public ClassificacoesDAO _unnamed_ClassificacoesDAO_;
	public ClassificacoesCorridasDAO _unnamed_ClassificacoesCorridasDAO_;
	public EscolhasDAO _unnamed_EscolhasDAO_;

	public void adicionajogador(Jogador aJogador, Carro aCarro, Piloto aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaAdesao(String aPiloto) {
		throw new UnsupportedOperationException();
	}

	public void guardaAfinacao(String aNome, float aPAC, Modo aModo, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getClassificacoesCorrida(String aProva) {
		throw new UnsupportedOperationException();
	}

	public void atualizaPontuacao(String aNome, Integer aClassificacao) {
		throw new UnsupportedOperationException();
	}

	public void adicionaJogadores(List<Pilotos> aJogadores, Map<String, Piloto> aPilotosEsc, Map<String, Carro> aCarrosEsc) {
		throw new UnsupportedOperationException();
	}

	public void simulaProva(String aPista) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> simulaCampeonato() {
		throw new UnsupportedOperationException();
	}
}