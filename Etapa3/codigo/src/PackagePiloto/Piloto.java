package PackagePiloto;

import PackageCircuito.Caracteristica;

import java.util.List;

public class Piloto implements IPiloto {
	private String _nome;
	private float _sva;
	private float _cts;

	@Override
	public Integer simulaDecisao(Caracteristica aSituacao, List<String> aClassificacao) {
		return null;
	}
}