package Geral;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
como o jogo é todo desenvolvido em torno de imagens, foi criada essa classe para facilitar sua manipulação

@param caminhoDaImagen		define a URL simples da imagem nas pastas do sistema

@author	Alex Costa
@version	0.7.0
@since		0.0
*/

public class Imagem extends JLabel {
	
	private int x, y, altura, largura;

	public Imagem (String caminhoDaImagem) {
		
		caminhoDaImagem = Principal.getPathAtual() + "\\" + caminhoDaImagem;			

		Icon imagem = new ImageIcon(caminhoDaImagem);
		setIcon(imagem);
		altura = imagem.getIconHeight();
		largura = imagem.getIconWidth();
	}
	
	/*
	 define a posição da imagem na tela
	 
	 @param x	define a posição na tela na coordenada X
	 @param y	define a posição na tela na coordenada Y
	 */
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
