package Geral;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Properties;

import javax.swing.*;

import Logica.*;
import Movimento.*;
import Terreno.*;

public class Principal extends JFrame implements KeyListener {

	//maximo que o usuário pode perder até perder a partida
	private static final int PONTUACAO_MAX = 200;
	//distancia em pixels entre os elementos da janela (objetos do jogo)
	public static final int DISTANCIA_ENTRE_OS_ELEMENTOS = 46;
	//variavel que guarda se no memento de verifcacao da mesma está sendo movimentado o personagem ou ele está parado
	public static boolean movert = false; 
	
	//X e Y em pixels da posição do personagem na tela
	private int cordXPixels, cordYPixels;
	//X e Y em array de elementos (terreno) da posição do personagem na tela
	private int cordXArray, cordYArray; 
	
	/* dados do usuario da partida */
	private String usuario = null;
	private int level = -1;
	private int pontos = PONTUACAO_MAX;
	
	/* objetos da tela */
	private Imagem personagem;
	private Terreno terreno;
	private static JPanel painel;
	private JMenuBar menuBar;
	private JMenu menuUsuario;
	private Font fonte;
	private JLabel pontuacao;
	
	/* controle de acessibilidade */
	private boolean deficienteVisual = false;
	private boolean daltonico = false;
	
