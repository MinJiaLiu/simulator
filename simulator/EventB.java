

public class EventB implements Comparable<EventB> {
    public double time;
    public String type;
    public String server;

    public EventB(double time,String type, String server){
        this.time = time;
        this.type = type;
        this.server = server; 
        
    }

    public String gettype (){
        return this.type;
    }

    public void function (
        double maxtime,
        double arrival_rate,
        double service_rate0,
        double service_rate1,
        double service_rate2, 
        double service_rate3_1,
        double service_rate3_2,
        double service_rate3_3,
        double p1,double p2,double p3,int k, double p01, double p02, double p3out, double p31, double p32, StateB sysB){
        
        String condition= this.type;
        switch(condition){
            case  "Birth":
                if(time > maxtime) {
                    break;
                }
                
                double value2= ExpB.getExp(arrival_rate);
                int namesub= (int)sysB.counter;
                String name = "R" + Integer.toString(namesub);
                RequestB r = new RequestB(name,time,0,0,0,0,"0");
                r.arrival_time0= time;
                sysB.S0.add(r);
                // String out1= r._id;
               
                
                System.out.println(r + " "+ "ARR:" +" "+ time );
                
                sysB.counter++;
                

             
                if (sysB.S0.size()== 1) {
                    r.arrival_time= time;
                    // String out2 = toString(r);
                    // s
                    double value1= ExpB.getExp(1/service_rate0);
                    EventB death= new EventB(time+value1,"Death","0");
                    Simulator.Schedule.add(death);
                    r.start_time= time;
                   
                    System.out.println(r + " "+ "START S0:" +" "+ time );
                }

                
                EventB newborn= new EventB (time+value2,"Birth","null");
                Simulator.Schedule.add(newborn);
                
                break;
            
            case "Death":
                if(time > maxtime){
                    break;       
                }
            
              
              
              
              
                if (this.server == "0") {
                    double value1= ExpB.getExp(1/service_rate0);
                    // String out3= complete._id;
                   
                    RequestB complete = sysB.S0.removeFirst();
                    complete.finish_time = time;
                    
                
                    String donet= Double.toString(complete.finish_time);
                    complete.response_time= complete.finish_time - complete.arrival_time;
                    sysB.t_response_time0 += complete.response_time;
                    sysB.completed_request0 += 1;
                    
                    sysB.t_busy_time0 = sysB.t_busy_time0 + ( complete.finish_time - complete.start_time);
                    
                    

                    System.out.println(complete+ " "+ "DONE S0:" +" " + donet );

                 
                    
                    
                    double result1or2=Custom.getCustom(new double[] {1.0,2.0}, new double[] {p01,p02});

                    if(result1or2 == 1.0) {

                        
                        
                                System.out.println(complete+ " "+ "FROM S0 TO S1:" +" " + donet );
                                sysB.S1.add(complete);
                                complete.arrival_time= time;
                                
                            
                                if (sysB.S1.size() == 1) {
                                   
                                    double gotoproc=Custom.getCustom(new double[] {1.0,2.0}, new double[] {0.5,0.5});
                                
                                    if (gotoproc== 1.0) {
                                        sysB.processor1 = true; 
                                        double value_s1= ExpB.getExp(1/service_rate1);
                                        EventB death= new EventB(time+value_s1,"Death","1");
                                        Simulator.Schedule.add(death);
                                        complete.server_id= "1";
                                        complete.start_time= time;
                                        System.out.println(complete + " "+ "START S1,1:" +" "+ donet );
                            
                                    
                                    }    
                                
                                
                                else {
                                    sysB.processor2 = true;  
                                    double value_s1= ExpB.getExp(1/service_rate1);
                                    EventB death= new EventB(time+value_s1,"Death","1");
                                    Simulator.Schedule.add(death);
                                    complete.server_id= "2";
                                    complete.start_time= time;
                                    System.out.println(complete + " "+ "START S1,2:" +" "+ donet );
                            
                            
                                }
                            }
                            
                            if (sysB.S1.size()==2) {
                            
                                    if(sysB.processor1 == false) {
                                        sysB.processor1 = true;  
                                        double value_s1= ExpB.getExp(1/service_rate1);
                                        EventB death= new EventB(time+value_s1,"Death","1");
                                        Simulator.Schedule.add(death);
                                        
                                        complete.start_time= time;
                                        complete.server_id= "1";
                    
                                        System.out.println(complete + " "+ "START S1,1:" +" "+ donet );
                                    }
                                
                                    else{
                                        if(sysB.processor2 == false){
                                            sysB.processor2= true;  
                                            double value_s1= ExpB.getExp(1/service_rate1);
                                            EventB death= new EventB(time+value_s1,"Death","1");
                                            Simulator.Schedule.add(death);
                                            complete.server_id= "2";
                                            complete.start_time= time;
                                    
                                            System.out.println(complete + " "+ "START S1,2:" +" "+ donet );
                                        }
                                    }
                                }
                                    
                                }
                            
                            
                            
                            
                       if(result1or2 == 2.0){

                        if (sysB.S2.size() >= k){
                            
                                System.out.println(complete + " "+ "DROP S2:" +" "+ donet);
                                sysB.num_drop = sysB.num_drop + 1;
                        }
                        else{
                                sysB.S2.add(complete);
                                System.out.println(complete+ " "+ "FROM S0 TO S2:" +" " + donet );
                                complete.arrival_time= time;
                                if (sysB.S2.size()== 1){
                                    
                                    double value_s2= ExpB.getExp(1/service_rate2);
                                    EventB death= new EventB(time+value_s2,"Death","2");
                                    Simulator.Schedule.add(death);
                                    complete.start_time= time;
                                
                                    System.out.println(complete + " "+ "START S2:" +" "+ time );
                                }
                        }
                    }
                    if (sysB.S0.size() > 0){
                        RequestB new_request = sysB.S0.peek();
                        new_request.start_time = time;
                        EventB deathevent = new EventB(time+value1,"Death","0");
                        Simulator.Schedule.add(deathevent);
                        System.out.println(new_request+ " "+ "START S0:" +" "+ time );
                    }
                    break;
                }
            

                if (this.server == "1"){
                   

                        RequestB complete = sysB.S1.removeFirst();
                       
                        sysB.completed_request1++;
                        complete.finish_time = time;
                        complete.response_time= complete.finish_time - complete.arrival_time;
                        String donet= Double.toString(complete.finish_time);
                        
                        sysB.t_response_time1 += complete.response_time;
                    
                if (complete.server_id== "1"){
                     
                    
                    System.out.println(complete+ " "+ "DONE 1,1:" +" " + donet );
                    sysB.processor1 = false;

                    
                    sysB.t_busy_time11 = sysB.t_busy_time11+  ( complete.finish_time - complete.start_time);
                     
                  
                        

                }
                if (complete.server_id== "2"){
                    
                    
                    System.out.println(complete+ " "+ "DONE 1,2:" +" " + donet );
                    sysB.processor2 = false;
                        
                    sysB.t_busy_time12 = sysB.t_busy_time12 + ( complete.finish_time - complete.start_time);
                
                }

                System.out.println(complete+ " "+ "FROM S1 TO S3:" +" " + donet );
               
                double value_S3 = Custom.getCustom(new double[] {service_rate3_1,service_rate3_2,service_rate3_3}, new double[] {p1,p2,p3});
                complete.arrival_time = time;
                sysB.S3.add(complete);
                if(sysB.S3.size()== 1){
                            
                                
                EventB death= new EventB(time+value_S3,"Death","3");
                Simulator.Schedule.add(death);
                complete.start_time= time;
                        
                System.out.println(complete + " "+ "START S3:" +" "+ donet );
                }
            
                
                if (sysB.S1.size() > 0){
				if (sysB.S1.peek().server_id == "0" || sysB.S1.size() > 1)
				{
                    
					
					RequestB new_req = sysB.S1.peek();

					if ((new_req.server_id == "0") == false){	
                       
                        
                        RequestB temp = sysB.S1.removeFirst();
						RequestB new_request = sysB.S1.peek();	
						sysB.S1.addFirst(temp);

                        
                        new_request.start_time = time;

						if (complete.server_id == "1")
						{
                            
                            new_request.server_id = "1"; 
                            sysB.processor1= true; 
							System.out.println( new_request+  " START S1,1: " + time);
						}
						else
						{
                          
                            new_request.server_id = "2"; 
                            sysB.processor2 = true;
							System.out.println(new_request + " START S1,2: " + time);
						}
                    }
                    
					else	
					{
                       
						new_req.start_time = time;

						double choose1or2 = Custom.getCustom(new double[] {1.0,2.0}, new double[] {0.5,0.5});
						if (choose1or2 == 1.0)
						{
							new_req.server_id = "1"; 
                            sysB.processor1= true; 
							System.out.println( new_req+  " START S1,1: " + time);
                        
					   
                        }
						else
						{
                            new_req.server_id = "2"; 
                            sysB.processor2 = true;
                            System.out.println(new_req+ " START S1,2: " + time);
                            
					    
						}
					}
                    double value_s1 = Exp.getExp(1/service_rate1);
                    EventB deathEvent = new EventB(time + value_s1, "Death", "1");
                    Simulator.Schedule.add(deathEvent);
		
				}
                }
                complete.server_id = "0";
                break;
            }
            
           
            if(this.server == "2"){
                RequestB complete = sysB.S2.removeFirst();
                double value_S2= ExpB.getExp(1/service_rate2);
                complete.finish_time = time;
                String donet= Double.toString(complete.finish_time);
            
                complete.response_time= complete.finish_time - complete.arrival_time;
                sysB.t_response_time2+= complete.response_time;
                sysB.completed_request2 += 1;
             
                sysB.t_busy_time2 += ( complete.finish_time - complete.start_time);
                
                
                System.out.println(complete+ " "+ "DONE S2:" +" " + donet );
                System.out.println(complete+ " "+ "FROM S2 TO S3:" +" " + donet );
                double value_S3 = Custom.getCustom(new double[] {service_rate3_1,service_rate3_2,service_rate3_3}, new double[] {p1,p2,p3});
            
                complete.arrival_time = time;
                sysB.S3.add(complete);
                if(sysB.S3.size()== 1){
                
                    complete.start_time= time;
                    EventB death= new EventB(time+value_S3,"Death","3");
                    Simulator.Schedule.add(death);
                    
            
                    System.out.println(complete + " "+ "START S3:" +" "+ donet );
                }
                if (sysB.S2.size() > 0){
                    RequestB new_request = sysB.S2.peek();
                    new_request.start_time = time;
                    EventB deathevent = new EventB(time+value_S2,"Death","2");
                    Simulator.Schedule.add(deathevent);
                    System.out.println(new_request+ " "+ "START S2:" +" "+ time );
                }
                
                
            break;
            }
            
        
        
        if(this.server == "3"){
            RequestB complete = sysB.S3.removeFirst();
            complete.finish_time = time;
            
            
            String donet= Double.toString(complete.finish_time);
            complete.response_time= complete.finish_time - complete.arrival_time;
            sysB.t_response_time3 += complete.response_time;
            sysB.completed_request3 += 1;
            
            sysB.t_busy_time3 = sysB.t_busy_time3+  ( complete.finish_time - complete.start_time);
            
            
            System.out.println(complete+ " "+ "DONE S3:" +" " + donet );
            double result1or2=Custom.getCustom(new double[] {1.0,2.0,3.0}, new double[] {p31,p32,p3out});

            if(result1or2 == 1.0) {
                        System.out.println(complete+ " "+ "FROM S3 TO S1:" +" " + donet );
                        sysB.S1.add(complete);
                        complete.arrival_time= time;
                    
                        if (sysB.S1.size() == 1) {
                            double gotoproc=Custom.getCustom(new double[] {1.0,2.0}, new double[] {0.5,0.5});
                        
                            if (gotoproc== 1.0) {
                                sysB.processor1 = true; 
                                double value_s1= ExpB.getExp(1/service_rate1);
                                EventB death= new EventB(time+value_s1,"Death","1");
                                Simulator.Schedule.add(death);
                                complete.server_id= "1";
                                complete.start_time= time;
                                System.out.println(complete + " "+ "START S1,1:" +" "+ donet );
                    
                            }
                        
                        
                        else {
                            sysB.processor2 = true;  
                            double value_s1= ExpB.getExp(1/service_rate1);
                            EventB death= new EventB(time+value_s1,"Death","1");
                            Simulator.Schedule.add(death);
                            complete.server_id= "2";
                            complete.start_time= time;
                            System.out.println(complete + " "+ "START S1,2:" +" "+ donet );
                        }
                    
                        }
                    
                        if (sysB.S1.size()==2) {
                            if(sysB.processor1 == false) {
                                sysB.processor1 = true;  
                                double value_s1= ExpB.getExp(1/service_rate1);
                                EventB death= new EventB(time+value_s1,"Death","1");
                                Simulator.Schedule.add(death);
                                
                                complete.start_time= time;
                                complete.server_id= "1";
            
                                System.out.println(complete + " "+ "START S1,1:" +" "+ donet );
                            }
                            else{
                                if(sysB.processor2 == false){
                                    sysB.processor2= true;  
                                    double value_s1= ExpB.getExp(1/service_rate1);
                                    EventB death= new EventB(time+value_s1,"Death","1");
                                    Simulator.Schedule.add(death);
                                    complete.server_id= "2";
                                    complete.start_time= time;
                            
                                    System.out.println(complete + " "+ "START S1,2:" +" "+ donet );
                                }
                            }
                            
                            
                        }
                    }
                    
                if(result1or2 == 2.0){

                if (sysB.S2.size() >= k){
                    
                        System.out.println(complete + " "+ "DROP S2:" +" "+ donet);
                        sysB.num_drop = sysB.num_drop+1;
                }
                else{
                        sysB.S2.add(complete);
                        System.out.println(complete+ " "+ "FROM S3 TO S2:" +" " + donet );
                        complete.arrival_time= time;
                        if (sysB.S2.size()== 1){
                            
                            double value_s2= ExpB.getExp(1/service_rate2);
                            EventB death= new EventB(time+value_s2,"Death","2");
                            Simulator.Schedule.add(death);
                            complete.start_time= time;
                        
                            System.out.println(complete + " "+ "START S2:" +" "+ time );
                        }
                }
            }
            if (result1or2 == 3.0){
                sysB.t_response_timefinal+= (time- complete.arrival_time0);
                sysB.completed_system++;
                System.out.println(complete + " "+ "FROM S3 TO OUT:" +" "+ time );
            }
            if (sysB.S3.size() > 0){
                double value_S3 = Custom.getCustom(new double[] {service_rate3_1,service_rate3_2,service_rate3_3}, new double[] {p1,p2,p3});
                RequestB new_request = sysB.S3.peek();
                new_request.start_time = time;
                EventB deathevent = new EventB(time+value_S3,"Death","3");
                Simulator.Schedule.add(deathevent);
                System.out.println(new_request+ " "+ "START S3:" +" "+ time );
            }
            break;
        }
        

            
            
         
            
            

                
            
            case  "Moniter":
                    double value3= ExpB.getExp(arrival_rate);
                  
                    sysB.Qlength0 = sysB.Qlength0 + (double)sysB.S0.size();
                    sysB.Qlength1 = sysB.Qlength1 + (double)sysB.S1.size();
                    sysB.Qlength2 = sysB.Qlength2+ (double)sysB.S2.size();
                    sysB.Qlength3 = sysB.Qlength3 + (double)sysB.S3.size();
                    sysB.monitering_event= sysB.monitering_event+ 1.0;
                    EventB newmon = new EventB(time+value3,"Moniter","null");
                    Simulator.Schedule.add(newmon);

                    break;
            
        }
    }

                

                


                
            
                

               
    

    public double gettime(){
        return this.time;
    }
    
// got help from https://www.callicoder.com/java-priority-queue/ for the compare to. 
    public int compareTo(EventB event) {
        if(this.gettime() > event.gettime()) {
            return 1;
        } else if (this.gettime() < event.gettime()) {
            return -1;
        } else {
            return 0;
        }
    }
}
    



