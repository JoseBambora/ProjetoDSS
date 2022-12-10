import PackageCarro.ModoMotor;
import PackageUtilizador.UtilizadoresDAO;
import PackageCircuito.CircuitoDAO;
import PackagePiloto.PilotoDAO;
import PackageCarro.CarroDAO;
import PackageCampeonato.CampeonatoDAO;
import PackageCampProva.CampeonatoProvaDAO;
import PackageUtilizador.Utilizador;
import PackagePiloto.Piloto;
import PackageCarro.Carro;
import PackageCarro.Categoria;
import PackageCircuito.Circuito;
import PackageCampeonato.Campeonato;
import PackageUtilizador.Jogador;

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
		throw new UnsupportedOperationException();
	}

	public Map<String, Boolean> verificaExistênciaJogadores(List<String> aNomes) {
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

	public void guardaAfinacao(String aIdCampProva, Jogador aNome, float aPAC, ModoMotor aModo, String aPneus) {
		throw new UnsupportedOperationException();
	}

	public List<Jogador> getClassificacoesGlobais() {
		throw new UnsupportedOperationException();
	}

	public Map<String, Integer> getClassificacoesCorrida(String aIdCampeonato, String aIdProva) {
		throw new UnsupportedOperationException();
	}

	public void atualizaPontuacaoGlobal(String aIdCampeonatoProva, String aNome) {
		throw new UnsupportedOperationException();
	}
}