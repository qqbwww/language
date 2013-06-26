package ast;

import java.util.ArrayList;
import java.util.List;

public class AST {
    Token token;      //节点源自哪个词法单元
    List<AST> children ;    //操作对象
    public AST(Token token){
        this.token = token;
    }
    
    public void addChild(AST t){
        if( children == null)
            children = new ArrayList<AST>();
        children.add(t);
    }
}
