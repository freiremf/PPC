import java.util.ArrayList;


public class Adjacencia
{
	private ArrayList<Integer> adjacentes = new ArrayList<>();
	private int elemento;

	public ArrayList<Integer> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(ArrayList<Integer> adjacentes) {
		this.adjacentes = adjacentes;
	}

	public int getElemento() {
		return elemento;
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
}
