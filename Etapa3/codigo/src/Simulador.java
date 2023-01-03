import PackageCampProva.CampeonatoProva;
import PackageCarro.*;
import PackageUtilizador.UtilizadoresDAO;
import PackageCircuito.CircuitoDAO;
import PackagePiloto.PilotoDAO;
import PackageCampeonato.CampeonatoDAO;
import PackageCampProva.CampeonatoProvaDAO;
import PackageUtilizador.Utilizador;
import PackagePiloto.Piloto;
import PackageCircuito.Circuito;
import PackageCampeonato.Campeonato;
import PackageUtilizador.Jogador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Simulador implements ISimulador {
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
		return ! UtilizadoresDAO.getInstance().containsKey(aNome);
	}

	public Map<String, Boolean> verificaExistênciaJogadores(List<String> aNomes) {
		throw new UnsupportedOperationException();
	}

	public Boolean validarDadosUser(String aNome, String aPass)
	{
		UtilizadoresDAO utilizadoresDAO = UtilizadoresDAO.getInstance();
		return utilizadoresDAO.containsKey(aNome) && utilizadoresDAO.get(aNome).get_password().equals(aPass);
	}

	public void adicionaPiloto(Piloto aPiloto) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaExistenciaPiloto(String aPiloto) {
		return PilotoDAO.getInstace().containsKey(aPiloto);
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

	public Boolean validaRegistoCircuito(String aNome, Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Integer aVoltas) {
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


	public Boolean validaDadosCircuito(String aNome, List<String> aPistas) {
		return null;
	}

	public Boolean validarNumeroCurvasChicanes(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	public Integer calcularNumeroRetas(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean validaGDUCarateristicas(Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes) {
		return null;
	}

	public Boolean verificaExistenciaCarro(String carro) {
		return CarroDAO.getInstace().containsKey(carro);
	}
	public List<Campeonato> getCampeonatos()
	{
		return new ArrayList<>(CampeonatoDAO.getInstance().values());
	}

	public List<Piloto> getPilotos()
	{
		return new ArrayList<>(PilotoDAO.getInstace().values());
	}
	public List<Carro> getCarros()
	{
		return new ArrayList<>(CarroDAO.getInstace().values());
	}

	@Override
	public List<Pneu> getPneus() {
		return CarroDAO.getInstace().getPneus();
	}

	@Override
	public List<ModoMotor> getModos() {
		return CarroDAO.getInstace().getModos();
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

	public int configuraCampeonato(String aCampnome, List<String> aJogadores, Map<String, String> aEscolhaPilotos, Map<String, String> aEscolhaCarros)
	{
		Campeonato campeonato = CampeonatoDAO.getInstance().get(aCampnome);
		CampeonatoProva campeonatoProva = new CampeonatoProva(campeonato);
		for(String player : aJogadores)
		{
			String pilotoID = aEscolhaPilotos.get(player);
			String carroID = aEscolhaCarros.get(player);
			Piloto piloto = PilotoDAO.getInstace().get(pilotoID);
			Carro carro = CarroDAO.getInstace().get(carroID);
			campeonatoProva.adicionajogador(player,carro,piloto);
		}
		int key =  campeonatoProva.get_id();
		CampeonatoProvaDAO.getInstance().put(key,campeonatoProva);
		return key;
	}

	public void guardaAfinacao(String aIdCampProva, String aNome, float aPAC, ModoMotor aModo, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public List<Jogador> getClassificacoesGlobais() {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getClassificacoesCorrida(String aIdCampeonato, String aIdProva) {
		throw new UnsupportedOperationException();
	}

	public void atualizaPontuacaoGlobal(int aIdCampeonatoProva, String aNome)
	{
		CampeonatoProva camp = CampeonatoProvaDAO.getInstance().get(aIdCampeonatoProva);
		if(CampeonatoProvaDAO.getInstance().containsKey(aIdCampeonatoProva))
		{
			Integer pontuacao = camp.get_classificacao().get(aNome);
			if(pontuacao != null)
				UtilizadoresDAO.getInstance().addPontuacao(aNome,pontuacao);
		}
	}
	public boolean existeCampeonato(String name)
	{
		return CampeonatoDAO.getInstance().containsKey(name);
	}

	public Map<String,Integer> simulaCampeonato(int campProva)
	{
		CampeonatoProva campeonatoProva = CampeonatoProvaDAO.getInstance().get(campProva);
		return campeonatoProva.simulaCampeonato();
	}
	public boolean isJogador(String name)
	{
		return UtilizadoresDAO.getInstance().containsKey(name) && UtilizadoresDAO.getInstance().get(name).isJogador();
	}

	@Override
	public Pneu getPneu(int id) {
		return CarroDAO.getInstace().getPneus(id);
	}

	@Override
	public ModoMotor getModo(int id) {
		return CarroDAO.getInstace().getModo(id);
	}
}