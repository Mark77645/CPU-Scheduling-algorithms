import java.util.ArrayList;
import java.util.Scanner;

public class Priority_Scheduling {

    Scanner scanner = new Scanner(System.in);
    int processes_counter = scanner.nextInt();
    void Priority_Schedule(){
        System.out.println("Enter the number of process: ");
        int processes_counter = scanner.nextInt();
        ArrayList<Process> processes = new ArrayList<>();
        int Burst_time[] = new int[processes_counter];
        int Arrival_time[] = new int[processes_counter];
        for (int i = 0; i < processes_counter; i++) {
            System.out.println("Enter the Burst time for Process number->" + (i + 1) + ": ");
            Process process = new Process();
            Burst_time[i] = scanner.nextInt();
            process.setBurstTime(Burst_time[i]);
            System.out.println("Enter the Arrival time for Process number->" + (i + 1) + ": ");
            Arrival_time[i] = scanner.nextInt();
            process.setArrivalTime(Arrival_time[i]);
            processes.add(process);
        }
//        ArrayList<Process> original_Processes = processes;
        int originalPriority[] = new int[processes_counter];

        for (int i = 0; i < processes_counter; i++) {
            System.out.println("Enter the Priority value for Process number->" + (i + 1) + ": ");
            originalPriority[i] = scanner.nextInt();
            processes.get(i).setPriority(originalPriority[i]);
            //lower value is higher priority
        }

        int Sorted_Priority[] = new int[processes_counter];
        for (int i = 0; i < processes_counter; i++) {
            Sorted_Priority[i] = originalPriority[i];
        }

        for (int i = 0; i < processes_counter; i++) {
            for (int j = 0; j < processes_counter - 1; j++) {
                if (originalPriority[j] > originalPriority[j + 1]) {
                    int tem = originalPriority[j];
                    originalPriority[j] = originalPriority[j + 1];
                    originalPriority[j + 1] = tem;
                }
            }
        }

        int wait = 0;
        int wait_time[] = new int[processes_counter];
        float total = 0;

        System.out.println("Process\t\t\t Burst time\t\t\t waiting time");

        for (int i = 0; i < processes_counter; i++) {
            for (int j = 0; j < processes_counter; j++) {
                if (originalPriority[i] == Sorted_Priority[j]) {
                    System.out.println("process" + (j + 1) + "\t\t" + Burst_time[j] + "\t\t\t" + wait);
                    wait_time[0] = wait;
                    total += wait;
                    wait += Burst_time[j];
                }
            }
        }

    }
}
