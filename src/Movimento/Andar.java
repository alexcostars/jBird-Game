package Movimento;

/*
 Ao receber este objeto, o personagem irá se mover para a posição X e Y
 
 @param x	define para onde o personagem deve andar (posição X)
 @param y	define para onde o personagem deve andar (posição y)
 
 @author	Alex Costa
 @version	0.3.0
 @since		0.0
 */

public class Andar extends Movimento {
	private int x, y;
	
	public Andar (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getMovimentoX() {
		return x;
	}

	public int getMovimentoY() {
		return y;
	}
}
