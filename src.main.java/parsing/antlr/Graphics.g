grammar Graphics;

file : command+ ;
command : 'line' 'from' point 'to' point;
point: INT ',' INT; 

INT : '0'..'9'+;  //匹配一个或多个数字的词法规则

/** 跳过空白符 */
WS:(' ' | '\t' | '\r' | '\n'){ skip(); };