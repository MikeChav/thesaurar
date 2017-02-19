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

	public static  String convert(String data) {
		try {
			IDictionary dict = new Rewriter().getDictionary();
			Text t = new Text(dict, data);
			return (t.rewriteText(true, null, null, null,  null,  2));
		} catch (IOException e) {
			return e.getMessage();
		}
	}

}
