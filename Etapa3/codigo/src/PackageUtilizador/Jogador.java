package PackageUtilizador;

import java.util.Objects;

public class Jogador extends Utilizador {
	private int _pontuacaoTotal;

	public Jogador(int _pontuacaoTotal) {
		this._pontuacaoTotal = _pontuacaoTotal;
	}

	public Jogador(String _username, String _password, int _pontuacaoTotal) {
		super(_username, _password);
		this._pontuacaoTotal = _pontuacaoTotal;
	}

	public int get_pontuacaoTotal() {
		return _pontuacaoTotal;
	}

	public void set_pontuacaoTotal(int _pontuacaoTotal) {
		this._pontuacaoTotal = _pontuacaoTotal;
	}

	public void aumentaClassficacao(int increment) {
		this._pontuacaoTotal+=increment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Jogador jogador = (Jogador) o;
		return _pontuacaoTotal == jogador._pontuacaoTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), _pontuacaoTotal);
	}

	@Override
	public String toString() {
		return "Jogador{" +
				"_username= " + this.get_username() + "\n" +
				"_password= " + this.get_password() + "\n" +
				"_pontuacaoTotal= " + _pontuacaoTotal +
				'}';
	}
}