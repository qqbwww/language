package parse.mode1.lexer;
/**
 * LL(1)递归下降的词法解析器
 * @author qqbwww
 *
 */
public class ListLexer extends Lexer{
	public static int NAME = 2;
	public static int COMMA = 3;
	public static int LBRACK = 4;
	public static int RBRACK = 5;
	/**
	 * 存放所有词法单元名称的字符串数组 
	 */
	public static String[] tokenNames ={"n/a","<EOF>","NAME","COMMA","LBRACK","RBRACK"};
	
	@Override
	public String getTokenName(int x){
		return tokenNames[x];
	}
	
	public ListLexer(String input) {
		super(input);
	}
	/**
	 * 如果是字母
	 * @return
	 */
	boolean isLETTER(){
		return c >='a' && c <='z' || c >='A' && c <='Z';
	}
	
	@Override
	public Token nextToken(){
		while( c != EOF){
			switch(c){
			case ' ':case '\t' :case '\n' : case '\r':WS();continue;
			case ',':consume();return new Token(COMMA,",");
			case '[':consume();return new Token(LBRACK,"[");
			case ']':consume();return new Token(RBRACK,"]");
			default:
				if(isLETTER()) return NAME();
				throw new Error("invalid character: " + c);
			}
		}
		return new Token(EOF_TYPE,"<EOF>");
	}
	
	/**
	 * 处理标识符
	 * @return
	 */
	Token NAME(){
		StringBuilder buf = new StringBuilder();
		do{
			buf.append(c);consume();
		}while(isLETTER());
		return new Token(NAME,buf.toString());
	}
	/**
	 * 直接跳过
	 */
	void WS(){
		while( c== ' ' || c=='\t' || c =='\n' || c=='\r'  )
			consume();
	}
	
}
