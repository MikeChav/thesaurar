import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

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

	public static  String convert(String data, String length, String noun, String verb, String adjective, String adverb, String depth) {
		HashMap<String, Pointer> relationships;
		relationships = new HashMap<String, Pointer>();
		relationships.put("Synonym", null);
		relationships.put("Hypernym", Pointer.HYPERNYM);
		relationships.put("Antonym", Pointer.ANTONYM);
		relationships.put("Entailment", Pointer.ENTAILMENT);
		relationships.put("Similar to", Pointer.SIMILAR_TO);
		try {
			IDictionary dict = new Rewriter().getDictionary();
			Text t = new Text(dict, data);
			boolean longest = "long".equals(length);
			return (t.rewriteText(longest, relationships.get(noun), relationships.get(verb), relationships.get(adjective), relationships.get(adverb), Integer.parseInt(depth)));
		} catch (IOException e) {
			return e.getMessage();
		}
	}

}
