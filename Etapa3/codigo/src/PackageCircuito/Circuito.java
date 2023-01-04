package PackageCircuito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Circuito implements ICircuito {
	private String _nome;
	private int _distancia;
	private int _voltas;
	private String _campeonato;
	private List<Caracteristica> _caracteristica;
	public MelhoresVoltasDAO _unnamed_MelhoresVoltasDAO_;

	public Circuito(String nome, int d, int e, String camp, List<Caracteristica> list) {
		this._nome = nome;
		this._distancia = d;
		this._voltas =  e;
		this._campeonato = camp;
		this._caracteristica = new ArrayList<>(list);
    }
	
	public String get_nome() {
		return _nome;
	}
	
	public int get_Dist() {
		return _distancia;
	}
	
	public int get_Voltas() {
		return _voltas;
	}

	public String get_Campeonato() {
		return _campeonato;
	}
	
	public void set_Nome(String nome) {
		this._nome = nome;
	}
	
	public void set_Dist(int dist) {
		this._distancia = dist;
	}
	
	public void set_Voltas(int voltas) {
		this._voltas = voltas;
	}

	public List<Caracteristica> get_caracteristica() {
		return new ArrayList<>(_caracteristica);
	}

	@Override
	public Boolean validaRegistoCircuito(Map<Integer, Integer> aRetas, Map<Integer, Integer> aCurvas, Map<Integer, Integer> aChicanes, Integer aVoltas) {
		return null;
	}

	public Boolean validaNumeroCurvas(Integer aCurvas, Integer aChicanes) {
		throw new UnsupportedOperationException();
	}

	public void calculaNrRetas(Integer aNrretas, Integer aNrchicanes) {
		throw new UnsupportedOperationException();
	}

	public Boolean verificaMelhorVolta(String aPiloto, Integer aTempo) {
		throw new UnsupportedOperationException();
	}

	public void atualizaMelhoresVoltas(Map<String, Integer> aTempos) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return this._nome + " " + this._voltas + " " + this.get_caracteristica();
	}
}
