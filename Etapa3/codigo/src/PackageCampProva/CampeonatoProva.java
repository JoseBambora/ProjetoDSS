package PackageCampProva;

import PackageCarro.ModoMotor;
import PackageUtilizador.Jogador;
import PackageCarro.Carro;
import PackagePiloto.Piloto;

import java.util.List;
import java.util.Map;

public class CampeonatoProva {
	private String _id;
	private ClassificacoesDAO _classificacao;
	private ClassificacoesCorridasDAO _classificacoesCorridas;
	private EscolhasDAO _escolhas;

	public void adicionajogador(Jogador aJogador, Carro aCarro, Piloto aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaAdesao(String aPiloto) {
		throw new UnsupportedOperationException();
	}

	public void guardaAfinacao(String aNome, float aPAC, ModoMotor aModo, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getClassificacoesCorrida(String aProva) {
		throw new UnsupportedOperationException();
	}

	public void atualizaPontuacao(String aNome, Integer aClassificacao) {
		throw new UnsupportedOperationException();
	}

	public void adicionaJogadores(List<Piloto> aJogadores, Map<String, Piloto> aPilotosEsc, Map<String, Carro> aCarrosEsc) {
		throw new UnsupportedOperationException();
	}

	public void simulaProva(String aPista) {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> simulaCampeonato() {
		throw new UnsupportedOperationException();
	}
}