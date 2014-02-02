package Terreno;

/*
objeto que será composto por elementos do terreno, formando o terreno (também conhecido como mapa)
por onde o usuário jogará

@param level	Existem vários terrenos cadastrados, estes estão vinculados a LEVELS. O usuário sempre começa
				no level 1, assim que vencer este level, começará a jogar a level 2 (que representa outro terreno)
				até chegar no último level ("virar" o jogo).
				Este parametro define qual deve ser o terreno a ser construido para o usuário jogar,
				de acordo com o level que este estiver
			

@author	Alex Costa
@version	0.37.0
@since		0.0
*/

public class Terreno {

//define a largura e a altura do terreno
private int tamanho = 16;
private ElementoDoTerreno[][] elementos = null;

public ElementoDoTerreno getElemento (int x, int y) {
	return elementos[x][y];
}

public int getTamanho() {
	return tamanho;
}

/*
descobre em que posição do terreno o personagem está no respectivo momento

@return posição X e Y
*/
public int[] getPosicaoPersonagem () {
	for(int cont = 0; cont < tamanho; cont++) {
		for(int cont2 = 0; cont2 < tamanho; cont2++) {
			if(elementos[cont][cont2] instanceof Personagem) {
				return new int[]{cont2, cont};
			}
		}
	}

	return new int[]{-1, -1};
}

/*
 método utilizado apenas por esta classe para salvar o terreno no objeto
 */
private void setTerreno (ElementoDoTerreno[][] terreno) {
	
	tamanho = terreno.length + 2;
	
	ElementoDoTerreno[][] terrenoFinal = new ElementoDoTerreno[terreno.length + 2][terreno.length + 2];
	
	for(int cont = 0; cont < terreno.length; cont++) {
		for(int cont2 = 0; cont2 < terreno.length; cont2++) {
			terrenoFinal[cont + 1][cont2 + 1] = terreno[cont][cont2];
		}
	}
	
	for(int cont = 0; cont < tamanho; cont++) {
		//lilha e coluna vs coluna e linha
		//cima e baixo
		terrenoFinal[0][cont] = new Portal(199990 + cont);
		terrenoFinal[tamanho - 1][cont] = new Portal(199990 + cont);
		
		//esquerda e direita
		terrenoFinal[cont][0] = new Portal(599990 + cont);
		terrenoFinal[cont][tamanho - 1] = new Portal(599990 + cont);
	}
	
	elementos = terrenoFinal;

}

/*
 de acordo com o level passado ao contrutor do objeto, define a disposição dos objetos dentro do terreno
 */
public Terreno (int level) {
	
	switch (level) {

		case 1:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	new Personagem(),	new Parede(),	null,	null,	null,	null,	null,	new Vitoria(),	null,	null,	null	},
					{	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);																
	
			break;
		case 2:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Personagem(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Portal(1),	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Portal(1),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Vitoria(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);																

			
			break;
		case 3:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null	},
					{	null,	new Parede(),	new Personagem(),	null,	null,	null,	null,	null,	null,	null,	new Bloqueador(3),	null,	new Parede(),	null	},
					{	null,	new Parede(),	new Parede(),	null,	new Parede(),	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	new Parede(),	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	new Parede(),	null,	null,	null,	new Vitoria(),	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	new Bloqueador(2),	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	new Bloqueador(4),	null,	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	new Bloqueador(1),	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);																

			break;
		case 4:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null	},
					{	null,	new Parede(),	new Personagem(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	new Vitoria(),	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);
			break;
		case 5:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Portal(1),	null	},
					{	null,	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null	},
					{	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	new Parede(),	new Personagem(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	new Vitoria(),	new Parede(),	new Parede(),	new Parede(),	null,	null,	new Parede(),	new Parede(),	null,	null,	null,	null	},
					{	null,	new Portal(1),	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	new Parede(),	new Parede(),	null,	null,	new Parede(),	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);																
			break;
		case 6:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Personagem(),	null,	null,	null,	null,	null,	new Bloqueador(3),	null,	null,	null,	new Parede(),	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	new Bloqueador(3),	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Bloqueador(4),	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	new Bloqueador(4),	null,	null,	null,	null,	null,	new Vitoria(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}
				);
			break;
		case 7:
			setTerreno( new ElementoDoTerreno[][] {																
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	new Vitoria(),	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	new Parede(),	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede()	},
					{	null,	null,	null,	new Portal(1),	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null,	null	},
					{	new Parede(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede()	},
					{	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null,	null,	null	},
					{	null,	null,	new Personagem(),	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
					{	null,	null,	new Parede(),	null,	null,	null,	null,	new Parede(),	null,	null,	null,	null,	null,	null	},
					{	null,	new Portal(1),	null,	null,	new Bloqueador(3),	null,	null,	null,	null,	null,	null,	null,	new Parede(),	null	},
					{	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null,	null	},
				}																
				);																
			break;
			
		default:
			
			elementos = new ElementoDoTerreno[tamanho ][tamanho ];
			for(int cont = 0; cont < tamanho ; cont++) {
				for(int cont2 = 0; cont2 < tamanho ; cont2++) {
					elementos[cont][cont2] = null;
				}
			}

			if (level > 0){
				tamanho = 999999;
			}
	}
}
}
