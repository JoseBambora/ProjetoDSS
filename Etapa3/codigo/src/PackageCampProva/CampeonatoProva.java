package PackageCampProva;

import PackageCampeonato.Campeonato;
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

	private Campeonato campeonato;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public ClassificacoesDAO get_classificacao() {
		return _classificacao;
	}

	public void set_classificacao(ClassificacoesDAO _classificacao) {
		this._classificacao = _classificacao;
	}

	public ClassificacoesCorridasDAO get_classificacoesCorridas() {
		return _classificacoesCorridas;
	}

	public void set_classificacoesCorridas(ClassificacoesCorridasDAO _classificacoesCorridas) {
		this._classificacoesCorridas = _classificacoesCorridas;
	}

	public EscolhasDAO get_escolhas() {
		return _escolhas;
	}

	public void set_escolhas(EscolhasDAO _escolhas) {
		this._escolhas = _escolhas;
	}

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