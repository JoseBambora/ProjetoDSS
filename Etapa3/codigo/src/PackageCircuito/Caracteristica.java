package PackageCircuito;

//import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.False;

public class Caracteristica implements ICaracteristica {
	private int _gdu;
	public Circuito _unnamed_Circuito_;
	
	// o circuito tem 10 rectas e apresenta a lista de curvas e rectas de modo a que 
	// o José indique o grau de dificuldade de ultrapassagem (GDU) em cada uma 
	// (as chicanes têm sempre um GDU de difícil). Para as rectas 1 e 6, e curvas 2 e 3,
	// indica um GDU de possível. Para as rectas 4, 5, 7 e 8, e curvas 4, 5, 7 e 8,
	// indica um GDU de impossível. Para os restantes, indica um GDU de difícil.
	
	// 1 - possivel
	// 2 - dificil
	// 3 - impossivel

	public Boolean validaGDU(Integer aGdu) {
		if(aGdu != 1 || aGdu != 2 || aGdu != 3)
			return false;
		else
		{
			return true;
		}
	}
}
