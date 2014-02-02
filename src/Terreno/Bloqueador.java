package Terreno;

/*
Objeto que permite que o personagem passe por cima caso venha de qualquer lado (acima, direita, abaixo, esquerda)
exceto se vier da direção configurada em direcaoDeBloqueio. Neste caso o objeto atuará como uma parede
Define um objeto que libera a passagem por cima dele quando 

@param direcaoBloqueador	define a diração que atuará como parede, ou seja, não permitirá que o
							usuário continue andando
							Caso o usuário não possa descer: passe 2
							Caso o usuário não possa ir para a direita: passe 1
							Caso o usuário não possa ir para a esquerda: passe 3
							Caso o usuário não possa subir: passe 4

@author	Alex Costa
@version	0.7.0
@since		0.0
*/

public class Bloqueador implements ElementoDoTerreno {
	
	private int direcaoDoBloqueio;

	public Bloqueador(int direcaoBloqueador) {
		this.direcaoDoBloqueio = direcaoBloqueador; 
	}
	
	public int getDirecaoBloqueio() {
		return direcaoDoBloqueio;
	}
}
