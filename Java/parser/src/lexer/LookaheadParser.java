package lexer;

/**
 * Created with IntelliJ IDEA.
 * User: qqbwww
 * Date: 12-11-6
 * Time: 上午12:06
 * To change this template use File | Settings | File Templates.
 */
public class LookaheadParser extends Parser {
    void element(){
        if(LA(1) == LookaheadLexer.NAME && LA(2) == LookaheadLexer.EQUALS){
            match(LookaheadLexer.NAME);
            match(LookaheadLexer.EQUALS);
            match(LookaheadLexer.NAME);
        }
        else if(LA(1) == LookaheadLexer.NAME)
            match(LookaheadLexer.NAME);
        else if(LA(1) == LookaheadLexer.LBRACK)
            list();
        else
            throw new Error("excepting name or list;found "+ LT(1));
    }
}
