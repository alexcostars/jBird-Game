package Terreno;

/*
Elemento que teletransportar� o personagem para outra posi��o do terreno

@param id	todo portal possui um par, ele e mais outro portal para onde o personagem ser�
			teletransportado caso entre neste portal. Ambos precisam ter a mesma ID, para
			que seja poss�vel identificar quem � par de quem

@author	Alex Costa
@version	0.5.0
@since		0.0
*/

public class Portal implements ElementoDoTerreno {
	
	private int id;
	
	public Portal (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

}
