public class Custom{
    
    public static double getCustom(double [] outcomes, double [] probabilities){
        double u = Math.random();
        double result=0;
        double count = 0 ;
        
        for(int i = 0; i < probabilities.length; i++){
            if((probabilities[i]+count) > u && u >= count){
            
            result = outcomes[i];
            break;
            
            }
            else{
                count+=probabilities[i];
            }
        
           
        }
        return result;  
            
    }

    public static void main (String[] args){
        double [] outcomes = new double[] {Double.parseDouble(args[0]),Double.parseDouble(args[2]), Double.parseDouble(args[4]), Double.parseDouble(args[6]), Double.parseDouble(args[8])};
        double [] probabilities= new double[] {Double.parseDouble(args[1]),Double.parseDouble(args[3]), Double.parseDouble(args[5]), Double.parseDouble(args[7]), Double.parseDouble(args[9])};
        int N = Integer.parseInt(args[10]);
        double result;
        
        for(int i = 0; i < N; i++){
        
        result = getCustom(outcomes, probabilities);
        System.out.println(result);
          
        
    }

}
}

    
    
    
