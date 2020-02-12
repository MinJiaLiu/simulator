import java.util.Random; 
import java.lang.Math;

public class ExpB{
    
    public static double getExp(double lambda){
        
        double u = Math.random();
        double result = Math.log(1-u)/(lambda*-1);
         
        return result;
       
   
        
    }
    
    

}
