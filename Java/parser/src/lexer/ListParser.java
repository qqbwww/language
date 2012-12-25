package lexer;

/**
 语法解析器
 */
public class ListParser extends Parser{

    public ListParser(Lexer lexer){
        surper(this) ;
    }

    public void list(){
        match((char) ListLexer.LBRACK);
        elements();
        match((char) ListLexer.RBRACK);
    }

    void elements(){
        element();
        while(lookahead.type == ListLexer.COMMA){
            match((char) ListLexer.COMMA);
            element();
        }
    }

    void element(){
        if(lookahead.type == ListLexer.NAME)
            match((char) ListLexer.NAME);
        else if(lookahead.type == ListLexer.LBRACK)
            list();
        else throw new Error("expecting name or list;found "+ lookahead);
    }
}
