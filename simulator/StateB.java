import java.util.LinkedList;

public class StateB{
    public LinkedList<RequestB> S0=new LinkedList<RequestB>();
    public LinkedList<RequestB> S1=new LinkedList<RequestB>();
    public LinkedList<RequestB> S2=new LinkedList<RequestB>();
    public LinkedList<RequestB> S3=new LinkedList<RequestB>();
    public double Qlength0= 0;
    public double Qlength1= 0;
    public double Qlength2= 0;
    public double Qlength3= 0;
    public double counter =0;
    public double t_busy_time0=0;
    public double t_busy_time11=0;
    public double t_busy_time12=0;
    public double t_busy_time2=0;
    public double t_busy_time3=0;
    public double t_busy_timefinal=0;
    public double t_response_time0=0; 
    public double t_response_time1=0;
    public double t_response_time2=0;
    public double t_response_time3=0;
    public double t_response_timefinal=0;
    
    public double  completed_request0=0;
    public double  completed_request1=0;
    public double  completed_request2=0;
    public double  completed_request3=0;
    public double  completed_requestfinal=0;
    public double completed_system = 0;
    public double  monitering_event=0;
    public int num_drop = 0;
    public Boolean processor1 = false;
    public Boolean processor2= false;


    // public State (
    //     double t_busy_time,
    //     double t_response_time,
    //     int  completed_request,
    //     int monitering_event) {
       
    //     this.Queue = new LinkedList<Request>();
    //     this.t_busy_time = t_busy_time;
    //     this.t_response_time=t_response_time;
    //     this.completed_request= completed_request;
    //     this.monitering_event=monitering_event;
    //     }

    // public void set_t_busy_time(double value){

    //  this.t_busy_time = value;
    //     }
    
    // public double get_t_busy_time(){

    //     return this.t_busy_time;
    //         }
    
    
     

    
}

    
