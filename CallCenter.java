// 1. Call Class

class Call {
    int id;
    int rank; // 0 = respondent, 1 = manager, 2 = director
    String status;

    public Call(int id, int rank) {
        this.id = id;
        this.rank = rank;
        this.status = "NEW";
    }
}

// 2. Abstract Employee

abstract class Employee {
    protected String name;
    protected boolean free = true;
    protected int rank;

    protected Employee nextLevel;

    public Employee(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public void setNextLevel(Employee nextLevel) {
        this.nextLevel = nextLevel;
    }

    public boolean isFree() {
        return free;
    }

    public void receiveCall(Call call) {
        free = false;
        call.status = "IN_PROGRESS";
        System.out.println(name + " is handling call " + call.id);
    }

    public void finishCall(Call call) {
        free = true;
        call.status = "DONE";
        System.out.println(name + " finished call " + call.id);
    }

    public void handleCall(Call call) {

        if (this.free && this.rank >= call.rank) {
            receiveCall(call);
        } else if (nextLevel != null) {
            nextLevel.handleCall(call);
        } else {
            System.out.println("Call " + call.id + " is waiting (no available employee)");
        }
    }
}

// 3. Concrete Classes

class Respondent extends Employee {
    public Respondent(String name) {
        super(name, 0);
    }
}

class Manager extends Employee {
    public Manager(String name) {
        super(name, 1);
    }
}

class Director extends Employee {
    public Director(String name) {
        super(name, 2);
    }
}

// 4. Call Center Class

import java.util.*;

class CallCenter {

    private List<Respondent> respondents = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private List<Director> directors = new ArrayList<>();

    private Employee chainHead;

    public CallCenter() {
        setupEmployees();
        setupChain();
    }

    private void setupEmployees() {
        respondents.add(new Respondent("R1"));
        respondents.add(new Respondent("R2"));

        managers.add(new Manager("M1"));
        directors.add(new Director("D1"));
    }

    private void setupChain() {

        // Simple chain: Respondent → Manager → Director
        Employee r = respondents.get(0);
        Employee m = managers.get(0);
        Employee d = directors.get(0);

        r.setNextLevel(m);
        m.setNextLevel(d);

        chainHead = r;
    }

    public void dispatchCall(Call call) {
        chainHead.handleCall(call);
    }
}


// 5. Main Method (Test)

public class Main {
    public static void main(String[] args) {

        CallCenter callCenter = new CallCenter();

        Call c1 = new Call(101, 0);
        Call c2 = new Call(102, 1);
        Call c3 = new Call(103, 2);

        callCenter.dispatchCall(c1);
        callCenter.dispatchCall(c2);
        callCenter.dispatchCall(c3);
    }
}

