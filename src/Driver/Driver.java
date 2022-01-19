package Driver;

import phonebook.*;

public class Driver
{
   public static void main(String[] args)
   {
      Trie trie = new Trie();

      String contacts[] = {"Mark Zuckerberg", "Will Smith", "William Gates", "Warren Buffet"};

      trie.insertIntoTrie(contacts);

      String query = "will";

      trie.displayContacts(query);

   }
}
