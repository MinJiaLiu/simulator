import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
 class Simulator{ 
     static PriorityQueue <EventB> Schedule= new PriorityQueue<EventB>(); 
     static StateB sysB;
     
    

    public static void simulate (
        double maxtime,
        double arrival_rate,
        double service_rate0,
        double service_rate1,
        double service_rate2, 
        double service_rate3_1,
        double service_rate3_2,
        double service_rate3_3,
        double p1,double p2,double p3,int k, double p01, double p02, double p3out, double p31, double p32)
 {
        // PriorityQueue <EventB> Schedule = new PriorityQueue<EventB>();
        StateB sysB = new StateB();
        
        double time= 0;
        double value2= ExpB.getExp(arrival_rate);

        EventB e1 = new EventB(time+value2,"Birth","Null");
        EventB e2= new EventB(time+value2,"Moniter","Null");
        Schedule.add(e1);
        Schedule.add(e2);
        
        while(time < maxtime){
        EventB next = Schedule.remove();
        time= next.time;
        next.function(maxtime,arrival_rate,service_rate0,service_rate1,service_rate2, service_rate3_1,
        service_rate3_2,service_rate3_3,p1,p2,p3,k,p01,p02,p3out,p31,p32,sysB);
        
        
      

        }
        // System.out.println(Double.toString(sysB.t_busy_time0) );
        // System.out.println(Double.toString(sysB.t_busy_time1) );
        // System.out.println(Double.toString(sysB.Qlength0) );
        // System.out.println(Double.toString(sysB.Qlength1) );
        // System.out.println(Double.toString(sysB.monitering_event) );
        // System.out.println(Double.toString(sysB.num_drop) );
        // System.out.println(Double.toString(sysB.completed_request) );
        // System.out.println(Double.toString(sysB.t_response_time) );

        System.out.println();
        double util0 = sysB.t_busy_time0 / maxtime;
        System.out.println("S0 UTIL:"+ " " + Double.toString(util0) );
        double qlen0 = sysB.Qlength0/ sysB.monitering_event;
        System.out.println("S0 QLEN:"+" "+ Double.toString(qlen0) );
        double Tresp0 = sysB.t_response_time0/sysB.completed_request0;
        System.out.println("S0 TRESP:" + " "+   Double.toString(Tresp0) );
      
         System.out.println();
        
        double util11 = sysB.t_busy_time11/ maxtime;
        System.out.println("S1,1 UTIL:"+ " " + Double.toString(util11) );
        double util12 = sysB.t_busy_time12/ maxtime;
        System.out.println("S1,2 UTIL:"+ " " + Double.toString(util12) );
        double qlen11 = sysB.Qlength1/ sysB.monitering_event;
        System.out.println("S1 QLEN:"+" "+ Double.toString(qlen11) );
        double Tresp11 = sysB.t_response_time1/ sysB.completed_request1;
        System.out.println("S1 TRESP:" + " "+   Double.toString(Tresp11) );
        
        
        // System.out.println();
        // double util12 = sysB.t_busy_time12/ maxtime;
        // System.out.println("S1,2 UTIL:"+ " " + Double.toString(util12) );
        // double qlen12 = sysB.Qlength1/ sysB.monitering_event;
        // System.out.println("S1,2 QLEN:"+" "+ Double.toString(qlen12) );
        // double Tresp12 = sysB.t_response_time12/sysB.completed_request12;
        // System.out.println("S1,2 TRESP:" + " "+   Double.toString(Tresp12) );

        System.out.println();
        double util2 = sysB.t_busy_time2/ maxtime;
        System.out.println("S2 UTIL:"+ " " + Double.toString(util2) );
        double qlen2 = sysB.Qlength2/ sysB.monitering_event;
        System.out.println("S2 QLEN:"+" "+ Double.toString(qlen2) );
        double Tresp2 = sysB.t_response_time2/sysB.completed_request2;
        System.out.println("S2 TRESP:" + " "+   Double.toString(Tresp2) );
        System.out.println("S2 DROPPED:" + " "+   Integer.toString(sysB.num_drop));

        System.out.println();
        double util3 = sysB.t_busy_time3/ maxtime;
        System.out.println("S3 UTIL:"+ " " + Double.toString(util3) );
        double qlen3 = sysB.Qlength3/ sysB.monitering_event;
        System.out.println("S3 QLEN:"+" "+ Double.toString(qlen3) );
        double Tresp3 = sysB.t_response_time3/sysB.completed_request3;
        System.out.println("S3 TRESP:" + " "+   Double.toString(Tresp3) );

        

        System.out.println();

        double qtotal = (sysB.Qlength0 + sysB.Qlength1 + sysB.Qlength2+sysB.Qlength3) / sysB.monitering_event;
        System.out.println("QTOT:"+" "+ Double.toString(qtotal) );
        // double Totalresp= sysB.t_response_time0  + sysB.t_response_time2 +sysB.t_response_time11 +sysB.t_response_time12 + sysB.t_response_time3;
    
        double total = sysB.t_response_timefinal/sysB.completed_system;
        
        System.out.println("TRESP:"+ " " + Double.toString(total));
        // System.out.println(sysB.Qlength1);
        // System.out.println(sysB.Qlength2);
        // System.out.println(sysB.Qlength3);
        // System.out.println(sysB.Qlength0);
        
        // System.out.println("TRESP:"+ " " + Double.toString(TTRESP));
        
        





    }
    public static void main (String[] args){
        double maxtime= Double.parseDouble(args[0]);
        double arrival_rate= Double.parseDouble(args[1]);
        double service_rate0 = Double.parseDouble(args[2]);
        double service_rate1= Double.parseDouble(args[3]);
        double service_rate2 = Double.parseDouble(args[4]);
        double service_rate3_1= Double.parseDouble(args[5]);
        double p1 = Double.parseDouble(args[6]);
        double service_rate3_2 =Double.parseDouble(args[7]);
        double p2 = Double.parseDouble(args[8]);
        double service_rate3_3 =Double.parseDouble(args[9]);
        double p3 = Double.parseDouble(args[10]);
        int k = Integer.parseInt(args[11]);
        double p01= Double.parseDouble(args[12]);
        double p02= Double.parseDouble(args[13]);
        double p3out= Double.parseDouble(args[14]);
        double p31= Double.parseDouble(args[15]);
        double p32= Double.parseDouble(args[16]);
        
        simulate(maxtime,arrival_rate,service_rate0,service_rate1,service_rate2, service_rate3_1,
        service_rate3_2,service_rate3_3,p1,p2,p3,k,p01,p02,p3out,p31,p32);
    }

    public static void add (EventB e){
        Schedule.add(e);
    }

    public static void remove(){
        Schedule.remove();
    }


    // public int getcounter(){
    //     return this.counter;
    // }
    
}