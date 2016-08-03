package core;

import core.model.lex.Kind;
import core.model.lex.Token;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Lexer {

    private static final String DECLARE = "declare";
    private static final String INTEGER = "integer";
    public static final String READ = "read";
    public static final String DISPLAY = "display";

    public Queue<Token> lex(String input) {
        Queue<Token> tokens = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            Token token = null;
            switch (input.charAt(i)) {
                case '=': {
                    token = new Token(Kind.EQUAL, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '+': {
                    token = new Token(Kind.PLUS, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '-': {
                    token = new Token(Kind.MINUS, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '*': {
                    token = new Token(Kind.MULTIPLY, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '/': {
                    token = new Token(Kind.DIVIDE, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '%': {
                    token = new Token(Kind.REMAINDER, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                }
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    token = new Token(Kind.NUMBER, String.valueOf(input.charAt(i)));
                    tokens.add(token);
                    break;
                default: {
                    if (isIdentifierFirst(input.charAt(i))) {
                        StringBuilder identifier = new StringBuilder("");
                        for (; i < input.length() && isIdentifier(input.charAt(i)); i++) {
                            identifier.append(input.charAt(i));
                        }
                        i--;
                        if (Objects.equals(identifier.toString(), DECLARE)) {
                            token = new Token(Kind.DECLARE, identifier.toString());
                            tokens.add(token);
                        } else if (Objects.equals(identifier.toString(), INTEGER)) {
                            token = new Token(Kind.INTEGER, identifier.toString());
                            tokens.add(token);
                        } else if (Objects.equals(identifier.toString(), READ)) {
                            token = new Token(Kind.READ_OPERATION, identifier.toString());
                            tokens.add(token);
                        } else if (Objects.equals(identifier.toString(), DISPLAY)) {
                            token = new Token(Kind.DISPLAY_OPERATION, identifier.toString());
                            tokens.add(token);
                        } else {
                            token = new Token(Kind.IDENTIFIER, identifier.toString());
                            tokens.add(token);
                        }
                    }
                }
            }
        }
        return tokens;
    }

    private boolean isIdentifier(char symbol) {
        return Character.isLetterOrDigit(symbol);
    }

    private boolean isIdentifierFirst(char symbol) {
        return Character.isLetter(symbol);
    }

}
