package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author yamatokataoka
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		char[] wordLowChars = word.toLowerCase().toCharArray();
		TrieNode current = root;
		boolean output = false;

	    for (int k=0; k<wordLowChars.length; k++) {
	    	TrieNode newNode = current.insert(wordLowChars[k]);

	    	if (newNode == null) {
	    		current = current.getChild(wordLowChars[k]);
	    	} else {
	    		current = newNode;
	    	}
	    	
	    	if (k == wordLowChars.length-1 && !current.endsWord()) {
	    		current.setEndsWord(true);
	    		size++;
	    		output = true;
	    	}
	    }
	    return output;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		char[] stringLowChars = s.toLowerCase().toCharArray();
		TrieNode current = root;

		if (stringLowChars.length == 0) {
			return false;
		}

		for (int k=0; k<stringLowChars.length; k++) {
	    	if (current.getValidNextCharacters().contains(stringLowChars[k])) {
	    		current = current.getChild(stringLowChars[k]);
	    	} else {
	    		return false;
	    	}
	    }

		return true;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 char[] stringLowChars = prefix.toLowerCase().toCharArray();
    	 TrieNode current = root;
    	 LinkedList<String> completions;

    	 for (int k=0; k<stringLowChars.length; k++) {
  	    	if (current.getValidNextCharacters().contains(stringLowChars[k])) {
  	    		current = current.getChild(stringLowChars[k]);
  	    	} else {
  	    		completions = new LinkedList<String>();
  	    		return completions;
  	    	}
  	     }

    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(current);

    	 completions = new LinkedList();
    	 while (queue.size() != 0 && completions.size() < numCompletions) {
    		 TrieNode node = queue.remove();
    		 if (node.endsWord() && !completions.contains(node.getText())) {
    			 completions.add(node.getText());
    		 }
    		 for (Character c : current.getValidNextCharacters()) {
    			 queue.add(current.getChild(c));
    		 }
    		 current = node;
    	 }
    	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}