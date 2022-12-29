package PackageUtilizador;

import java.util.Objects;

public class Utilizador {
	private String _username;
	private String _password;

	public Utilizador(){
		this._username = "";
		this._password="";
	}

	public Utilizador(String _username, String _password){
		this._username = _username;
		this._password = _password;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Utilizador that = (Utilizador) o;
		return this._username.equals(that._username) && this._password.equals(that._password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_username, _password);
	}

	public boolean isAdmin(){
		return this instanceof Admin;
	}

	public boolean isJogador(){
		return this instanceof Jogador;
	}

	@Override
	public String toString() {
		return "Utilizador{\n" +
				"_username='" + _username + '\n' +
				"_password='" + _password + '\n' +
				'}';
	}

    public int get_pontuacaoTotal() {
		int pontuacao = 0;
		if (this instanceof Jogador) pontuacao = ((Jogador) this).get_pontuacaoTotal();
		return pontuacao;
	}
}