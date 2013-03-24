import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

public class JDOM1
{
   //Nous allons commencer notre arborescence en cr�ant la racine XML
   //qui sera ici "personnes".
   static Element racine = new Element("personnes");

   //On cr�e un nouveau Document JDOM bas� sur la racine que l'on vient de cr�er
   static org.jdom2.Document document = new Document(racine);

   public static void main(String[] args)
   {
      //On cr�e un nouvel Element etudiant et on l'ajoute
      //en tant qu'Element de racine
      Element etudiant = new Element("etudiant");
      racine.addContent(etudiant);

      //On cr�e un nouvel Attribut classe et on l'ajoute � etudiant
     //gr�ce � la m�thode setAttribute
      Attribute classe = new Attribute("classe","P2");
      etudiant.setAttribute(classe);

      //On cr�e un nouvel Element nom, on lui assigne du texte
      //et on l'ajoute en tant qu'Element de etudiant
      Element nom = new Element("nom");
      nom.setText("CynO");
      etudiant.addContent(nom);

      //Les deux m�thodes qui suivent seront d�finies plus loin dans l'article
      affiche();
      enregistre("Exercice1.xml");
   }
   
 //Ajouter ces deux m�thodes � notre classe JDOM1
   static void affiche()
   {
      try
      {
         //On utilise ici un affichage classique avec getPrettyFormat()
         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
         sortie.output(document, System.out);
      }
      catch (java.io.IOException e){}
   }

   static void enregistre(String fichier)
   {
      try
      {
         //On utilise ici un affichage classique avec getPrettyFormat()
         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
         //Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
         //avec en argument le nom du fichier pour effectuer la s�rialisation.
         sortie.output(document, new FileOutputStream(fichier));
      }
      catch (java.io.IOException e){}
   }
}