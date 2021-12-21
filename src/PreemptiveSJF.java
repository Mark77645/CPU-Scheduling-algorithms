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

    public static int currentTime = 0, index = 0, latestArrival = 0;

    public ArrayList<Process> processes = new ArrayList<>();

    public void SRTF(ArrayList<Process> processes) {
        sortArrivalTime(processes);
        int h = processes.size()-1;
        for (int i = 0; i < h; i++) {
            currentTime = processes.get(i).getArrivalTime();
            index = shortestBurstTime(processes); //returns the shortest burst time index in all processes at current time
            System.out.print("process " + processes.get(index).getName() + " entered the CPU for " + (processes.get(i+1).getArrivalTime() - processes.get(i).getArrivalTime()) + " unit Time");
            processes.get(index).setBurstTime(processes.get(index).getBurstTime() - (processes.get(i+1).getArrivalTime() - processes.get(i).getArrivalTime()));
            System.out.println(", " + processes.get(index).getBurstTime() + " left");
        }
        for (int i =0; i< processes.size();i++){
            if (processes.get(i).getBurstTime() <= 0) {
                processes.remove(i);
            }
        }

        sortBurstTime(processes);
        for (Process p : processes) {
            System.out.println("process " + p.getName() + " entered the CPU for " + p.getBurstTime() + " unit");
        }

    }

    private int shortestBurstTime(ArrayList<Process> processes) {
        int shortestBurstTime = processes.get(0).getBurstTime();
        int shortestBurstTimeIndex = 0;
        for (int i = 0; i < processes.size(); i++) {
            if ((processes.get(i).getBurstTime() <= shortestBurstTime) && processes.get(i).getBurstTime() != 0&& (processes.get(i).getArrivalTime() <= currentTime)) {
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


}