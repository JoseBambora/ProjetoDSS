public interface IPiloto {

	public Integer simulaDecisão(Caracteristica aSituacao, List<String> aClassificacao);
}public interface ISimulador {

	public void adicionaUtilizador(Utilizador aUtilizador);

	public Boolean validarRegistoUser(String aNome);

	public Map<String, Boolean> verificaExistênciaJogadores(List<String> aNomes);

	public Boolean validarDadosUser(String aNome, String aPass);

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

	public Boolean validaRegistoCircuito(String aNome, map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Integer aVoltas);

	public Map<String, Integer> getMelhoresVoltas(String aIdPista);

	public Boolean verificaMelhorVolta(String aPista, String aPiloto, Integer aTempo);

	public void atualizaMelhorVolta(String aIdPista, String aIdPiloto, Integer aTempo);

	public void validaRegistoCampeonato(String aNome, List<String> aNomescircuitos);

	public void adicionaCircuito(Circuito aPista);

	public Boolean validaDadosCircuito(String aNome, List<Strings> aPistas);

	public Boolean validarNumeroCurvasChicanes(Integer aCurvas, Integer aChicanes);

	public Integer calcularNumeroRetas(Integer aCurvas, Integer aChicanes);

	public Boolean validaGDUCarateristicas(map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes);

	public Boolean verificaExistenciaCarro(String aMarca, String aModelo);

	public void adicionaCampeonato(Campeonato aCampeonato);

	public Boolean validarCampeonato(String aNome);

	public void alteraEstDisponivel(String aIdCampeonato);

	public void resgistaEscolhas(String aPiloto, String aCarro, String aPneus);

	public void configuraCampeonato(String aCampnome, List<String> aJogadores, Map<String, String> aEscolhaPilotos, Map<String, String> aEscolhaCarros);

	public void guardaAfinacao(String aIdCampProva, Jogador aNome, float aPAC, Modo aModo, String aPneus);

	public List<Jogadores> getClassificacoesGlobais();

	public Map<String, Integer> getClassificacoesCorrida(String aIdCampeonato, String aIdProva);

	public void atualizaPontuacaoGlobal(String aIdCampeonatoProva, String aNome);
}