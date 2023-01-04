package PackagePiloto;

import PackageCircuito.Caracteristica;

import java.util.List;

public interface IPiloto {

	public String simulaDecisao(Caracteristica aSituacao, List<String> aClassificacao);
}