// ArrayLists
import java.util.ArrayList;

// Gestion de fichier
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// IUT
import iut.algo.*;

public class Metier 
{
    // Chemin vers le dossier des fichiers .data
    private static final String DOSSIER = "C:\\iut\\TP\\s1\\sae1_01\\travail\\ex4\\FichiersPlats";
    
    // Attributs
    private ArrayList<Categorie> alCategorie;
    private ArrayList<Editeur>   alEditeur;
    private ArrayList<Auteur>    alAuteur;

    private ArrayList<Jeu>       alJeu;
    
    // Constructeur
    public Metier()
    {
        initCategorie();
        initEditeur  ();
        initAuteur   ();
        initJeu      ();
    }

    // Inits
    private void initCategorie()
    {
        // Instanciation
        this.alCategorie = new ArrayList<Categorie>();

        try
		{
            // Ouverture du fichier
			Scanner sc = new Scanner ( new FileReader( DOSSIER + "\\categorie.data" ) );

			while ( sc.hasNextLine() )
            {
				this.alCategorie.add( new Categorie( sc.nextLine() ) );
            }
            sc.close();
		}
		catch ( Exception e ) { e.printStackTrace(); }
    }

    private void initEditeur()
    {
        // Instanciation
        this.alEditeur = new ArrayList<Editeur>();
        
        try
		{
			// Ouverture du fichier
            Scanner sc = new Scanner ( new FileReader( DOSSIER + "\\editeur.data" ) );

			while ( sc.hasNextLine() )
            {
				// Decomposeur à partir de la ligne courante
                Decomposeur dec = new Decomposeur( sc.nextLine() );

                this.alEditeur.add( new Editeur( dec.getString( 0 ), dec.getString( 1 ) ) );
            }
            sc.close();
		}
		catch ( Exception e ) { e.printStackTrace(); }
    }

    private void initAuteur()
    {
		// Instanciation
        this.alAuteur = new ArrayList<Auteur>();

        try
		{
			// Ouverture du fichier
			Scanner sc = new Scanner ( new FileReader( DOSSIER + "\\jeu.data" ) );

			while ( sc.hasNextLine() )
            {
				// Decomposeur à partir de la ligne courante
                Decomposeur dec = new Decomposeur( sc.nextLine() );

                for ( int cpt = 3 ; cpt < 5 ; cpt++ )
                {
                    // Si l'auteur est renseigné, et qu'il n'existe pas dans la liste des auteurs
                    if ( dec.getString( cpt ).equals( "" ) == false && isAuteurInList( dec.getString( cpt ) ) == false )
                    {
                        this.alAuteur.add( new Auteur( dec.getString( cpt ) ) );
                    }
                }
            }
            sc.close();
		}
		catch ( Exception e ) { e.printStackTrace(); }
    }

    private void initJeu()
    {
		// Instanciation
        this.alJeu = new ArrayList<Jeu>();

        try
		{
			// Ouverture du fichier
			Scanner sc = new Scanner ( new FileReader( DOSSIER + "\\jeu.data" ) );

			while ( sc.hasNextLine() )
            {
				// Decomposeur à partir de la ligne courante
				Decomposeur dec = new Decomposeur( sc.nextLine() );

                // On récupère les objets qu'on va lier au jeu
                Categorie cat      = getCategorie( dec.getString( 0 ) );
                Auteur    auteur1  = getAuteur   ( dec.getString( 3 ) );
                Auteur    auteur2  = getAuteur   ( dec.getString( 4 ) );
				Editeur   editeur  = this.alEditeur.get( dec.getInt( 5 ) );

                this.alJeu.add( new Jeu( 
                    cat, 
                    dec.getString( 1 ), 
                    dec.getString( 2 ), 
                    auteur1, auteur2, 
                    editeur, 
                    dec.getInt( 6 ), dec.getInt( 7 ), 
                    dec.getInt( 8 ), 
                    dec.getInt( 9 ),
                    dec.getString( 10 )
                ));
            }
            sc.close();
		}
		catch ( Exception e ) { e.printStackTrace(); }
    }

    // Méthodes
	
    // Retourne si l'auteur existe dans la liste par son prenom et nom
    private boolean isAuteurInList( String prenomNomAuteur )
    {
        for ( Auteur aut : this.alAuteur )
        {
            if ( aut.getPrenomNom().equals( prenomNomAuteur ) )
            {
                return true;
            }
        }
        
        return false;
    }

    private Categorie getCategorie( String lib )
    {
        for ( Categorie cat : this.alCategorie )
        {
            if ( cat.getLibelle().substring( 0, 1 ).equals( lib ) )
            {
                return cat;
            }
        }
        return null;
    }

    private Auteur getAuteur( String nomPrenom )
    {
        for ( Auteur auteur : this.alAuteur )
        {
            if ( auteur.getPrenomNom().equals( nomPrenom ) )
            {
                return auteur;
            }
        }
        return null;
    }
	
	
                                                                                      // ------------
	public void genererPages( String repDest)                                        // genererPages
	{                                                                                // ------------
		try
		{
			if ( !Files.exists ( Paths.get("../" + repDest) ) )
			{
				Files.createDirectory ( Paths.get("../" + repDest) );
			}
		}catch(Exception e){ e.printStackTrace(); }

		GenerationPageAccueil .generer ( repDest, alAuteur, alEditeur );
		GenerationPagesAuteur .generer ( repDest, alAuteur, alJeu     );
		GenerationPagesEditeur.generer ( repDest, alEditeur, alJeu    );
	}

    // Affichage
    public String toString()
    {
        String sRet;

        sRet = "Catégories :\n";
        for ( Categorie cat : this.alCategorie )
        {
            sRet += cat + "\n";
        }

        sRet += "\nEditeurs :\n";
        for ( Editeur edi : this.alEditeur )
        {
            sRet += edi + "\n";
        }

        sRet += "\nAuteurs :\n";
        for ( Auteur aut : this.alAuteur )
        {
            sRet += aut + "\n";
        }

        sRet += "\nJeu :\n";
        for ( Jeu jeu : this.alJeu )
        {
            sRet += jeu + "\n";
        }

        return sRet;
    }
}
