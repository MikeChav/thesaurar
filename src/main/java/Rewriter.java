import java.io.IOException;
import java.net.URL;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.Pointer;


public class Rewriter {

	public IDictionary getDictionary() throws IOException{
		 // construct the URL to the Wordnet dictionary directory
		 URL url = getClass().getResource("dict") ;
//		 URL url = new URL ("file", null , "dict" ) ;

		 // construct the dictionary object and open it
		 IDictionary dict = new Dictionary(url) ;
		 dict.open() ;
		 return dict;
	}
	
//	/**
//	 * @param args
//	 * @throws IOException
//	 */
//	public static void main(String[] args) throws IOException {
//		IDictionary dict  = getDictionary();
//		Text t = new Text(dict, "Membership lists reviewed by The New York Times show that the club’s nearly 500 paying members include dozens of real estate developers, Wall Street financiers, energy executives and others whose businesses could be affected by Mr. Trump’s policies. At least three club members are under consideration for an ambassadorship. Most of the 500 have had memberships predating Mr. Trump’s presidential campaign, and there are a limited number of memberships still available.");
//
//		//rewriteText(longest or shortest, Noun, Verb, Adjective, Adverb, level) either enter a pointer relationship or leave null for synonyms
//		//not all pointer relationships are applicable, but it wont cause an error.
//		System.out.println(t.rewriteText( true, null, Pointer.ANTONYM, Pointer.SIMILAR_TO, Pointer.HYPONYM, 2));
//	}

	public static  String convert(String data) {
		try {
			IDictionary dict = new Rewriter().getDictionary();
			Text t = new Text(dict, data);
			return (t.rewriteText(true, null, Pointer.ANTONYM, Pointer.SIMILAR_TO, Pointer.HYPONYM, 2));
		} catch (IOException e) {
			return e.getMessage();
		}
	}


}
