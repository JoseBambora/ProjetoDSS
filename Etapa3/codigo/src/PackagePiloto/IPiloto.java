package PackagePiloto;

import PackageCircuito.Caracteristica;

import java.util.List;

public interface IPiloto {

	public Integer simulaDecisao(Caracteristica aSituacao, List<String> aClassificacao);
}