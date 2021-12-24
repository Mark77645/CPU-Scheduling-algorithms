import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Process p1=new Process();
        Process p2=new Process();
        Process p3=new Process();
        Process p4=new Process();
        Process p5=new Process();
        p1.setArrivalTime(0);
        p2.setArrivalTime(2);
        p3.setArrivalTime(4);
        p4.setArrivalTime(5);
        p5.setArrivalTime(6);
        p1.setBurstTime(7);
        p2.setBurstTime(4);
        p3.setBurstTime(1);
        p4.setBurstTime(4);
        p5.setBurstTime(12);
        p1.setName("P1");
        p2.setName("p2");
        p3.setName("P3");
        p4.setName("p4");
        p5.setName("P5");
        PreemptiveSJF sjf1=new PreemptiveSJF();
        NonPreemptiveSJF sjf=new NonPreemptiveSJF();
        ArrayList<Process> pp=new ArrayList<>();
        pp.add(p1);
        pp.add(p2);
        pp.add(p3);
        pp.add(p4);
        pp.add(p5);
        //sjf.NPSJF(pp);
        sjf1.SRTF(pp);
    }
}
