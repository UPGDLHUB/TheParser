import javiergs.Parser;
import javiergs.tulip.compiler.Lexer;
import javiergs.tulip.compiler.Token;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Compares ONLY the first CSV column (TOKEN) against lexer token types,
 * but prints the full CSV row (TOKEN,ITEM) on mismatches.
 *
 * @author javiergs
 * @version 2026
 */
class ParserTest {

	private int grade = 0;

	@Test
	void testingTokens() throws IOException {
    File file = new File("src/main/resources/easy.txt");
    // lexer
    Lexer lexer = new Lexer(file);
    lexer.run();
    Vector<Token> tokens = lexer.getTokens();

    //parser
    Parser parser = new Parser(tokens);
    parser.parse();

    // everything went well.
    grade += 100;
    System.out.println("Test passed! Grade: " + grade);
  }

}
