package PackageFacade;

import PackageCarro.ModoMotor;
import PackageCarro.Pneu;
import PackageUtilizador.Utilizador;
import PackagePiloto.Piloto;
import PackageCarro.Carro;
import PackageCarro.Categoria;
import PackageCircuito.Circuito;
import PackageCampeonato.Campeonato;
import PackageUtilizador.Jogador;

import java.util.List;
import java.util.Map;

public interface ISimulador {

	public void adicionaUtilizador(Utilizador aUtilizador);

	public Boolean validarRegistoUser(String aNome);

	public Map<String, Boolean> verificaExistênciaJogadores(List<String> aNomes);

	public Boolean validarDadosUser(Utilizador utilizador, String aPass);

	public void adicionaPiloto(Piloto aPiloto);

	public Boolean verificaExistenciaPiloto(String aPiloto);

	public Boolean validaRegistoPiloto(String aNome, Float aCts, Float aSva);

	public void adicionaCarro(Carro aCarro);

	public Boolean validaAfinacaoPAC(Float aPAC);

	public Boolean validaModo(String aModo);

	public Boolean validarRegistoCarro(String aMarca, String aModelo, Integer aPotenciac, Integer aPotenciae, Float aPac);

	public void modoMotorNomal(String aIdCarro);

	public void calculaFiabilidade(String aIdCarro);

	public void capacidadeComb100(Carro aIdCarro);

	public Boolean validaCilindrada(Categoria aCategoria, Integer aCilindrada);

	public Boolean validaPneus(String aPneus);

	public Boolean existeCircuitos(List<String> aPistas);

	public boolean existeCircuito(String aIdPista);

	public Boolean validaRegistoCircuito(String aNome, Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Integer aVoltas);

	public Map<String, Integer> getMelhoresVoltas(String aIdPista);

	public Boolean verificaMelhorVolta(String aPista, String aPiloto, Integer aTempo);

	public void atualizaMelhorVolta(String aIdPista, String aIdPiloto, Integer aTempo);

	public void validaRegistoCampeonato(String aNome, List<String> aNomescircuitos);

	public void adicionaCircuito(Circuito aPista);

	public Boolean validaDadosCircuito(String aNome, List<String> aPistas);

    public Boolean validarNumeroCurvasChicanes(Integer aCurvas, Integer aChicanes);

	public Integer calcularNumeroRetas(Integer aCurvas, Integer aChicanes);

	public Boolean validaGDUCarateristicas(Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes);

	public Boolean verificaExistenciaCarro(String carro);

	public void adicionaCampeonato(Campeonato aCampeonato);

	public Boolean validarCampeonato(String aNome);

	public void alteraEstDisponivel(String aIdCampeonato);

	public void resgistaEscolhas(String aPiloto, String aCarro, String aPneus);

	public int configuraCampeonato(String aCampnome, List<String> aJogadores, Map<String, String> aEscolhaPilotos, Map<String, String> aEscolhaCarros);

	public void guardaAfinacao(String aIdCampProva, String aNome, float aPAC, ModoMotor aModo, String aPneus);

	public List<Jogador> getClassificacoesGlobais();

	public Map<String, Integer> getClassificacoesCorrida(String aIdCampeonato, String aIdProva);

	public void atualizaPontuacaoGlobal(int aIdCampeonatoProva, String aNome);

	public Map<String,Integer> simulaCampeonato(int campProva);
	public boolean existeCampeonato(String name);

	public List<Campeonato> getCampeonatos();
	public List<Piloto> getPilotos();
	public List<Carro> getCarros();
	public List<Pneu> getPneus();
	public List<ModoMotor> getModos();
	public boolean isJogador(String name);
	public Pneu getPneu(int id);
	public ModoMotor getModo(int id);
}
