package Geral;

import java.util.ArrayList;
import Movimento.*;

/*
Para permitir maior fuidez do personagem ao se movimentar na tela e conseguir tratar possíveis comandos
que o usuário possa executar durante um movimento, defini esta Thread, para tornar independente o movimento
do personagem

@param personagem				Objeto que guarda a imagem do personagem que percorrerá a tela
@param conjuntoDeMovimentos		Array contendo um conjunto de movimentos já previstos, que deve apenas ser executados
								pela Thread
@param principal				Instancia da janela principal, para permitir que o personagem tenha interação
								com o restante dos movimentos (contidos em Peincipal)
								
@author	Alex Costa
@version	0.24.0
@since		0.0
*/

class ThreadMoverPersonagem extends Thread {

    private Imagem personagem;
    private ArrayList<Movimento> conjuntoDeMovimentos;
    private Principal principal;
    
    public ThreadMoverPersonagem (Imagem personagem, ArrayList<Movimento> conjuntoDeMovimentos, Principal principal) {
    	this.personagem = personagem;
    	this.conjuntoDeMovimentos = conjuntoDeMovimentos;
    	this.principal = principal;
    }
    
    public void run() {

		    	for(int cont = 0; cont < conjuntoDeMovimentos.size(); cont++) {
		    		
		    		Movimento mov = conjuntoDeMovimentos.get(cont);
		    		
		    		/*
		    		 caso seja um movimento, obtem todo o movimento a ser realizado
		    		 */
		    		Andar andar = null;
		    		if(mov instanceof MoverParaXY) {
		    			personagem.setCoordenada( (((MoverParaXY) mov).getMovimentoX() -1) * Principal.DISTANCIA_ENTRE_OS_ELEMENTOS, (((MoverParaXY) mov).getMovimentoY() -1) * Principal.DISTANCIA_ENTRE_OS_ELEMENTOS);
		    			andar = ((MoverParaXY) mov).getAndar();
				    }
				    if(mov instanceof Andar || andar != null) {
				    	
				    	if(mov instanceof Andar) 
				    		andar = (Andar) mov;
				    	
				    	/*
				    	 faz o personagem percorrer o caminho definido, perando 5m por movimento, para
				    	 deixar mais claro e suave o efeito de "andar" pela tela
				    	 */
				    	for(int cont2 = 0; cont2 < Principal.DISTANCIA_ENTRE_OS_ELEMENTOS; cont2++) {
				    		
				    		try {
				    			this.sleep(5);
				    		}
				    		catch (Exception eer) {
				    			System.out.println("Erro no sleep da thread. " + eer.toString());
				    		}
				    		personagem.setCoordenada(personagem.getX() + andar.getMovimentoX(), personagem.getY() + andar.getMovimentoY());
				    	}
				    }
				    
				    principal.atualizarPlacar();
				    if (mov instanceof Fim) {
				    	principal.atualizarPlacar();
				    	principal.incrementarLevel();
				    }
				    else if(mov instanceof Perder) {
				    	principal.atualizarPlacar();
				    	principal.perder();
					}
				    else {
				    	principal.atualizarPlacar(conjuntoDeMovimentos.size() - 1 + principal.getPontos() - cont);
				    }
		    	}
		    	
		    	principal.atualizarPlacar();
		    	Principal.movert = false;
    }
}

