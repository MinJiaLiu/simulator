public class RequestB{
    public String  _id;
    public double arrival_time;
    public double start_time;
    public double finish_time;
    public double response_time;
    public double busy_time;
    public String  server_id;
    public double arrival_time0=0;

    public RequestB ( String  _id,
         double arrival_time,
         double start_time,
         double finish_time,
         double response_time,
         double busy_time,
         String server_id
        ){
            this._id= _id;
            this.server_id = server_id;
            this.arrival_time= arrival_time;
            this.start_time= start_time;
            this.finish_time=finish_time;
            this.response_time = response_time;
            this.busy_time= busy_time;
        


    }
    public void set_busy_time(double value){

        this.busy_time = value;
           }
       
       public double get_busy_time(){
   
           return this.busy_time;
               }
   
       public void set_response_time(double value){
   
           this.response_time = value;
           }
       
       public double get_response_time(){
   
           return this.response_time;
               }    
               
       public void set_arrival_time(double value){
   
           this.arrival_time= value;
           }
       
       public double get_arrival_time(){
   
           return this.arrival_time;
               }
       
       public void set_finish_time(double value){
   
           this.finish_time= value;
           }
       
       public double get_finish_time(){
   
        return this.finish_time;
               }
       
        public void set_start_time(double value){
   
        this.start_time= value;
                }
            
        public double get_start_time(){
        
        return this.start_time;

}
    public void set_id (String id){
   
     this._id= id;
            }
        
    public String get_id(){
    
    return this._id;

}
public String toString() 
    { 
        return _id; 
}
}