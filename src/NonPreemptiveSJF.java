import java.util.ArrayList;

public class NonPreemptiveSJF {
public ArrayList <Process> processes=new ArrayList<>();
public static int counter=0;
public void NPSJF(ArrayList <Process> processes) {
    sortArrivalTime(processes);
    System.out.println("Process P1 has been sent to cpu with waiting time: "+counter+" and Turnaround time: "+(processes.get(0).getBurstTime()+counter));
    processes.get(0).setWaitTime(counter); // sets process 1 to '0' wait time
    counter += processes.get(0).getBurstTime();
    processes.remove(0);
    sortBurstTime(processes);
    for (int i = 0; i < processes.size(); i++) { // p1 0 7  p2 2 4
        processes.get(i).setWaitTime(counter - processes.get(i).getArrivalTime());
        counter += processes.get(i).getBurstTime();
        System.out.println("process " + processes.get(i).getName() + " has been sent to cpu"+ " with waiting time: " + processes.get(i).getWaitTime() + " and Turnaround time: " +(processes.get(i).getBurstTime()+processes.get(i).getWaitTime()) );
    }
    System.out.println("Average waiting time is: "+ calculateAverageWaitingTime(processes));
    System.out.println("Average Turnaround time is: "+ calculateAverageTurnAroundTime(processes));
}
private void sortArrivalTime(ArrayList <Process> processes){ // selection sort
    int pos;
    Process temp;
    for (int i = 0; i < processes.size(); i++)
    {
        pos = i;
        for (int j = i+1; j <processes.size(); j++)
        {
            if (processes.get(j).getArrivalTime() < processes.get(pos).getArrivalTime())                  //find the index of the minimum element
            {
                pos = j;
            }
        }

        temp = processes.get(pos);            //swap the current element with the minimum element
        processes.set(pos, processes.get(i));
        processes.set(i, temp);
    }
}

    private void sortBurstTime(ArrayList <Process> processes){ // selection sort
        int position;
        Process temp;
        for (int i = 0; i < processes.size(); i++)
        {
            position = i;
            for (int j = i+1; j <processes.size(); j++)
            {
                if (processes.get(j).getBurstTime() < processes.get(position).getBurstTime())                  //find the index of the minimum element
                {
                    position = j;
                }
                else if (processes.get(j).getBurstTime() == processes.get(position).getBurstTime()){
                    if (processes.get(j).getArrivalTime() < processes.get(position).getArrivalTime()){
                        position = j;
                    }
                }
            }

            temp = processes.get(position);            //swap the current element with the minimum element
            processes.set(position, processes.get(i));
            processes.set(i, temp);
        }
    }

public int calculateAverageWaitingTime(ArrayList <Process> processes){
    int average=0;
    for (Process p: processes){
        average+=p.getWaitTime();
    }
    average=average/ (processes.size()+1);
    return average;
}
public int calculateAverageTurnAroundTime(ArrayList <Process> processes){
    int average=0;
    for (Process p: processes){
        average = average + p.getWaitTime() + p.getBurstTime();
    }
    average=average/ (processes.size());
    return average;
}
}
