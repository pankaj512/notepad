package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if (sourceText.length() != 0) {
            ListNode temp;
            String[] words = sourceText.split("[\\s]+");
            this.starter = words[0];
            ListNode last = temp = new ListNode(this.starter);
            this.wordList.add(last);
            for (int i = 1; i < words.length; ++i) {
                temp = this.find(last.getWord());
                temp.addNextWord(words[i]);
                ListNode temp2 = this.find(words[i]);
                if (!temp2.getWord().equals("not found")) {
                    last = temp2;
                    continue;
                }
                temp2 = new ListNode(words[i]);
                this.wordList.add(temp2);
                last = temp2;
            }
        }
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		 String curWord = this.starter;
        String output = "";
        if (numWords != 0 && !this.wordList.isEmpty()) {
            output = output + curWord;
            while (numWords > 0) {
                ListNode temp = this.find(curWord);
                curWord = temp.getRandomNextWord(this.rnGenerator);
                if (curWord.equals("")) {
                    curWord = this.starter;
                }
                output = output + " " + curWord;
                --numWords;
            }
        }
        return output;
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		this.wordList = new LinkedList<ListNode>();
        if (sourceText.length() != 0) {
            ListNode temp;
            String[] words = sourceText.split("[\\s]+");
            this.starter = words[0];
            ListNode last = temp = new ListNode(this.starter);
            this.wordList.add(last);
            for (int i = 1; i < words.length; ++i) {
                temp = this.find(last.getWord());
                temp.addNextWord(words[i]);
                ListNode temp2 = this.find(words[i]);
                if (!temp2.getWord().equals("not found")) {
                    last = temp2;
                    continue;
                }
                temp2 = new ListNode(words[i]);
                this.wordList.add(temp2);
                last = temp2;
            }
        }
	}

	// TODO: Add any private helper methods you need here.
	 private ListNode find(String word) {
        ListNode re = new ListNode("not found");
        ListIterator<ListNode> lt = this.wordList.listIterator();
        while (lt.hasNext()) {
            ListNode t = lt.next();
            if (!t.getWord().equals(word)) continue;
            return t;
        }
        return re;
    }

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
	    return null;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}


