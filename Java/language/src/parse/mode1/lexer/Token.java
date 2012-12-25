package parse.mode1.lexer;
/**
 * 词法单元类
 * @author qqbwww
 *
 */
public class Token {
	 //类型
	 public int type;
	 //文本
	 public String text;
	 
	 public Token(int type,String text){
		 this.type = type;
		 this.text = text;
	 }
	 
	 public String toString(){
		 String tname = ListLexer.tokenNames[type];
		 return "<'" + text + "'," + tname + ">";
	 }
}
