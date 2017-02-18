import java.util.LinkedList;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.Pointer;


public class Text {

	LinkedList<Word> breakup;
	IDictionary dict;
	String text;
	
	public Text(IDictionary dict, String text){
		this.dict = dict;
		this.text = text;
		breakUpText();
	}
	
	public void breakUpText(){
		breakup = new LinkedList<Word>();
		
		boolean punctuation = true;
		if(Character.isLetter(text.charAt(0))){
			punctuation = false;
		}
		String block = "";
		for(int i = 0; i< text.length(); i++){
			char character = text.charAt(i);
			if(Character.isLetter(character) && !punctuation){
				block = block + character;
			}else if(Character.isLetter(character) && punctuation){
				Word w = new Word(dict, block);
				breakup.add(w);
				punctuation = false;
				block = "" + character;
			}else if(!Character.isLetter(character) && punctuation){
				block = block + character;
			}else if(!Character.isLetter(character) && !punctuation){ 
				Word w = new Word(dict, block);
				breakup.add(w);
				punctuation = true;
				block = "" + character;
			}
			
		}
		Word w = new Word(dict, block);
		breakup.add(w);
		
	}
	
	public void rewriteText(){
		for(Word word : breakup){
			System.out.print(word.getWordReplacement(true, Pointer.HYPERNYM, 0));
		}
	}
}
