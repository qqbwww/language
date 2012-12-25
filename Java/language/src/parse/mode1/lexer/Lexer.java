package parse.mode1.lexer;
/**
 * ����ʷ�������
 */

public abstract class Lexer {
	//EOF���ļ��Ľ�β
	public static final char EOF = (char)-1;
	//EOF�����ͱ�־
	public static final int EOF_TYPE = 1;
	//�����ַ���
	String input;
	//��ǰ�����ַ����±�
	int p = 0;
	//��ǰ�ַ�
	char c;
	
	
	public Lexer(String input){
		this.input = input;
		//׼����ǰ���ַ�
		c = input.charAt(p);
	}
	/**
	 * ��ǰ�ƶ�һ���ַ�����������Ƿ����
	 */
	public void consume(){
		p++;
		if (p > input.length())
			c = EOF;
		else 
			c = input.charAt(p);
	}
	/**
	 * �����������ĵ�ǰ�ַ��Ƿ�ƥ��x
	 * @param x
	 */
	public void match(char x){
		if ( c == x )
			consume();
		else 
			throw new Error("expecting " + x + "; found " + c);
	}
	/**
	 * ��ȡ��һ���ʷ���Ԫ
	 * @return
	 */
	public abstract Token nextToken();
	/**
	 * ��ȡ�˷���Ԫ������
	 * @param tokenType  �ʷ���Ԫ����
	 * @return
	 */
	public abstract String getTokenName(int tokenType);
}
