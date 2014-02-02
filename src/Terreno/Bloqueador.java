package Terreno;

/*
Objeto que permite que o personagem passe por cima caso venha de qualquer lado (acima, direita, abaixo, esquerda)
exceto se vier da dire��o configurada em direcaoDeBloqueio. Neste caso o objeto atuar� como uma parede
Define um objeto que libera a passagem por cima dele quando 

@param direcaoBloqueador	define a dira��o que atuar� como parede, ou seja, n�o permitir� que o
							usu�rio continue andando
							Caso o usu�rio n�o possa descer: passe 2
							Caso o usu�rio n�o possa ir para a direita: passe 1
							Caso o usu�rio n�o possa ir para a esquerda: passe 3
							Caso o usu�rio n�o possa subir: passe 4

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
