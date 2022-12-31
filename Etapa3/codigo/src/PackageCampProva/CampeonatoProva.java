package PackageCampProva;

import PackageCampeonato.Campeonato;
import PackageCampeonato.CampeonatoDAO;
import PackageCarro.ModoMotor;
import PackageUtilizador.Jogador;
import PackageCarro.Carro;
import PackagePiloto.Piloto;

import java.util.List;
import java.util.Map;

public class CampeonatoProva {
	private int _id;
	private static ClassificacoesDAO _classificacao;
	private static ClassificacoesCorridasDAO _classificacoesCorridas;
	private static EscolhasDAO _escolhas;
	private Campeonato _campeonato;

	public CampeonatoProva(int _id ,Campeonato _campeonato) {
		this._id = _id;
		this._campeonato = _campeonato;
	}

	public CampeonatoProva(Campeonato _campeonato) {
		this._campeonato = _campeonato;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Campeonato get_campeonato() {
		return _campeonato;
	}

	public void set_campeonato(Campeonato _campeonato) {
		this._campeonato = _campeonato;
	}

	public ClassificacoesDAO get_classificacao() {
		return _classificacao;
	}

	public void set_classificacao(ClassificacoesDAO _classificacao) {
		CampeonatoProva._classificacao = _classificacao;
	}

	public ClassificacoesCorridasDAO get_classificacoesCorridas() {
		return _classificacoesCorridas;
	}

	public void set_classificacoesCorridas(ClassificacoesCorridasDAO _classificacoesCorridas) {
		CampeonatoProva._classificacoesCorridas = _classificacoesCorridas;
	}

	public EscolhasDAO get_escolhas() {
		return _escolhas;
	}

	public void set_escolhas(EscolhasDAO _escolhas) {
		CampeonatoProva._escolhas = _escolhas;
	}

	public Campeonato getCampeonato() {
		return _campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this._campeonato = campeonato;
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