import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellingChecker {

  private HashSet<String> dictionary;

  public SpellingChecker(String dictionaryFile) {
    dictionary = new HashSet<>();
    loadDictionary(dictionaryFile);
  }

  private void loadDictionary(String filename) {
    try (Scanner scanner = new Scanner(new File(filename))) {
      while (scanner.hasNextLine()) {
        dictionary.add(scanner.nextLine().trim().toLowerCase());
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error: Dictionary file not found!");
    }
  }

  public boolean isCorrectlySpelled(String word) {
    return dictionary.contains(word.toLowerCase());
  }

  public Set<String> suggestCorrections(String misspelledWord) {
    Set<String> suggestions = new HashSet<>();
    String lowerCaseWord = misspelledWord.toLowerCase();

    // Rule a: Add one character to the beginning
    for (char c = 'a'; c <= 'z'; c++) {
      String suggestion = c + lowerCaseWord;
      if (dictionary.contains(suggestion)) {
        suggestions.add(suggestion);
      }
    }

    // Rule b: Add one character to the end
    for (char c = 'a'; c <= 'z'; c++) {
      String suggestion = lowerCaseWord + c;
      if (dictionary.contains(suggestion)) {
        suggestions.add(suggestion);
      }
    }

    // Rule c: Remove one character from the beginning
    if (lowerCaseWord.length() > 1) {
      String suggestion = lowerCaseWord.substring(1);
      if (dictionary.contains(suggestion)) {
        suggestions.add(suggestion);
      }
    }

    // Rule d: Remove one character from the end
    if (lowerCaseWord.length() > 1) {
      String suggestion = lowerCaseWord.substring(0, lowerCaseWord.length() - 1);
      if (dictionary.contains(suggestion)) {
        suggestions.add(suggestion);
      }
    }

    // Rule e: Exchange adjacent characters
    for (int i = 0; i < lowerCaseWord.length() - 1; i++) {
      char[] chars = lowerCaseWord.toCharArray();
      char temp = chars[i];
      chars[i] = chars[i + 1];
      chars[i + 1] = temp;
      String suggestion = new String(chars);
      if (dictionary.contains(suggestion)) {
        suggestions.add(suggestion);
      }
    }

    return suggestions;
  }

  public static void main(String[] args) {
    SpellingChecker checker = new SpellingChecker("words.txt");
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.print("Enter a word (or type 'exit' to quit): ");
      String word = input.nextLine();

      if (word.equalsIgnoreCase("exit")) {
        break;
      }

      if (checker.isCorrectlySpelled(word)) {
        System.out.println("No mistakes found.");
      } else {
        Set<String> suggestions = checker.suggestCorrections(word);
        if (suggestions.isEmpty()) {
          System.out.println("No suggestions found.");
        } else {
          System.out.println("Suggestions: " + suggestions);
        }
      }
    }

    input.close();
  }
}

/*
 * Enter a word (or type 'exit' to quit): potato
 * No mistakes found.
 * Enter a word (or type 'exit' to quit): otato
 * Suggestions: [potato]
 * Enter a word (or type 'exit' to quit): tomato
 * No mistakes found.
 * Enter a word (or type 'exit' to quit): omato
 * Suggestions: [pomato, tomato]
 * Enter a word (or type 'exit' to quit): tomat
 * Suggestions: [toma, tomato]
 * Enter a word (or type 'exit' to quit): onion
 * No mistakes found.
 * Enter a word (or type 'exit' to quit): nion
 * Suggestions: [anion, onion, inion, ion, union, nino]
 * Enter a word (or type 'exit' to quit): hotel
 * No mistakes found.
 * Enter a word (or type 'exit' to quit): otel
 * Suggestions: [ote, botel, hotel, tel, motel]
 * Enter a word (or type 'exit' to quit): hotell
 * Suggestions: [hotel]
 * Enter a word (or type 'exit' to quit): hotle
 * Suggestions: [hotel]
 */