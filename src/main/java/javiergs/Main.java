package javiergs;

import javiergs.tulip.compiler.Lexer;
import javiergs.tulip.compiler.Token;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Main class to run the lexer
 *
 * @author javiergs
 * @version 1.0
 */
class Main {

  public static void main(String[] args) throws IOException {
    File file = new File("src/main/resources/easy.txt");

    // lexer
    Lexer lexer = new Lexer(file);
    lexer.run();
    Vector<Token> tokens = lexer.getTokens();
    for (Token token : tokens) {
      System.out.println("Token: " + token.getValue() + " Type: " + token.getType());
    }

    //parser
    Parser parser = new Parser(tokens);
    parser.parse();

  }

}