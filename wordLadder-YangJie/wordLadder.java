import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class wordLadder {

	public static void error(String s) throws Exception
	{
		throw new Exception(s);
	}
	
	public static void neighbors(String topword, Set<String> allwords, Set<String> neigh)
	{//get all the neighbors of the first word in the stack
		
		for (int i = 0; i < topword.length(); ++i)
		{
			StringBuffer buff = new StringBuffer(topword);
			for (char j = 'a'; j <= 'z'; j++)
			{
				buff.setCharAt(i, j);
				String temp = buff.toString();
				if (allwords.contains(temp))
					neigh.add(temp);
			}
		}
	}
	
	public static Stack<String> findway(String w1, String w2, Set<String> allwords) 
	{//find the way from w2 to w1
		
		Queue<Stack<String>> allstack = new LinkedList<Stack<String>>();
		//collect all the stacks whose first word is a neighbor
		Stack<String> firsta = new Stack<String>();
		firsta.push(w1);
		//create a new stack that has only w1
		allstack.offer(firsta);
		Set<String> usedwords = new HashSet<String>();
		//collect all the used words in the ladder

		while (!allstack.isEmpty()) 
		{
			Stack<String> nowstack = allstack.poll();
			//get and cancel the used stack from queue
			String topword = nowstack.peek();
			usedwords.add(topword);
			//insert a used word while getting a new stack from the queue

			Set<String> wordneighbor = new HashSet<String>();
			neighbors(topword, allwords, wordneighbor);
			//collect neighbors of the top word
			
			for (String neigh : wordneighbor) 
			{
				if (!usedwords.contains(neigh)) 
				{
					if (neigh.equals(w2)) 
					{//if the neighbor is w2, return this stack
						nowstack.push(neigh);
						return nowstack;
					}
					else 
					{//create a new stack with the neighbor on its top and put it into the queue
						Stack<String> newstack = (Stack<String>) nowstack.clone();
						newstack.push(neigh);
						allstack.offer(newstack);
					}
				}
			}
		}
		return new Stack<String>();

	}
	
	public static void getwords(File file, Set<String> allwords) throws Exception
	{//get words from the file
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String word = null;
		word = br.readLine();
		while(word != null)
		{
			allwords.add(word);
			word = br.readLine();
		}
		br.close();
	}
	
	public static String checkinput(Scanner sc) throws Exception 
	{//check invalid input
		
		String input = sc.nextLine();
		
		if (input == "")error("wrong input");
		//error when input nothing
		for (int i = input.length();--i>=0;)
		{  //error when input numbers
			      if (Character.isDigit(input.charAt(i)))
			    	  error("wrong input");
		}
		
		return input;
		}
	
	public static void wordladder(Set<String> allwords,Scanner sc) throws Exception 
	{//output, input,and respond to the invalid input
		
		while (true) 
		{
			System.out.println("Word #1 (or Enter to quit) : ");
			String w1 = checkinput(sc);
			w1 = w1.toLowerCase();
			if (!allwords.contains(w1))error("The two words must be found in the dictionary.");

			System.out.println("Word #2 (or Enter to quit) : ");
			String w2 = checkinput(sc);
			w2 = w2.toLowerCase();
			if (!allwords.contains(w2))error("The two words must be found in the dictionary.");
			if (w1.length() != w2.length())error("The two words must be the same length.");
			if (w1 == w2)error("The two words must be different.");

			Stack<String> theway = findway(w1, w2, allwords);
			System.out.println ("A ladder from " + w2 + " to " + w1 + ": " + "\n");
			if (theway.empty())
			{
				System.out.println( "No word ladder from " + w2 + " back to " + w1 + ".");
			}
			while (!theway.empty())
			{
				System.out.println( theway.pop() + " ");
			}
			System.out.println("\n");
		}
	}
	
	public static void tryit(Scanner sc) throws Exception 
	{
		System.out.println("Dictionary file name?");
		String filename = checkinput(sc);
		File file = new File("src/" + filename);
		Set<String> allwords = new HashSet<String>();
		getwords(file, allwords);

		while (true)
		try
		{
			wordladder(allwords,sc);
			System.out.println( "Have a nice day." + "\n" );
			break;
		}
		catch (Exception e) 
		{
			System.out.println( e.getMessage() + "\n" + "\n");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		while(true)
			try 
		{
			Scanner sc = new Scanner(System.in);
			tryit(sc);
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage() + "\n" + "\n");
		}
	}
	
}
