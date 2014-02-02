package Geral;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Imagem extends JLabel {
	
	private int x, y, altura, largura;

	public Imagem (String caminhoDaImagem) {
		
		caminhoDaImagem = Principal.getPathAtual() + "\\" + caminhoDaImagem;			

		Icon imagem = new ImageIcon(caminhoDaImagem);
		setIcon(imagem);
		altura = imagem.getIconHeight();
		largura = imagem.getIconWidth();
	}
	
	public void setCoordenada (int x, int y) {
		this.x = x;
		this.y = y;
		
		setBounds(x,y,largura,altura);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
