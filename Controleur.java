public class Controleur
{
	private Metier met;

	public Controleur(String repDestination )
	{
		this.met = new Metier();

		this.met.genererPages(repDestination);

		System.out.println ( this.met.toString() );
	}


	public static void main(String[] a)
	{
		String repCible = "ludotheque";

		new Controleur( repCible );
	}

}