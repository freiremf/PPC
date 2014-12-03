/*
 * Universidade Federal do Pará - UFPA
 * Instituto de Ciências Exatas e Naturais - ICEN
 * Faculdade de Computação - FC
 * Disciplina: Computação Evolucionária
 * Professor: Claudomiro Sales
 * Aluno: Matheus Ferreira Freire
 * Data: 03/12/2014
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int qtdGeracao = 200;
		double desvio = 0;
		AGPPC pop = new AGPPC();
		pop.calculaFitness();
		
		PrintWriter writer = new PrintWriter("execucao05.txt", "UTF-8");
		
		for (int p = 0; p < qtdGeracao; p++) 
		{
			System.out.println("======== Geracao " + (p + 1) + " =========");
	
			writer.println(pop.getMelhorIndividuo().getFitness());
						
			double mAtual = pop.calculaMedia();
			
			for (int j = 0; j < pop.getPopCavalos().size(); j++) 
			{
				desvio = desvio + Math.pow((pop.getPopCavalos().get(j).getFitness() - mAtual), 2); //soma dos quadrados dos desvios			
			}
			
			desvio = Math.sqrt(desvio)/pop.getPopCavalos().size(); // desvio padrao (desvio/50)
						
			if(pop.getMelhorIndividuo().getFitness() == pop.getPopCavalos().get(0).getTamMovimentos()-1)
			{
				System.out.println("Melhor Indivíduo Encontrado!!!");
				break;
			}
			
			pop.steadyState();
			
			pop.calculaFitness();	
		}
		writer.close();
		
		System.out.println("===== Problema do Percurso do Cavalo ====="); System.out.println();
		System.out.println("===== População Final =====");
		pop.printPopCavalos(); System.out.println();
		System.out.println("===== Melhor Indivíduo Encontrado =====");
		for (int i = 0; i < pop.getPopCavalos().get(0).getTamMovimentos(); i++)
			System.out.print(pop.getMelhorIndividuo().getMovimentos().get(i) + "|");
		System.out.println();
		System.out.println("===== Fitness do Melhor Indivíduo =====");
		System.out.println(pop.getMelhorIndividuo().getFitness());
		
		
		//======================================================================================
		/*
		
		System.out.println("============== Tabuleiro ==============");
		pop.getTabuleiroAG().printTabuleiro();
		
		System.out.println();
		
		System.out.println("============== População de Cavalos ==============");
		pop.printPopCavalos();
		
		System.out.println("============== Vizinhos da Casa [i][j] ==============");
		System.out.println(pop.getTabuleiroAG().getTabuleiro()[2][2].getNum());
		pop.getTabuleiroAG().getTabuleiro()[2][2].printVizinhos();
		
		pop.calculaFitness();
		
		System.out.println("============== Fitness ==============");
		for (int i = 0; i < pop.getTamPop(); i++)
		{
			System.out.println(i + "- " + pop.getPopCavalos().get(i).getFitness());			
		}
		
		Cavalo filhoGerado = new Cavalo(); 
		filhoGerado = pop.edgeRecombination(pop.getPopCavalos().get(0), pop.getPopCavalos().get(1));
		
		System.out.println("============== Filho Gerado ==============");
		
		for (int i = 0; i < filhoGerado.getTamMovimentos(); i++)
			System.out.print(filhoGerado.getMovimentos().get(i) + "|");
		System.out.println();
		System.out.println("============== Filho Mutado ==============");
		pop.mutacaoBaseadaVizinhos(filhoGerado);
		for (int i = 0; i < filhoGerado.getTamMovimentos(); i++)
			System.out.print(filhoGerado.getMovimentos().get(i) + "|");

		===================================================================
		
		System.out.println("============== População de Cavalos ==============");
			pop.printPopCavalos();
			System.out.println("============== Fitness ==============");
			for (int kl = 0; kl < pop.getTamPop(); kl++)
				System.out.println(kl + "- " + pop.getPopCavalos().get(kl).getFitness());
			
			System.out.println("============== Melhor Indivíduo da Geração ==============");
			for (int gg = 0; gg < pop.getPopCavalos().get(0).getTamMovimentos(); gg++)
				System.out.print(pop.getMelhorIndividuo().getMovimentos().get(gg) + "|");
			
			System.out.println();
		*/
		
		
			
	}

}
