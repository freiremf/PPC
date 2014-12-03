/*
 *  Universidade Federal do Pará - UFPA
 * Instituto de Ciências Exatas e Naturais - ICEN
 * Faculdade de Computação - FC
 * Disciplina: Computação Evolucionária
 * Professor: Claudomiro Sales
 * Aluno: Matheus Ferreira Freire
 * Data: 03/12/2014
 */

import java.util.ArrayList;
import java.util.Comparator;

public class Cavalo {
	static MersenneTwisterFast obj = new MersenneTwisterFast();
	private int tamMovimentos = Tabuleiro.tamTabuleiro*Tabuleiro.tamTabuleiro; //tamanho do cromossomo
	private ArrayList<Integer> movimentos = new ArrayList<>(); //cromossomo
	private int fitness;
	//construtor inicia um cavalo com movimentos aleatórios
	Cavalo()
	{
		criaMovimentoAleatorio();
	}
	
	private void criaMovimentoAleatorio()
	{
		int aleatorio;
		ArrayList<Integer> arrayAux = new ArrayList<>();
		for (int i = 0; i < getTamMovimentos(); i++)
			arrayAux.add(i);
		
		for (int i = 0; i < getTamMovimentos(); i++) 
		{
			aleatorio = obj.nextInt(arrayAux.size());
			getMovimentos().add(arrayAux.get(aleatorio));
			arrayAux.remove(aleatorio);
		}
		
	}
	
	//ordena cavalos de acordo com o fitness
			public static Comparator<Cavalo> IndFitness = new Comparator<Cavalo>()
			{		
				@Override
				public int compare(Cavalo arg0, Cavalo arg1)
				{			
					int fit1 = arg0.getFitness();
					int fit2 = arg1.getFitness();
					return fit1-fit2;
				}
			};

	public int getTamMovimentos() {
		return tamMovimentos;
	}

	public void setTamCromossomo(int tamCromossomo) {
		this.tamMovimentos = tamCromossomo;
	}

	public ArrayList<Integer> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(ArrayList<Integer> movimentos) {
		this.movimentos = movimentos;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
}
