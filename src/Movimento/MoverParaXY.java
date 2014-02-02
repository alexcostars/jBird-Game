package Movimento;

/*
Ao receber este objeto, o personagem ir� se teletransportar para a posi��o X e Y

@param x		define para onde o personagem deve ser teletransportado (posi��o X)
@param y		define para onde o personagem deve ser teletransportado (posi��o Y)
@param andar	ap�s ser teletransportado, para onde o personagem deve andar

@author	Alex Costa
@version	0.5.0
@since		0.0
*/

public class MoverParaXY extends Movimento {
	private int x, y;
	Andar andar;
	
	public MoverParaXY (int x, int y, Andar andar) {
		this.x = x;
		this.y = y;
		this.andar = andar;
	}

	public int getMovimentoX() {
		return x;
	}

	public int getMovimentoY() {
		return y;
	}
	
	public Andar getAndar() {
		return andar;
	}
		
	

}
