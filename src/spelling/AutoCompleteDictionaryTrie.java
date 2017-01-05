package spelling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
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
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
	    String w = word.toLowerCase();
        TrieNode temp = this.root;
        int count = 0;
        for (int i = 0; i < w.length(); ++i) {
            char c = w.charAt(i);
            if (temp.getValidNextCharacters().contains(Character.valueOf(c))) {
                temp = temp.getChild(Character.valueOf(c));
                continue;
            }
            temp = temp.insert(Character.valueOf(c));
            ++count;
        }
        temp.setEndsWord(true);
        if (count != 0) {
            return true;
        }
        return false;
	}

	/**
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	   int count = 0;
           LinkedList<TrieNode> list_trie = new LinkedList<TrieNode>();
           list_trie.add(this.root);
           while (!list_trie.isEmpty()) {
           ArrayList<TrieNode> trie_child;
           TrieNode temp = (TrieNode)list_trie.pop();
           if (temp.endsWord()) {
                ++count;
           }
           if ((trie_child = temp.getAllChild()) == null)
               continue;
           list_trie.addAll(trie_child);
          }
         return count;
	}


	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s)
	{
	    // TODO: Implement this method
		if (s.length() != 0) {
            TrieNode temp = this.root;
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!temp.getValidNextCharacters().contains(Character.valueOf(c))) {
                    return false;
                }
                temp = temp.getChild(Character.valueOf(c));
            }
            return true;
        }
        return false;
	}

	/**
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions

         TrieNode search_node;
        LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
        LinkedList<String> completions = new LinkedList<String>();
        if (numCompletions != 0 && (search_node = this.findWord(prefix.toLowerCase())) != null) {
            queue.add(search_node);
            while (!queue.isEmpty() && numCompletions != 0) {
                TrieNode temp = (TrieNode)queue.pop();
                if (temp.endsWord()) {
                    completions.add(temp.getText());
                    --numCompletions;
                }
                queue.addAll(temp.getAllChild());
            }
        }
        return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		this.printNode(this.root);
 	}

 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) {
            return;
        }
        System.out.println(curr.getText() + "  " + curr.endsWord());
        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            this.printNode(next);
        }
 	}

    public TrieNode findWord(String s) {
        TrieNode temp = this.root;
        int i = 0;
        if (s.length() != 0) {
            s = s.toLowerCase();
            for (i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!temp.getValidNextCharacters().contains(Character.valueOf(c))) break;
                temp = temp.getChild(Character.valueOf(c));
            }
            if (i != s.length()) {
                return null;
            }
            return temp;
        }
        return this.root;
    }


}