	public Principal () {
		
			/* Questoes de acessibilidade */
			if (JOptionPane.showConfirmDialog(null, "Você possui algum tipo de deficiência visual parcial?", "Mensagem", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				deficienteVisual = true;
			}
			if (JOptionPane.showConfirmDialog(null, "Você é daltônico?", "Mensagem", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				daltonico = true;
			}
		
			/* carregando a fonte do placar (não presente no sistema) na memória */
			try {
				FileInputStream in = new FileInputStream (new File ( getPathAtual() + "\\" + "arquivos\\font.ttf" ));
				Font dynamicFont =	Font.createFont (Font.TRUETYPE_FONT, in); 
				fonte = dynamicFont.deriveFont(40f);
	        }
	        catch (Exception ert) {
	        	System.out.println("Erro ao carregar a fonte. " + ert.toString());
	        }
	        
			/* Definindo o titulo, as dimensoes e o posicionamento da janela */
			setTitle("Projeto - Angry Birds");
	        this.setSize((new Terreno(-1).getTamanho() -2 )* DISTANCIA_ENTRE_OS_ELEMENTOS + 20, (new Terreno(-1).getTamanho() -2) * DISTANCIA_ENTRE_OS_ELEMENTOS + 20 + 20);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        menuBar = new JMenuBar();
	        this.setJMenuBar(menuBar);
	        /* populando o menu superior com opcoes */
	        mostrarMenu();
	        
	        painel = new JPanel();
			mostrarNovoJogo();
	        
	        this.add(painel);
	        this.setVisible(true);
	        
	        this.addKeyListener(this);
	}
	
	/*
	 método que popula o menu superior com opcoes
	 */
	private void mostrarMenu () {
		
        menuBar.removeAll();
        menuBar.revalidate();
        
        if(deficienteVisual) {
	        Font f = new Font("arial", Font.BOLD, 20);
	        UIManager.put("Menu.font", f);
	        UIManager.put("MenuBar.font", f);
	        UIManager.put("MenuItem.font", f);
        }
        
        menuUsuario = new JMenu("Jogo");
        menuBar.add(menuUsuario);
        
        JMenuItem menuReiniciarPartida = new JMenuItem("Reiniciar partida atual");
        menuUsuario.add(menuReiniciarPartida);
        menuReiniciarPartida.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {  
            	if(movert == false) {
            		pontos = PONTUACAO_MAX;
            		mostrarNovoJogo();
            	}
            }  
        });
        
        JMenuItem menuCriarUsuario = new JMenuItem("Criar usuário");
        menuUsuario.add(menuCriarUsuario);
        menuCriarUsuario.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {  
            	setUsuario();
            }  
        });
        
        JMenuItem menuRanking = new JMenuItem("Ranking");
        menuUsuario.add(menuRanking);
        menuRanking.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {  
            	mostrarRanking();
            }  
        });
        
        menuUsuario.addSeparator();
        
        JMenu submenuSelecionarUsuario = new JMenu("Selecionar usuário");
        menuUsuario.add(submenuSelecionarUsuario);
        
        String[] usuarios = getUsuarios();
        
        for (int cont = 0; cont < usuarios.length; cont++) {
        	JMenuItem usuario = new JMenuItem(usuarios[cont]);
        	submenuSelecionarUsuario.add(usuario);
        	
        	usuario.addActionListener(new ActionListener() {   
                public void actionPerformed(ActionEvent e) {
                	selecionarUsuario(e.getActionCommand());
                }  
            });
        }
        
        menuBar.repaint();
	}
	
	/*
	 método que coloca o jogo na tela. Toda vez que o jogador iniciar ou reiniciar uma partida
	 este método será o responsável por "pintar" os elementos na tela
	 */
	private void mostrarNovoJogo() {
		
		terreno = new Terreno(level);
		
		if(terreno.getTamanho() == 999999) {
			if(usuario != null)
				gerarMensagem("Parabéns " + usuario + ", você virou o jogo");
			usuario = null;
			terreno = new Terreno(-1);
		}
		
		painel.removeAll();

        painel.setLayout(null);
        painel.setBackground(Color.black);
        
        int numeroDeComponentesAdicionados = 0;
        
        /* percorre a matriz X e Y do terreno e vai adicionado seus elementos na tela */
		for(int cont = 1; cont < terreno.getTamanho() -1; cont++) {
			for(int cont2 = 1; cont2 < terreno.getTamanho() -1; cont2++) {
				
				ElementoDoTerreno elemento = terreno.getElemento(cont2, cont);
				Imagem imagem = null;
				
				if(elemento instanceof Parede) {
					imagem = new Imagem("arquivos\\parede.png");
				}
				else if(elemento instanceof Vitoria) {
					imagem = new Imagem("arquivos\\fim.png");
				}
				else if(elemento instanceof Bloqueador) {
					imagem = new Imagem("arquivos\\bloqueador" + ((Bloqueador) elemento).getDirecaoBloqueio() + ".png");
				}
				else if(elemento instanceof Portal) {
					imagem = new Imagem("arquivos\\portal.png");
				}
				
				if(imagem != null) {
					imagem.setCoordenada((cont - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS, (cont2 - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS);
					painel.add(imagem);
					numeroDeComponentesAdicionados++;
				}
			}
		}
		
		Imagem background = new Imagem("arquivos\\fundo.jpg");
		if(daltonico) {
        	background = new Imagem("arquivos\\fundo_daltonicos.jpg");
        }
        
        background.setCoordenada(0, 0);
       
        int[] posicaoPersonagem = terreno.getPosicaoPersonagem();
        
        cordXPixels = (posicaoPersonagem[0] - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS;
        cordYPixels = (posicaoPersonagem[1] - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS;
        
        cordXArray = posicaoPersonagem[0];
        cordYArray = posicaoPersonagem[1];
        
        personagem = new Imagem("arquivos\\personagem.png");
        if(daltonico) {
        	personagem = new Imagem("arquivos\\personagem_pb.png");
        }
        personagem.setCoordenada(cordXPixels, cordYPixels);
        
        painel.add(background);
        painel.add(personagem);
        
        numeroDeComponentesAdicionados += 2;

        painel.setComponentZOrder(background, numeroDeComponentesAdicionados -1);
        painel.setComponentZOrder(personagem, 0);
        
        /* Se houver um usuario, mostra o placar no topo da pagina */
		if(usuario != null) {
			pontuacao = new JLabel();
			pontuacao.setFont(fonte);
	        if(daltonico) {
	        	pontuacao.setForeground(Color.WHITE);
	        } else {
	        	pontuacao.setForeground(Color.BLACK);
	        }
	        pontuacao.setHorizontalAlignment(SwingConstants.CENTER);
			pontuacao.setBounds(0 ,0  ,this.getSize().width, 45);
		    painel.add(pontuacao);
		    painel.setComponentZOrder(pontuacao, 1);
		    atualizarPlacar();
		}
        
        painel.repaint();
        painel.setVisible(true);
	}
	
	public void atualizarPlacar() {
		atualizarPlacar(pontos);
	}
	
	public void atualizarPlacar(int pontos) {
		pontuacao.setText(usuario + " - Level: " + level + " - " + pontos + " pts");
	}
	
	private String lerDoTeclado (String texto) {
		return JOptionPane.showInputDialog(texto);
	}
	
	private void gerarMensagem (String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	/*
	 Evento que ocorrerá quando o usuário pressionar uma tecla
	 */
	
	public void keyPressed(KeyEvent ke) {
		
		if(usuario != null) {
			if(movert == false) {
				
				//define que o objeto está sendo movido
				movert = true;

				int direcao = -1;				
				int movX = 0;
				int movY = 0;
				
				//de acordo com a tecla, define a direção do movimento
				switch (ke.getKeyCode()) {
			        case KeyEvent.VK_RIGHT:
			        	direcao = 1;
			        	movX++;
			        	break;
			        case KeyEvent.VK_LEFT:
			        	direcao = 3;
			        	movX--;
			        	break;
			        case KeyEvent.VK_DOWN:
			        	direcao = 2;
			        	movY++;
			        	break;
			        case KeyEvent.VK_UP:
			        	direcao = 4;
			        	movY--;
			        	break;
				}
				
				if(direcao > 0 && pontos > 0) {
						
					IA ia = new IA();	
					ArrayList<Movimento> conjuntoDeMovimentos = new ArrayList<Movimento>();

					Movimento mov = null;
					    
				    do {
				    	pontos--;
				    	
				    	//quando os pontos chegarem a zero, o usuario perde
				    	if(pontos == 0) {
							conjuntoDeMovimentos.add(new Perder());
				    		break;
						}
				    	
				    	mov = ia.gerarPercurso(terreno, cordXArray, cordYArray, direcao);
				    	
				    	//atualiza as coordenadas
				    	if(mov instanceof MoverParaXY) {
					        
				    		cordXArray = ((MoverParaXY) mov).getMovimentoX();
					        cordYArray = ((MoverParaXY) mov).getMovimentoY();
					    }
				    	if(mov instanceof Andar || mov instanceof MoverParaXY) {
						    
				    		cordXArray += movX;
						    cordYArray += movY;
						    cordXPixels = (cordXArray - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS;
					        cordYPixels = (cordYArray - 1) * DISTANCIA_ENTRE_OS_ELEMENTOS;
					        
					        conjuntoDeMovimentos.add(mov);
						}
				    	else if (mov instanceof Fim) {
					    	
					    	conjuntoDeMovimentos.add(mov);
					    	mov = null;
					    }
				    }
				    while(mov != null);
				    //efetivamente move o personagem para as coordenadas definidas
				    ThreadMoverPersonagem thread = new ThreadMoverPersonagem(personagem, conjuntoDeMovimentos, this);
			        thread.start();
				}
			}
		}
		else {
			gerarMensagem("Selecione um usuário para começar a jogar");
		}
		
	}

	/*
	 devido ao implements, e necessario definir aos seguintes metodos, mas nao serao usados
	 */
	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	/*
	 quando o usuário completar uma fase, este método será chamado
	 */
	public void incrementarLevel() {

		level++;
    	salvarJogoUsuario();
    	mostrarNovoJogo();
    	pontos = PONTUACAO_MAX;
    }
	
	private void mostrarRanking () {

		String[] usuariosFormatados = getUsuarios();
		String [][] ranking = new String[usuariosFormatados.length][3];
		
		//lendo os pontos do arquivo
		for(int cont = 0; cont < usuariosFormatados.length; cont++) {
			
			String usuarioAtual = usuariosFormatados[cont];
			ranking[cont][0] = usuarioAtual;
			ranking[cont][1] = getProperties("arquivos/config.properties", usuarioAtual + "_pts");
			ranking[cont][2] = getProperties("arquivos/config.properties", usuarioAtual + "_level");
		}
		
		/*
		 ordenando do maior level para o menor, sendo que o criterio de desempate é o numero de pontos,
		 quem tiver o maior numero de pontos concluiu com menos movimentos as fases
		 */
		Arrays.sort(ranking, new Comparator<String[]>() {
            public int compare( String[] tupla1,  String[] tupla2) {
                 Integer level1 = Integer.parseInt(tupla1[2]);
                 Integer level2 = Integer.parseInt(tupla2[2]);
                 Integer pts1 = Integer.parseInt(tupla1[1]);
                 Integer pts2 = Integer.parseInt(tupla2[1]);
                 
                 if (level1 > level2) {
                     return -1;
                 } else if (level2 > level1) {
                     return 1;
                 } else {
                	 if (pts1 > pts2) {
	                     return -1;
	                 } else if (pts2 > pts1) {
	                     return 1;
	                 } else {
	                     return 0;
	                 }
                 }
            }
        });
		
		//definindo a mensagem a ser mostrada
		String rankingEmTexto = "";
		int cont = 1;
        for ( String[] s : ranking) {
            rankingEmTexto += "Posição " + cont + ": " + s[0] + "\npts: " + s[1] + " - level: " + s[2] + "\n\n";
            cont++;
        }        
		gerarMensagem(rankingEmTexto);		
	}
	
	public int getPontos () {
		return pontos;
	}
	
	public void perder () {
		gerarMensagem("Você perdeu!");
		pontos = PONTUACAO_MAX;
		mostrarNovoJogo();
	}
	
	/* 
	 Manipulação de arquivos
	 */
	private String getProperties(String arquivoProperties, String campo)  {

		String resultado = null;
		
		try {
			
			arquivoProperties = Principal.getPathAtual() + "\\" + arquivoProperties;
			
			File arq = new File(arquivoProperties);
			Properties props = new Properties();
			FileInputStream fis = null;

		    fis = new FileInputStream(arq);
		    props.load(fis);    
		    fis.close();
		    
		    resultado = props.getProperty(campo);
		}
		catch (Exception ert) {
			System.out.println(ert.toString());
		}
		    
		return resultado;
	}
		
	private void setProperties(String arquivoProperties, String campo, String conteudo)  {

		try {

			arquivoProperties = Principal.getPathAtual() + "\\" + arquivoProperties;

			File arq = new File(arquivoProperties);
			Properties props = new Properties();
			FileInputStream fis = null;
  
		    fis = new FileInputStream(arq);
		    props.load(fis);    
		    fis.close();

		    props.setProperty(campo, conteudo);
    
            FileOutputStream fos = new FileOutputStream(arquivoProperties);
            //grava os dados no arquivo
            props.store(fos, "");
            //fecha o arquivo
            fos.close();
		}
		catch (Exception ert) {
			System.out.println(ert.toString());
		}
	}
	
	/* MANIPULACAO DE USUARIOS */
	
	private String[] getUsuarios () {
		return getProperties("arquivos/config.properties", "usuarios").split(";");
	}
	
	private void setUsuario () {
		
		String nome = lerDoTeclado("Informe o nome do novo usuário:");
		
		if(nome != null) {

				String usuarios = getProperties("arquivos/config.properties", "usuarios");
				if(usuarios.length() > 0 && usuarios.substring(0, 1).equals(";")) {
					usuarios = usuarios.substring(1, usuarios.length());
				}
				if(usuarios.length() > 0 && usuarios.substring(usuarios.length() -1, usuarios.length()).equals(";")) {
					usuarios = usuarios.substring(0, usuarios.length() -1);
				}
				usuarios = usuarios.replaceAll(";;", ";");
				
				String[] usuariosFormatados = usuarios.split(";");
				
				boolean usuarioValido = true;
				for(int cont = 0; cont < usuariosFormatados.length; cont++) {
					if(nome.equals(usuariosFormatados[cont])) {
						usuarioValido = false;
						break;
					}
				}
				
				if(usuarioValido) {
					
					setProperties("arquivos/config.properties", "usuarios", usuarios + (usuarios.length() > 1 ? ";" : "") + nome);
					setProperties("arquivos/config.properties", nome + "_level", "1");
					setProperties("arquivos/config.properties", nome + "_pts", "0");
					mostrarMenu();
				}
				else {
					
					gerarMensagem("O usuário escolhido já existe, escolha outro nome");
					setUsuario();
				}
		}
	}

	private void salvarJogoUsuario () {
		
		setProperties("arquivos/config.properties", usuario + "_level", "" + level);
		int pontuacaoAnterior = Integer.parseInt(getProperties("arquivos/config.properties", usuario + "_pts"));
		setProperties("arquivos/config.properties", usuario + "_pts", "" + (pontos + pontuacaoAnterior));
	}
	
	private void selecionarUsuario (String usuario) {
		
		this.usuario = usuario;
		this.level = Integer.parseInt(getProperties("arquivos/config.properties", usuario + "_level"));
		this.pontos = PONTUACAO_MAX;
		mostrarNovoJogo();
	}
	
	public static String getPathAtual () {
		File f = new File(System.getProperty("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		return dir.toString();
	}
	
	public static void main (String[] args) {
		new Principal();
	}
}
