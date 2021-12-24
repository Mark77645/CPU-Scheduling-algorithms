import java.util.ArrayList;

public class PreemptiveSJF {
    /* algorithm
    process on
    another process comes in
    burst time of running process  = current burst time - arrival time of next process
    pick the smallest remaining  burst time
    run the job until another one comes in
    repeat
     */

    public static int currentTime = 0, index = 0 , time=0 ;

    public void SRTF(ArrayList<Process> processes) {
        sortArrivalTime(processes);
        int temp = 0;
        for (int i = 0; i < processes.size()-1; i++) {
            currentTime = processes.get(i).getArrivalTime();
            index = shortestBurstTime(processes); //returns the shortest burst time index in all processes at current time
            //That means this condition will be true only once for each process that will be sent to the cpu
            if (processes.get(index).getArrivalTime()==currentTime){
                processes.get(index).setWaitTime(time);
            }
            System.out.print("process " + processes.get(index).getName() + " entered the CPU for " + (processes.get(i+1).getArrivalTime() - processes.get(i).getArrivalTime()) + " unit Time");
            time+=processes.get(i+1).getArrivalTime() - processes.get(i).getArrivalTime();
            processes.get(index).setBurstTime(processes.get(index).getBurstTime() - (processes.get(i+1).getArrivalTime() - processes.get(i).getArrivalTime())); //Burst can be negative value
            System.out.println(", " + processes.get(index).getBurstTime() + " left");
            // Condition that checks whether a process has finished its burst or not, in order to set its turnaround time.
            if (processes.get(index).getBurstTime() <= 0) {
                processes.get(index).setTurnAroundTime(time - processes.get(index).getArrivalTime());
            }

        }
        for (int i =0; i< processes.size();i++) {
            if (processes.get(i).getBurstTime() <= 0) {
                System.out.println("process " + processes.get(i).getName() + " turnaround time is " + processes.get(i).getTurnAroundTime() + " and waiting time is "+processes.get(i).getWaitTime());
            }
        }
        sortBurstTime(processes);
        for (Process p : processes) {
            if (p.getBurstTime()<=0) {
                continue;
            }
            // Setting the wait time for all the processes that didn't enter the above for loop and has arrival time greater than zero.
            if (p.getWaitTime()==0 && p.getArrivalTime()!=0){
                p.setWaitTime(time);
            }
            // Like the "Non-Preemptive".
            time+=p.getBurstTime();
            p.setTurnAroundTime(time-p.getArrivalTime());
            System.out.println("process " + p.getName() + " entered the CPU for " + p.getBurstTime() + " unit with turnaround time= "+p.getTurnAroundTime()+" and waiting time= "+p.getWaitTime());

        }
        System.out.println("Average waiting time is: "+ calculateAverageWaitingTime(processes));
        System.out.println("Average Turnaround time is: "+ calculateAverageTurnAroundTime(processes));
    }

    private int shortestBurstTime(ArrayList<Process> processes) {
        int shortestBurstTime = processes.get(0).getBurstTime();
        int shortestBurstTimeIndex = 0;
        for (int i = 0; i < processes.size(); i++) {
            if ((processes.get(i).getBurstTime() <= shortestBurstTime) && processes.get(i).getBurstTime() != 0 && (processes.get(i).getArrivalTime() <= currentTime)) {
                shortestBurstTime = processes.get(i).getBurstTime();
                shortestBurstTimeIndex = i;
            }
        }
        return shortestBurstTimeIndex;
    }

    private void sortArrivalTime(ArrayList<Process> processes) { // selection sort
        int pos;
        Process temp;
        for (int i = 0; i < processes.size(); i++) {
            pos = i;
            for (int j = i; j < processes.size(); j++) {
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

    public void sortBurstTime(ArrayList<Process> processes) { // selection sort
        int position;
        Process temp;
        for (int i = 0; i < processes.size(); i++) {
            position = i;
            for (int j = i + 1; j < processes.size(); j++) {
                if (processes.get(j).getBurstTime() < processes.get(position).getBurstTime())                  //find the index of the minimum element
                {
                    position = j;
                } else if (processes.get(j).getBurstTime() == processes.get(position).getBurstTime()) {
                    if (processes.get(j).getArrivalTime() < processes.get(position).getArrivalTime()) {
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
        average=average/ (processes.size());
        return average;
    }
    public int calculateAverageTurnAroundTime(ArrayList <Process> processes){
        int average=0;
        for (Process p: processes){
            average = average + p.getTurnAroundTime();
        }
        average=average/ (processes.size());
        return average;
    }
}