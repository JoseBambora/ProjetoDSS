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

	public static String[] getCondutor(String key)
	{
		return key.split(",");
	}
	
	public String get_nome() {
		return _nome;
	}
	
	public Float get_SVA() {
		return _sva;
	}
	
	public Float get_CTS() {
		return _cts;
	}
	
	public void set_Nome(String nome) {
		this._nome = nome;
	}
	
	public void set_SVA(float sva) {
		this._sva = sva;
	}
	
	public void set_CTS(float cts) {
		this._cts = cts;
	}
	private String simulaFacil(List<String> aClassificacao)
	{
		return  "";

	}
	private String simulaMedio(List<String> aClassificacao)
	{
		return  "";

	}
	private String simulaDificil(List<String> aClassificacao)
	{
		return  "";
	}
    @Override
	public String simulaDecisao(Caracteristica aSituacao, List<String> aClassificacao)
	{
		String res = "";
		switch (aSituacao.get_gdu())
		{
			case 1 -> res = simulaFacil(aClassificacao);
			case 2 -> res = simulaMedio(aClassificacao);
			case 3 -> res = simulaDificil(aClassificacao);
		}
		return res;
	}

	@Override
	public String toString() {
		return this._nome;
	}
}
