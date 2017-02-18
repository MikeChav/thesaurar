import java.io.IOException;
import java.net.URL;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;


public class Rewriter {

	public static IDictionary getDictionary() throws IOException{
		 // construct the URL to the Wordnet dictionary directory
		 URL url = new URL ("file", null , "dict" ) ;
		
		 // construct the dictionary object and open it
		 IDictionary dict = new Dictionary(url) ;
		 dict.open() ;
		 return dict;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		IDictionary dict  = getDictionary();
		Word w = new Word(dict, "red");
		System.out.println(w.longestSynonym());
		Text t = new Text(dict, "Membership lists reviewed by The New York Times show that the club’s nearly 500 paying members include dozens of real estate developers, Wall Street financiers, energy executives and others whose businesses could be affected by Mr. Trump’s policies. At least three club members are under consideration for an ambassadorship. Most of the 500 have had memberships predating Mr. Trump’s presidential campaign, and there are a limited number of memberships still available.");
		t.rewriteText();
	}


}
