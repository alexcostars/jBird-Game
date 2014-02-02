package Logica;
import Movimento.*;
import Terreno.*;

/*
 classe responsável por toda a inteligência artificial do jogo 
 
 @author	Alex Costa
 @version	0.98.3
 @since		0.0
 */

public class IA {
	/*
	 Quando o personagem entrar em um determinado portal, esse método retornará a posição do outro objeto portal
	 (para onde o personagem deverá ser teletransportado)
	 
	 @param 	terreno			terreno que o usuário está jogando
	 @param 	posicaoAtualX	posição X do portal onde o personagem está entrando
	 @param 	posicaoAtualY	posição Y do portal onde o personagem está entrando
	 @return	posição X e Y para onde o personagem deve ser teletransportado
	 */
	public int[] getOutraPontaDoPortal (Terreno terreno, int posicaoAtualX, int posicaoAtualY) {
		
		ElementoDoTerreno elemento = terreno.getElemento(posicaoAtualY, posicaoAtualX);
		//Portal implementa terreno, logo é possível fazer o cast
		int id = ((Portal) elemento).getId();
		
		for(int cont = 0; cont < terreno.getTamanho(); cont++) {
			for(int cont2 = 0; cont2 < terreno.getTamanho(); cont2++) {
				
				ElementoDoTerreno elementoAtual = terreno.getElemento(cont, cont2);
				
				/*
				 se for um Portal
				 se a ID dos dois for a mesma
				 se não for o mesmo
				 
				 encontrou o portal e rorna a informação
				 */
				if(elementoAtual instanceof Portal && ((Portal) elementoAtual).getId() == id && ( cont2 != posicaoAtualX || cont != posicaoAtualY )) {
				//	System.out.println("Encontrou a outra ponta: " + cont2 + " " + cont);
					return new int[]{cont2, cont};
				}
				
			}
		}
		
		return null;
		
	}
	
	/*
	 De uma posição X e Y, após o usuário teclar alguma seta de direção (para mover o personagem pela tela)
	 este método é o responsável pode definir qual será o percurso percorrido (até o momento final da ação de andar)
	 por parte personagem
	 
	 @param 	terreno			terreno que o usuário está jogando
	 @param 	posicaoAtualX	posição X do terreno onde o personagem está inicialmente
	 @param 	posicaoAtualY	posição Y do terreno onde o personagem está inicialmente
	 @param 	direcao			Direção para onde o usuário mandou o objeto se mover. 1 = para direita, 2 = para baixo, 3 = para esquerda, 4 = para cima
	 @return	um movimento
	*/
	public Movimento gerarPercurso (Terreno terreno, int posicaoAtualX, int posicaoAtualY, int direcao) {
		
		int[] movX = new int[]{1, 0, -1, 0};
		int[] movY = new int[]{0, 1, 0, -1};
		
		ElementoDoTerreno elementoAtual = terreno.getElemento(posicaoAtualY, posicaoAtualX);
		
		if(elementoAtual instanceof Portal) {
			int[] portalApontaPara = getOutraPontaDoPortal(terreno, posicaoAtualX, posicaoAtualY);

			return new MoverParaXY(portalApontaPara[0], portalApontaPara[1], new Andar(movX[direcao - 1], movY[direcao - 1]));
		}
		else if (elementoAtual instanceof Vitoria) {
			return new Fim();
		}

		ElementoDoTerreno elementoAFrente = terreno.getElemento(posicaoAtualY + (movY[direcao - 1]), posicaoAtualX + (movX[direcao - 1]));

		if(elementoAFrente instanceof Parede) {
			return null;
		}
		else if(elementoAFrente instanceof Bloqueador) {
			Bloqueador bloqueador = (Bloqueador) elementoAFrente;

			if(bloqueador.getDirecaoBloqueio() != direcao) {
				return new Andar(movX[direcao - 1], movY[direcao - 1]);
			}
			else {
				return null;
			}
		}
		
		return new Andar(movX[direcao - 1], movY[direcao - 1]);
	}
	
}
