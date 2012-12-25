package parse.mode1.lexer;
/**
 * 抽象词法解析器
 */

public abstract class Lexer {
	//EOF，文件的结尾
	public static final char EOF = (char)-1;
	//EOF的类型标志
	public static final int EOF_TYPE = 1;
	//输入字符串
	String input;
	//当前输入字符的下标
	int p = 0;
	//当前字符
	char c;
	
	
	public Lexer(String input){
		this.input = input;
		//准备向前看字符
		c = input.charAt(p);
	}
	/**
	 * 向前移动一个字符；检测输入是否结束
	 */
	public void consume(){
		p++;
		if (p > input.length())
			c = EOF;
		else 
			c = input.charAt(p);
	}
	/**
	 * 检验输入流的当前字符是否匹配x
	 * @param x
	 */
	public void match(char x){
		if ( c == x )
			consume();
		else 
			throw new Error("expecting " + x + "; found " + c);
	}
	/**
	 * 获取下一个词法单元
	 * @return
	 */
	public abstract Token nextToken();
	/**
	 * 获取此法单元的名字
	 * @param tokenType  词法单元类型
	 * @return
	 */
	public abstract String getTokenName(int tokenType);
}
