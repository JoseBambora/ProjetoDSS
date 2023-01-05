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

	/**
	 * case "Agressivo" -> this.agressivo(aGdu);
	 * 			case "Normal" -> this.normal(aGdu);
	 * 			case "Conservador" -> this.conservador(aGdu);
	 */
	private String simulaFacil(int posicao, int size, int metereologia)
	{
		if(posicao == 1)
		{
			if(_sva < 0.3f)
				return  "Conservador";
			else if(_sva < 0.7f)
				return "Normal";
			else
				return "Agressivo";
		}
		else
		{
			if(posicao > size/2 && _sva > 0.2f)
				return "Agressivo";
			else
				return "Normal";
		}

	}
	private String simulaMedio(int posicao, int size, int metereologia)
	{
		if(posicao == 1)
		{
			if(_sva < 0.4f)
				return  "Conservador";
			else if(_sva < 0.8f)
				return "Normal";
			else
				return "Agressivo";
		}
		else
		{
			if(posicao > size/2 && metereologia == 1 && _sva > 0.5f && _cts > 0.5f)
				return "Agressivo";
			else
				return "Normal";
		}
	}
	private String simulaDificil(int posicao, int size, int metereologia)
	{
		if(metereologia == 0 || posicao == 1)
		{
			if(_sva < 0.7f)
				return  "Conservador";
			else
				return "Normal";
		}
		else
		{
			if(posicao > size/2 + size/3 && _cts > 0.8f && _sva > 0.7f)
				return "Agressivo";
			else
				return "Normal";
		}
	}
    @Override
	public String simulaDecisao(Caracteristica aSituacao, int posicao, int size, int metereologia)
	{
		String res = "";
		switch (aSituacao.get_gdu())
		{
			case 1 -> res = simulaFacil(posicao, size, metereologia);
			case 2 -> res = simulaMedio(posicao, size, metereologia);
			case 3 -> res = simulaDificil(posicao, size, metereologia);
		}
		return res;
	}

	@Override
	public String toString() {
		return this._nome;
	}
}
