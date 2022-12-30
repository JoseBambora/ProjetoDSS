package PackageCampProva;

import PackageCarro.IConjuntoPneus;
import PackageCarro.Pneu;
import PackageUtilizador.Jogador;
import PackagePiloto.Piloto;
import PackageCarro.Carro;
import PackageCarro.ModoMotor;

public class Escolha {
	private Jogador _jogador;
	private Piloto _piloto;
	private Carro _carro;
	private Float _pac;
	private Pneu _pneu;
	private ModoMotor _modo;
	public Escolha() {
		this._jogador = null;
		this._piloto = null;
		this._carro = null;
		this._pac = null;
		this._pneu = null;
		this._modo = null;
	}

	public Escolha(Jogador _jogador, Piloto _piloto, Carro _carro, Float _pac, Pneu _pneu, ModoMotor _modo) {
		this._jogador = _jogador;
		this._piloto = _piloto;
		this._carro = _carro;
		this._pac = _pac;
		this._pneu = _pneu;
		this._modo = _modo;
	}

	public Jogador get_jogador() {
		return _jogador;
	}

	public void set_jogador(Jogador _jogador) {
		this._jogador = _jogador;
	}

	public Piloto get_piloto() {
		return _piloto;
	}

	public void set_piloto(Piloto _piloto) {
		this._piloto = _piloto;
	}

	public Carro get_carro() {
		return _carro;
	}

	public void set_carro(Carro _carro) {
		this._carro = _carro;
	}

	public Float get_pac() {
		return _pac;
	}

	public void set_pac(Float _pac) {
		this._pac = _pac;
	}

	public Pneu get_pneu() {
		return _pneu;
	}

	public void set_pneu(Pneu _pneu) {
		this._pneu = _pneu;
	}

	public ModoMotor get_modo() {
		return _modo;
	}

	public void set_modo(ModoMotor _modo) {
		this._modo = _modo;
	}
}