package parse.mode1.lexer;
/**
 * �ʷ���Ԫ��
 * @author qqbwww
 *
 */
public class Token {
	 //����
	 public int type;
	 //�ı�
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
