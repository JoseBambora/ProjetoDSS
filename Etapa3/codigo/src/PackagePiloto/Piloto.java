package PackagePiloto;

import PackageCircuito.Caracteristica;

import java.util.List;

public class Piloto implements IPiloto {
	private String _nome;
	private float _sva;
	private float _cts;

	public Piloto(String nome, double d, double e) {
		this._nome = nome;
		this._sva = (float) d;
		this._cts = (float) e;
    }

	public String insertCommandPiloto()
	{
		return '\'' + this._nome + '\'' + "," +'\'' + this._sva +'\'' + "," + this._cts;
	}

	public static String[] getCondutor(String key)
	{
		return key.split(",");
	}
	
	public void set_Nome(int nome) {
		this._nome = nome;
	}

    @Override
	public Integer simulaDecisao(Caracteristica aSituacao, List<String> aClassificacao) {
		return null;
	}

	public String get_nome() {
		return _nome;
	}
}
