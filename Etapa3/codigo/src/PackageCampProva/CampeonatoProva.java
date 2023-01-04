package PackageCampProva;

import PackageCampeonato.Campeonato;
import PackageCampeonato.CampeonatoDAO;
import PackageCarro.ModoMotor;
import PackageCarro.Carro;
import PackageCarro.Pneu;
import PackageCircuito.Caracteristica;
import PackageCircuito.Circuito;
import PackageCircuito.CircuitoDAO;
import PackagePiloto.Piloto;
import PackageIO.TextUI;
import PackageUtilizador.UtilizadoresDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public void adicionajogador(String aJogador, Carro aCarro, Piloto aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaAdesao(String aPiloto) {
		throw new UnsupportedOperationException();
	}

	public void guardaAfinacao(String aNome, float aPAC, ModoMotor aModo, Pneu aPneus) {
		EscolhasDAO.getInstance().guardaAfinacao(aNome,aPAC,aModo,aPneus);
	}

	public Map<String, Integer> getClassificacoesCorrida(String aProva) {
		Map<String,Integer> pontuacoesJogadores = new HashMap<>();
		Set<Map.Entry<String, Integer>> aux = ClassificacoesCorridasDAO.getInstance().entrySet();
		for(Map.Entry<String, Integer> entry : aux){
			String pk = entry.getKey();
			String nomeJogador = ClassificacoesCorridasDAO.getCampProvaUsername(pk)[2];
			pontuacoesJogadores.put(nomeJogador,entry.getValue());
		}
		return pontuacoesJogadores;
	}

	public void atualizaPontuacao(String aNome, String circuito, Integer aClassificacao) {
		ClassificacoesDAO.getInstance().addPontuacao(this._id,aNome,aClassificacao);
		ClassificacoesCorridasDAO.getInstance().addPontuacao(this._id,circuito,aNome,aClassificacao);
	}

	public void simulaProva(String aPista)
	{
		Circuito circuito = CircuitoDAO.getInstace().get(aPista);
		int volta = circuito.get_Voltas();
		List<Caracteristica> list = circuito.get_caracteristica();
		List<String> classificacao = EscolhasDAO.getInstance().initSimulacao(this._id);
		for(int i = 0; i < volta; i++)
		{
			for(Caracteristica caracteristica : list)
			{

			}
			TextUI.printClassifacao(classificacao);// Imprimir classificação
		}
	}

	public Map<String, Integer> simulaCampeonato()
	{
		List<String> circuitos = CircuitoDAO.getInstace().getCircuitosCampeonato(_campeonato.get_nome());
		for(String circuito : circuitos)
		{
			simulaProva(circuito);
		}
		return ClassificacoesDAO.getInstance().getClassificacoes(this._id);
	}

	@Override
	public String toString() {
		return "CampeonatoProva{" +
				"_id=" + _id +
				", _campeonato=" + _campeonato +
				'}';
	}
}