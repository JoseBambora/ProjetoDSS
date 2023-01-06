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

import java.util.*;

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
		Escolha escolha = new Escolha(aJogador,aPiloto,aCarro, aCarro.get_pac(), aCarro.get_unnamed_IConjuntoPneus_(),aCarro.get_motor().get_modo());
		ClassificacoesDAO.getInstance().put(ClassificacoesDAO.getInstance().generateKey(this._id,aJogador), 0);
		EscolhasDAO.getInstance().put(EscolhasDAO.getInstance().generateKey(this._id,aJogador), escolha);
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

	private void ultrapassagens(List<String> classificacao, List<String> decisoes, Random random)
	{
		for(int i = classificacao.size()-1; i > 0; i--)
		{
			String jog1 = classificacao.get(i);
			String jog2 = classificacao.get(i-1);
			String da = decisoes.get(i);
			String df = decisoes.get(i-1);
			if(!da.equals(df))
			{
				float probabilidade = random.nextFloat(101);
				if((da.equals("Agressivo") && df.equals("Normal")) || (da.equals("Normal") && df.equals("Conservador")))
				{
					if(probabilidade > 50)
					{
						classificacao.set(i,jog1);
						classificacao.set(i-1,jog2);
						i--;
					}
				}
				else if(da.equals("Agressivo") && df.equals("Conservador"))
				{
					if(probabilidade > 30)
					{
						classificacao.set(i,jog1);
						classificacao.set(i-1,jog2);
						i--;
					}
				}
			}
		}
	}
	public void simulaProva(String aPista, Map<String,Integer> afinacoes, int nrProvas) throws InterruptedException {
		Circuito circuito = CircuitoDAO.getInstace().get(aPista);
		int volta = circuito.get_Voltas();
		List<Caracteristica> list = circuito.get_caracteristica();
		List<String> classificacao = EscolhasDAO.getInstance().initSimulacao(this._id);
		List<String> desclassificados = new ArrayList<>();
		Map<String, Escolha> escolhas = new HashMap<>();
		for(String nomeJ : classificacao)
		{
			if(!afinacoes.containsKey(nomeJ))
				afinacoes.put(nomeJ,0);
			if(afinacoes.get(nomeJ) < 2 * nrProvas / 3)
			{
				if (TextUI.pretendeAfinar(nomeJ)) {
					float pac = TextUI.getPac();
					ModoMotor modo = TextUI.getModo();
					Pneu pneu = TextUI.getPneus();
					EscolhasDAO.getInstance().guardaAfinacao(this._id+","+nomeJ, pac, modo, pneu);
				}
			}
			String pk = this._id+","+nomeJ;
			Escolha e = EscolhasDAO.getInstance().get(pk);
			escolhas.put(nomeJ,e);
		}
		Random random = new Random();
		List<String> decisoes = new ArrayList<>();
		int condMeteorologicas = random.nextInt(2);
		for(int i = 0; i < volta; i++)
		{
			for(Caracteristica caracteristica : list)
			{
				List<String> aux = new ArrayList<>();
				for(String nomeJ : classificacao)
				{
					int size = classificacao.size();
					Escolha e = escolhas.get(nomeJ);
					Piloto piloto = e.get_piloto();
					Carro c = e.get_carro();
					String decisao = piloto.simulaDecisao(caracteristica,classificacao.indexOf(nomeJ),size,condMeteorologicas);
					decisoes.add(decisao);
					c.reduzCapacidadeCombustivel(decisao);
					float fiabilidadeAntiga = c.recalculaFiabilidade(decisao,caracteristica.get_gdu());
					c.reduzCapacidadePneu(decisao,caracteristica.get_gdu());
					float fiabilidade = random.nextFloat(30);
					if(fiabilidade > fiabilidadeAntiga || c.surrender())
						aux.add(nomeJ);
				}
				aux.forEach(s -> {classificacao.remove(s); desclassificados.add(s);});
				ultrapassagens(classificacao,decisoes,random);
				decisoes.clear();
			}
			TextUI.printClassifacao(classificacao);// Imprimir classificação
			TextUI.printDesclassificados(desclassificados); //Imprimir desclassificados
		}
		Map<String,Integer> jogadorPontos = new HashMap<>();
		int pontosP = 30;
		for(String nomejog : classificacao){
			jogadorPontos.put(nomejog,pontosP);
			if(pontosP>0) pontosP-=3;
		}
		for (String key : jogadorPontos.keySet()) {
			ClassificacoesCorridasDAO.getInstance().addPontuacao(this._id,circuito.get_nome(),key,jogadorPontos.get(key));
			ClassificacoesDAO.getInstance().addPontuacao(this._id,key,jogadorPontos.get(key));
		}
	}

	public Map<String, Integer> simulaCampeonato() throws InterruptedException {
		Map<String,Integer> afinacoesNum = new HashMap<>();
		List<String> circuitos = CircuitoDAO.getInstace().getCircuitosCampeonato(_campeonato.get_nome());
		for(String circuito : circuitos)
		{
			simulaProva(circuito,afinacoesNum,circuitos.size());
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