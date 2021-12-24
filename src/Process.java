public class Process {
    private String name;
    private String color;
    private int burstTime;
    private int arrivalTime;
    private int priority;
    private int waitTime; // Start - Arrival OR Turnaround - Burst
    private int turnAroundTime; // Waiting + Burst OR Finish - Arrival


    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int startTime) {
        this.waitTime = startTime;
    }
}
