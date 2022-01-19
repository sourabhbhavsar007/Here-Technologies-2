package phonebook;

import java.util.*;

class TrieNode
{
   /** Each Trie Node contains a Map 'child'
   where each alphabet points to a Trie
   Node. */
   HashMap<Character,TrieNode> child;

   /** 'isLast' is true if the node represents
   end of a contact */
   boolean isLast;

   public TrieNode()
   {
      child = new HashMap<Character,TrieNode>();
      isLast = false;
   }
}

public class Trie
{
   TrieNode root;

   // Insert all the Contacts into the Trie
   public void insertIntoTrie(String contacts[])
   {
      root = new TrieNode();
      int n = contacts.length;
      for (int i = 0; i < n; i++)
      {
         insert(contacts[i].toLowerCase());
      }
   }

   // Insert a Contact into the Trie
   public void insert(String s)
   {
      int len = s.length();

      TrieNode itr = root;
      for (int i = 0; i < len; i++)
      {

         TrieNode nextNode = itr.child.get(s.charAt(i));
         if (nextNode == null)
         {
            // If not found then create a new TrieNode
            nextNode = new TrieNode();

            // Insert into the HashMap
            itr.child.put(s.charAt(i),nextNode);
         }

         itr = nextNode;

         if (i == len - 1)
            itr.isLast = true;
      }
   }

   /** This function simply displays all dictionary words
    going through current node. String 'prefix'
    represents string corresponding to the path from
   root to curNode. */
   public void displayContactsUtil(TrieNode curNode,
         String prefix)
   {
      if (curNode.isLast)
         System.out.println(prefix);

      for (Character c : curNode.child.keySet()) {
         displayContactsUtil(curNode.child.get(c), (prefix+c));
      }

   }

   /** This method displays suggestions after every character enter by
    the user for a given string 'str' */
   public void displayContacts(String str)
   {
      TrieNode prevNode = root;

      String prefix = "";
      int len = str.length();

      int i;
      for (i = 0; i < len; i++)
      {
         prefix += str.charAt(i);

         char lastChar = prefix.charAt(i);

         TrieNode curNode = prevNode.child.get(lastChar);

         // If nothing found, then break the loop as
         // no more prefixes are going to be present.
         if (curNode == null)
         {
            System.out.println("No Results Found for " + prefix +  " ");
            i++;
            break;
         }

         // If present in trie then display all
         // the contacts with given prefix.
         System.out.println("Suggestions based on " + prefix + " are ");
         displayContactsUtil(curNode, prefix);

         prevNode = curNode;
      }

      for ( ; i < len; i++)
      {
         prefix += str.charAt(i);
         System.out.println("No Results Found for " + prefix + " ");
      }
   }

}