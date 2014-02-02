package Terreno;

/*
Elemento que teletransportará o personagem para outra posição do terreno

@param id	todo portal possui um par, ele e mais outro portal para onde o personagem será
			teletransportado caso entre neste portal. Ambos precisam ter a mesma ID, para
			que seja possível identificar quem é par de quem

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
