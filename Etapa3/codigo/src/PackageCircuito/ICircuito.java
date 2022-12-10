package PackageCircuito;

import java.util.Map;

public interface ICircuito {

	public Boolean validaRegistoCircuito(Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes, Integer aVoltas);

	public Boolean validaNumeroCurvas(Integer aCurvas, Integer aChicanes);

	public void calculaNrRetas(Integer aNrretas, Integer aNrchicanes);

	public Boolean verificaMelhorVolta(String aPiloto, Integer aTempo);

	public void atualizaMelhoresVoltas(Map<String, Integer> aTempos);
}