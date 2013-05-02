package br.ufc.quixada.es.util;
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
* Anotação para excluir campo do JSON gerado pelo Gson. 
*  
* @author David Buzatto 
*/  
@Retention( RetentionPolicy.RUNTIME )  
@Target( ElementType.FIELD )  
public @interface ExcluirJSON {  
      
    // apenas marcação de campo  
      
}  
