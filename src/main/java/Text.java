import java.util.LinkedList;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.POS;
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
	
	public String rewriteText(boolean longest, Pointer noun, Pointer verb,  Pointer adjective, Pointer adverb, int level){
		String newString = "";
		for(Word word : breakup){
			
			if(word.getPOS() == POS.NOUN){
				newString = newString +" "+word.getWordReplacement(longest, noun, level);
			}else if(word.getPOS() == POS.VERB){
				newString = newString +" "+ word.getWordReplacement(longest, verb, level);
			}else if(word.getPOS() == POS.ADJECTIVE){
				newString = newString +" "+ word.getWordReplacement(longest, adjective, level);
			}else if(word.getPOS() == POS.ADVERB){
				newString = newString +" "+ word.getWordReplacement(longest, adverb, level);
			}else{
				newString = newString + word.rawWord;
			}
		}
		return newString;
	}
}
