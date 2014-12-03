/*
 * Universidade Federal do Pará - UFPA
 * Instituto de Ciências Exatas e Naturais - ICEN
 * Faculdade de Computação - FC
 * Disciplina: Computação Evolucionária
 * Professor: Claudomiro Sales
 * Aluno: Matheus Ferreira Freire
 * Data: 03/12/2014
 */

public class Tabuleiro {
	static int tamTabuleiro = 8;
	private Casa [][] tabuleiro = new Casa [getTamTabuleiro()][getTamTabuleiro()];

	Tabuleiro()
	{
		criaTabuleiro();
		calculaVizinhosCavalo();
	}
	
	private void criaTabuleiro ()
	{
		int cont=0;
		for (int i = 0; i < getTabuleiro().length; i++)
		{
			for (int j = 0; j < getTabuleiro()[i].length; j++) 
			{
				getTabuleiro()[i][j] = new Casa();
				getTabuleiro()[i][j].setNum(cont);
				cont++;
			}
		}
	}
	
	private void calculaVizinhosCavalo ()
	{
		for (int i = 0; i < getTabuleiro().length; i++)
		{
			for (int j = 0; j < getTabuleiro()[i].length; j++)
			{
				//1. calcula posição L+2, C+1 
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i+2][j+1].getNum());			
				}
				catch(Exception e){
					
				}
				//2. calcula posição L+2, C-1
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i+2][j-1].getNum());			
				}
				catch(Exception e){
					
				}
				//3. calcula posição L-2, C+1
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i-2][j+1].getNum());			
				}
				catch(Exception e){
					
				}
				//4. calcula posição L-2, C-1
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i-2][j-1].getNum());			
				}
				catch(Exception e){
					
				}
				//5. calcula posição L+1, C+2
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i+1][j+2].getNum());			
				}
				catch(Exception e){
					
				}
				//6. calcula posição L-1, C+2
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i-1][j+2].getNum());			
				}
				catch(Exception e){
					
				}
				//7. calcula posição L+1, C-2
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i+1][j-2].getNum());			
				}
				catch(Exception e){
					
				}
				//8. calcula posição L-1, C-2
				try{
					getTabuleiro()[i][j].getVizinhosCavalo().add(getTabuleiro()[i-1][j-2].getNum());			
				}
				catch(Exception e){
					
				}
			}
			
		}
	}
	
	public void printTabuleiro()
	{
		for (int i = 0; i < tabuleiro.length; i++)
		{
			for (int j = 0; j < tabuleiro[i].length; j++)
			{
				System.out.print(getTabuleiro()[i][j].getNum() + "   ");
			}
			System.out.println();
		}
	}

	public int getTamTabuleiro() {
		return tamTabuleiro;
	}

	public Casa [][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Casa [][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}

