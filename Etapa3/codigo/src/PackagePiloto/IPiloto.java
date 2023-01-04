package PackagePiloto;

import PackageCircuito.Caracteristica;

import java.util.List;

public interface IPiloto {

	public String simulaDecisao(Caracteristica aSituacao, int posicao, int size, int metereologia);
}