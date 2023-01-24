import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.io.FileReader;
import java.nio.charset.Charset;


public class GenerationPageAccueil
{
	public static void generer( String repertoire, ArrayList<Auteur> alAuteur, ArrayList<Editeur> alEditeur)
	{
		
		String ligne ;
  		PrintWriter pw = null;

  		try{	pw = new PrintWriter ( new File ( "../" + repertoire + "/indexAccueil.html" ), "utf-8" ); }
  		catch (Exception e){e.printStackTrace();}
		
		
		try
		{
			Scanner sc = new Scanner ( new FileReader ( "C:\\iut\\TP\\s1\\sae1_01\\travail\\ex4"  , Charset.forName("UTF-8") )  );

			while ( sc.hasNextLine() )
			{	
		  		ligne = sc.nextLine();
				
				pw.println ( ligne ) ;

			}
			sc.close();
		for ( Auteur aut : alAuteur )
		pw.println ( "\t\t" + aut.toString() + "<br />" );
		

			pw.close();
		
		}catch (Exception e){e.printStackTrace();}
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