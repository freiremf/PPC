/*
 * Universidade Federal do Pará - UFPA
 * Instituto de Ciências Exatas e Naturais - ICEN
 * Faculdade de Computação - FC
 * Disciplina: Computação Evolucionária
 * Professor: Claudomiro Sales
 * Aluno: Matheus Ferreira Freire
 * Data: 03/12/2014
 */

import java.util.ArrayList;
import java.util.Arrays;

public class AGPPC {
	private ArrayList<Cavalo> popCavalos = new ArrayList<>();
	private Tabuleiro tabuleiroAG = new Tabuleiro();
	//private Casa casaAG = new Casa();
	private int tamPop = 200;
	private double probMutacao = 0.5; //10%
	private double probRecombinacao = 0.9; //90%
	private boolean verificaRecombinacao = false;
	private int qtdFilhos = (int)(tamPop*0.4);
	//construtor inicia população de cavalos;
	AGPPC()
	{
		for (int i = 0; i < getTamPop(); i++)
			getPopCavalos().add(new Cavalo());		
	}
	//Realiza o cálculo de Fitness de uma população de Cavalos
	public void calculaFitness()
	{
		//int contador = popCavalos.get(1).getTamMovimentos()-1;
		boolean verificador=false;
		boolean verificaSeq=false;
		//boolean verificaNumSeq=false;
		int contSeq;
		int tamSeq;
		int numSeq;
		
		for (int h = 0; h < popCavalos.size(); h++) 
		{
			contSeq=0;
			tamSeq=1;
			numSeq=0;
			for (int i = 0; i < popCavalos.get(i).getTamMovimentos()-1; i++) // percorre até o penultimo movimento 
			{	
				verificaSeq=false;
				verificador = false;
				int movAtual = popCavalos.get(h).getMovimentos().get(i); //mov recebe o movimento atual
				int movAdj = popCavalos.get(h).getMovimentos().get(i+1); //mov recebe o movimento adjacente
	
				for (int j = 0; j < getTabuleiroAG().getTabuleiro().length; j++)
				{					
					for (int k = 0; k < getTabuleiroAG().getTabuleiro()[j].length; k++)
					{
						if(getTabuleiroAG().getTabuleiro()[j][k].getNum() == movAtual)
						{
							if(getTabuleiroAG().getTabuleiro()[j][k].getVizinhosCavalo().contains(movAdj))
							{
								tamSeq++;
								verificaSeq = true;
								//verificaNumSeq=true;
							}
							verificador = true;
							break;
						}	
					}
					if(verificador)
						break;
					
				}
				if(verificaSeq==false)
				{
					if(tamSeq>1)
					{
						contSeq+=tamSeq;
						numSeq++;
						tamSeq=1;
					}
				}	
			} //fim_cromossomo
			if(tamSeq>1)
			{
				contSeq+=tamSeq;
				numSeq++;
				tamSeq=1;
			}
			popCavalos.get(h).setFitness(contSeq - numSeq);
		}
	}
	//Método de recombinação por arestas
	public Cavalo edgeRecombination (Cavalo pai1, Cavalo pai2)
	{
		int posicaoAtual;
		int posicaoCorrespondente;
		int elemento;
		Cavalo filho = new Cavalo();
		ArrayList<Adjacencia> movsAdjPai1 = new ArrayList<>();
		ArrayList<Adjacencia> movsAdjPai2 = new ArrayList<>();
		ArrayList<Adjacencia> movsAdjPai1Pai2 = new ArrayList<>();
		
		if(Cavalo.obj.nextDouble() < getProbRecombinacao())
		{
			for (int i = 0; i < pai1.getTamMovimentos(); i++)
				movsAdjPai1Pai2.add(new Adjacencia());
				
			//Calcula vizinhos do Pai1
			for (int i = 0; i < pai1.getTamMovimentos(); i++)
			{
				movsAdjPai1.add(new Adjacencia());
				//System.out.println("Tamanho = " + pai1.getMovimentos().size());
				//System.out.println("Elemento = " + pai1.getMovimentos().get(pai1.getMovimentos().size()-1));
				try{
					movsAdjPai1.get(i).getAdjacentes().add(pai1.getMovimentos().get(i-1));
					movsAdjPai1.get(i).setElemento(pai1.getMovimentos().get(i));
				}
				catch(Exception e){
					movsAdjPai1.get(i).getAdjacentes().add(pai1.getMovimentos().get(pai1.getMovimentos().size()-1));
					movsAdjPai1.get(i).setElemento(pai1.getMovimentos().get(i));
				}
				try{
					movsAdjPai1.get(i).getAdjacentes().add(pai1.getMovimentos().get(i+1));
				}
				catch(Exception e){
					movsAdjPai1.get(i).getAdjacentes().add(pai1.getMovimentos().get(0));
				}
			}	
			//Calcula vizinhos do Pai2
			for (int i = 0; i < pai2.getTamMovimentos(); i++)
			{
				movsAdjPai2.add(new Adjacencia());
				
				try{
					movsAdjPai2.get(i).getAdjacentes().add(pai2.getMovimentos().get(i-1));
					movsAdjPai1.get(i).setElemento(pai2.getMovimentos().get(i));
				}
				catch(Exception e){
					movsAdjPai2.get(i).getAdjacentes().add(pai2.getMovimentos().get(pai2.getMovimentos().size()-1));
					movsAdjPai1.get(i).setElemento(pai2.getMovimentos().get(i));
				}
				try{
					movsAdjPai2.get(i).getAdjacentes().add(pai2.getMovimentos().get(i+1));
				}
				catch(Exception e){
					movsAdjPai2.get(i).getAdjacentes().add(pai2.getMovimentos().get(0));
				}
			}
			criaTabelaAdjacencia(pai2, movsAdjPai1, movsAdjPai2, movsAdjPai1Pai2);
			posicaoAtual = Cavalo.obj.nextInt(pai1.getTamMovimentos());
			elemento = pai1.getMovimentos().get(posicaoAtual);
			posicaoCorrespondente = buscaPosicaoCorrespondente(pai2, elemento);
			filho.getMovimentos().add(elemento);
			//Remove todas as referências ao elemento nas adjacencias
			removeReferencias(movsAdjPai1Pai2, elemento);
			
			for (int h = 0; h < movsAdjPai1Pai2.size()-1; h++) 
			{
				boolean control=false;
				for (int i = 0; i < movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().size(); i++)
				{
					for (int j = 0; j < movsAdjPai1Pai2.get(posicaoCorrespondente).getAdjacentes().size(); j++)
					{
						if(movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().get(i).
								equals(movsAdjPai1Pai2.get(posicaoCorrespondente).getAdjacentes().get(j)))
						{   
							elemento = movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().get(i);
							posicaoAtual = buscaPosicaoCorrespondente(pai1, elemento);
							filho.getMovimentos().add(elemento);
							removeReferencias(movsAdjPai1Pai2, elemento);
							control = true;
							break;												
						}
					}
					if(control)
						break;
				}
				if(!control)
				{
					int [] arrayAux = new int[movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().size()]; 
					
					for (int i = 0; i < movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().size(); i++)
						arrayAux[i] = movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().get(i);
					
					Arrays.sort(arrayAux);
					if(arrayAux.length>0)
						elemento = arrayAux[0];
					posicaoAtual = buscaPosicaoCorrespondente(pai1, elemento);
					filho.getMovimentos().add(elemento);
					removeReferencias(movsAdjPai1Pai2, elemento);
					control = true;
				}
				if(!control)
				{
					int aleatorio;
					
					aleatorio = Cavalo.obj.nextInt(movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().size());
					elemento = movsAdjPai1Pai2.get(posicaoAtual).getAdjacentes().get(aleatorio);
					posicaoAtual = buscaPosicaoCorrespondente(pai1, elemento);
					filho.getMovimentos().add(elemento);
					removeReferencias(movsAdjPai1Pai2, elemento);
				}
			}
			verificaRecombinacao = true;
		}
		return filho;
	}
	//Método auxiliar para a recombinação. Retira todas as referências ao elemento da tabela de adjacentes
	private void removeReferencias (ArrayList<Adjacencia> arrayList, int elem)
	{
		for (int i = 0; i < arrayList.size(); i++) 
			for (int j = 0; j < arrayList.get(i).getAdjacentes().size(); j++)
				if(arrayList.get(i).getAdjacentes().get(j).equals(elem))
				{
					arrayList.get(i).getAdjacentes().remove(arrayList.get(i).getAdjacentes().get(j));
					break;
				}
	}
	//Método auxiliar à Recombinação. Busca a posição de um movimento no ArrayList de um Pai2 correspondente a aum Pai1
	private int buscaPosicaoCorrespondente(Cavalo cavalo, int elemento)
	{
		int position=0;
		
		for (int i = 0; i < cavalo.getTamMovimentos(); i++)
		{
			if(cavalo.getMovimentos().get(i) == elemento)
			{
				position = i;
				break;
			}
		}
		return position;
	}
	//Método auxiliar à Recombinação. Cria uma tabela com adjacentes de cada movimento
	private void criaTabelaAdjacencia (Cavalo pai2, ArrayList<Adjacencia> adjPai1,
			ArrayList<Adjacencia> adjPai2, ArrayList<Adjacencia> adjTodos)
	{
		int pos;
		for (int i = 0; i < adjPai1.size(); i++)
		{
			adjTodos.get(i).setElemento(adjPai1.get(i).getElemento());
			adjTodos.get(i).getAdjacentes().add(adjPai1.get(i).getAdjacentes().get(0));
			adjTodos.get(i).getAdjacentes().add(adjPai1.get(i).getAdjacentes().get(1));
			
			pos = buscaPosicaoCorrespondente(pai2, adjPai1.get(i).getElemento());
			
			if(!adjTodos.get(i).getAdjacentes().contains(adjPai2.get(pos).getAdjacentes().get(0)))
				adjTodos.get(i).getAdjacentes().add(adjPai2.get(pos).getAdjacentes().get(0));
			
			if(!adjTodos.get(i).getAdjacentes().contains(adjPai2.get(pos).getAdjacentes().get(1)))
				adjTodos.get(i).getAdjacentes().add(adjPai2.get(pos).getAdjacentes().get(1));
		}
	}
	//Realiza a mutação baseada nos vizinhos
	public void mutacaoBaseadaVizinhos (Cavalo filho)
	{
		int aleatorio = Cavalo.obj.nextInt(filho.getTamMovimentos());
		int cont = aleatorio;
		boolean control;
		
		if(Cavalo.obj.nextDouble() < getProbMutacao())
		{
			while(cont>0)
			{
				control=false;
				for (int i = 0; i < getTabuleiroAG().getTabuleiro().length; i++)
				{
					for (int j = 0; j < getTabuleiroAG().getTabuleiro()[i].length; j++)
					{
						if(getTabuleiroAG().getTabuleiro()[i][j].getNum() == filho.getMovimentos().get(aleatorio))
						{
							try{
								if(getTabuleiroAG().getTabuleiro()[i][j].getVizinhosCavalo().
										contains(filho.getMovimentos().get(aleatorio-1)))
								{
									control=true;
									break;
								}
								else
								{
									for (int k = 0; k < filho.getTamMovimentos(); k++)
									{
										if(getTabuleiroAG().getTabuleiro()[i][j].getVizinhosCavalo().
												contains(filho.getMovimentos().get(k)))
										{
											int aux = filho.getMovimentos().get(aleatorio-1);
											filho.getMovimentos().set(aleatorio-1, filho.getMovimentos().get(k));
											filho.getMovimentos().set(k, aux);
											
											control=true;
											break;
										}
									}
									if (control)
										break;
								}
							}
							catch(Exception e)
							{
								if(getTabuleiroAG().getTabuleiro()[i][j].getVizinhosCavalo().
										contains(filho.getMovimentos().get(aleatorio+1)))
									{
										control=true;
										break;
									}
									else
									{
										for (int k = 0; k < filho.getTamMovimentos(); k++)
										{
											if(getTabuleiroAG().getTabuleiro()[i][j].getVizinhosCavalo().
													contains(filho.getMovimentos().get(k)))
											{
												int aux = filho.getMovimentos().get(aleatorio+1);
												filho.getMovimentos().set(aleatorio+1, filho.getMovimentos().get(k));
												filho.getMovimentos().set(k, aux);
												
												control=true;
												break;
											}
										}
										if(control)
											break;
									}
								}
							}
						}
					if(control)
						break;	
					}
				aleatorio = Cavalo.obj.nextInt(filho.getTamMovimentos());
				cont--;
			}
		}
	}
	//Seleciona dois cavalos pais por torneio
	private Cavalo[] torneio(ArrayList<Cavalo> populacaoCavalos)
	{
		Cavalo[] pais = new Cavalo[2];
        Cavalo[] populacaoAux = new Cavalo[15];
        int individuoAleatorio;
        //seleciona 10 indivíduos aleatoriamente na população original
        for (int i = 0; i < 15; i++)
        {
        	individuoAleatorio = Cavalo.obj.nextInt(getPopCavalos().size());
        	populacaoAux[i] = populacaoCavalos.get(individuoAleatorio);
		}
        //ordena a população auxiliar
        Arrays.sort(populacaoAux, Cavalo.IndFitness);
        //seleciona os 2 melhores da população auxiliar
        //pais[0] = populacaoAux[0];
        //pais[1] = populacaoAux[1];
        pais[0] = populacaoAux[populacaoAux.length-1];
        pais[1] = populacaoAux[populacaoAux.length-2];

        return pais;
    }
	//Realiza o Steady State
	public void steadyState()
	{
		Cavalo[] pais = new Cavalo[2];
		Cavalo filho = new Cavalo();
		int posicao = getPopCavalos().size()/3; //posição a ser introduzido o filho gerado
		
		for(int i = 0; i < qtdFilhos; i++)			
		{
			pais = torneio(getPopCavalos());
			
			filho = edgeRecombination(pais[0], pais[1]);
			
			if(verificaRecombinacao)
			{				
				mutacaoBaseadaVizinhos(filho);
				
				getPopCavalos().set(posicao, filho);
				
				posicao++;
				
				verificaRecombinacao=false;
			}
		}
		//elitismo
		getPopCavalos().set(0, getMelhorIndividuo());
	}
	//Imprime a população de cavalos
	public void printPopCavalos()
	{
		for (int i = 0; i < getPopCavalos().size(); i++)
		{
			System.out.print(i + "- ");
			for (int j = 0; j < getPopCavalos().get(i).getTamMovimentos(); j++)
				System.out.print(getPopCavalos().get(i).getMovimentos().get(j) + "|");
			System.out.println();
		}
	}
	//Calcula média de Fitness da População de Cavalos
	public double calculaMedia ()
	{
		double media=0;
		for (int i = 0; i < getPopCavalos().size(); i++)
			media += getPopCavalos().get(i).getFitness();
		
		media /= getPopCavalos().size();
		return media;
	} 
	//Retorna o cavalo com melhor Fitness da população de cavalos
	public Cavalo getMelhorIndividuo()
	{
		Cavalo[] popAux = new Cavalo[getPopCavalos().size()]; 
		//ArrayList<Cavalo> popAux = new ArrayList<>();
		for (int i = 0; i < popAux.length; i++)
			popAux[i] = getPopCavalos().get(i);			
		//popAux = getPopCavalos();
		Arrays.sort(popAux, Cavalo.IndFitness);
		//Collections.sort(popAux, Cavalo.IndFitness);
		return popAux[popAux.length-1];
		//return getPopCavalos().get(getPopCavalos().size()-1);
		//return popAux.get(popAux.size()-1);
	}
		
	public int getTamPop() {
		return tamPop;
	}

	public ArrayList<Cavalo> getPopCavalos() {
		return popCavalos;
	}

	public Tabuleiro getTabuleiroAG() {
		return tabuleiroAG;
	}

	public double getProbRecombinacao() {
		return probRecombinacao;
	}

	public double getProbMutacao() {
		return probMutacao;
	}
}
