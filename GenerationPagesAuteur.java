import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;


public class GenerationPagesAuteur
{
	public static void generer(String repertoire, ArrayList<Auteur> alAuteur, ArrayList<Jeu> alJeu)
	{
  		PrintWriter pw = null;
		
		
		for ( int cpt1 = 0; cpt1 < alAuteur.size(); cpt1 ++ )
		{
			
			
			try{	pw = new PrintWriter ( new File ( "C:\\iut\\TP\\s1\\sae1_01\\travail\\ludotheque\\indexAuteur.html" ) , "utf-8" ); }
			catch (Exception e){e.printStackTrace();}
			
			GenerationPagesAuteur.haut    ( pw) ;
			// ( "../" + repertoire + "/auteur" + cpt + ".html")
			for ( int cpt2 = 0; cpt2 < alAuteur.size(); cpt2 ++ )
			{			
				pw.println ( "\t\t" +"<a "+ "href= C:\\iut\\TP\\s1\\sae1_01\\travail\\ludotheque\\auteur" +cpt2 + ">" + alAuteur.get(cpt2).getPrenomNom() +	"</a> </br>"    );
				pw.println ();
						
			}
			
		}
		
		GenerationPagesAuteur.basPage ( pw ) ;
		pw.close();
  	}
	
	public static void haut ( PrintWriter pw)
    {
       		
		pw.println("<!DOCTYPE html>\r\n"                                                     );
		pw.println();
		pw.println("<html>\r\n"																 );
		pw.println("	<head>\r\n"															 );
		pw.println("		<title> SAE 1.01</title>\n"										 );
		pw.println("		<meta charset=\"utf-8\">\r\n"									 );
		pw.println("		<meta name=\"Author\" lang=\"fr\" content=\"Groupe_10\"  />\r\n" );
		pw.println("	</head>\r\n"														 );
		pw.println();
		pw.println("	</body>\r\n");
		
    }
	
	public static void basPage(PrintWriter pw)
    {        
         
        pw.println("	</body>\r\n" ) ;
        pw.println("</html>\r\n");
    }
        
}