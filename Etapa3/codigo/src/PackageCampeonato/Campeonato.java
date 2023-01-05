package PackageCampeonato;

import PackageCircuito.CircuitoDAO;

public class Campeonato {
	private String _nome;
	
	private Boolean _disponivel;
	
	private CircuitoDAO _provas;
	
	
	public Campeonato() {
		this._nome = "untitled";
		this._disponivel = false;
	}

	public Campeonato(String _nome, Boolean _disponivel) {
		this._nome = _nome;
		this._disponivel = _disponivel;
	}

	public void alteraEstadoDisp() {
		throw new UnsupportedOperationException();
	}
	
	public String get_nome() {
		return _nome;
	}
	
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	
		public Boolean get_disponivel() {
			return _disponivel;
		}
	
		public void set_disponivel(Boolean _disponivel) {
			this._disponivel = _disponivel;
		}
	public CircuitoDAO get_provas() {
		return _provas;
	}

	public void set_provas(CircuitoDAO _provas) {
		this._provas = _provas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_nome == null) ? 0 : _nome.hashCode());
		result = prime * result + ((_disponivel == null) ? 0 : _disponivel.hashCode());
		result = prime * result + ((_provas == null) ? 0 : _provas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campeonato other = (Campeonato) obj;
		if (_nome == null) {
			if (other._nome != null)
				return false;
		} else if (!_nome.equals(other._nome))
			return false;
		if (_disponivel == null) {
			if (other._disponivel != null)
				return false;
		} else if (!_disponivel.equals(other._disponivel))
			return false;
		if (_provas == null) {
			if (other._provas != null)
				return false;
		} else if (!_provas.equals(other._provas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return _nome;
	}
}