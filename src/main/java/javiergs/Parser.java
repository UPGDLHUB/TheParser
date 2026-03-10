package javiergs;

import javiergs.tulip.compiler.Token;

import java.util.Vector;

public class Parser {

  private final Vector<Token> tokens;
  private int currentToken;

  public Parser(Vector<Token> tokens) {
    this.tokens = tokens;
    this.currentToken = 0;
  }

  private void print(String ruleName, int indent) {
    for (int i = 0; i < indent; i++) {
      System.out.print("-");
    }
    System.out.println(ruleName);
  }

  public void parse() {
    rule_clase();
    System.out.println("Parsing completed successfully.");
  }

  private void rule_clase() {
    print("rule_clase (root)", 0);
    // class declaration
    if (tokens.get(currentToken).getValue().equals("class")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected 'class' at the beginning of class declaration");
    }
    // class name
    if (tokens.get(currentToken).getType().equals("IDENTIFIER")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected class name");
    }
    // opening brace
    if (tokens.get(currentToken).getValue().equals("{")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected '{'");
    }
    // lines of code
    while (tokens.get(currentToken).getValue().equals("entero") ||
        // and eventually more types
        tokens.get(currentToken).getValue().equals("int")) {
      rule_declaration_variable();
    }
    // end of class
    if (tokens.get(currentToken).getValue().equals("}")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected '}'");
    }
  }

  private void rule_declaration_variable() {
    print("rule_declaration_variable", 1);
    // type
    if (tokens.get(currentToken).getValue().equals("entero") ||
        tokens.get(currentToken).getValue().equals("int")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected type");
    }
    // identifier
    if (tokens.get(currentToken).getType().equals("IDENTIFIER")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected identifier");
    }
    // optional initialization
    if (tokens.get(currentToken).getValue().equals("=")) {
      currentToken++;
      rule_expression();
    }
    // semicolon
    if (tokens.get(currentToken).getValue().equals(";")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected ';'");
    }
  }

  private void rule_expression() {
    print("rule_expression", 1);
    // this needs to grow, but for now it just support literals
    rule_literal();
  }

  private void rule_literal() {
    print("rule_literal", 1);
    if (tokens.get(currentToken).getType().equals("INTEGER") ||
        tokens.get(currentToken).getType().equals("CHARACTER") ||
        // more types of literals can be added here
        tokens.get(currentToken).getType().equals("STRING")) {
      currentToken++;
    } else {
      throw new RuntimeException("Expected literal");
    }
  }

}
