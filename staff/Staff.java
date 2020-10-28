package staff;
import leave.Leave;
import leave.Leave.LeaveStatus;
import leave.Leave.LeaveType;

public abstract class Staff {
    
    private String id;
    private String name;
    public Leave leave;
    private Manager manager;

    public Staff(String id, String name) {
        this.id = id;
        this.name = name;
        this.leave = new Leave();
        // ToDo: init with manager
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    // check what's your leave status
    public String checkLeaveStatus(String type) {
        LeaveStatus status;
        switch (type) {
            case "annual": {
                status = leave.leaveStatusMap.get(LeaveType.ANNUAL_LEAVE);
                break;
            }
            case "sick": {
                status = leave.leaveStatusMap.get(LeaveType.SICK_LEAVE);
                break;
            }
            default: {
                status = leave.leaveStatusMap.get(LeaveType.OTHER_LEAVE);
                break;
            }
        }
        return status.getType();
    }

    // check how much balance do you have
    public double checkLeaveBalance(String type) {
        double balance;
        switch (type) {
            case "annual": {
                balance = leave.leaveBalanceMap.get(LeaveType.ANNUAL_LEAVE);
                break;
            }
            case "sick": {
                balance = leave.leaveBalanceMap.get(LeaveType.SICK_LEAVE);
                break;
            }
            default: {
                balance = leave.leaveBalanceMap.get(LeaveType.OTHER_LEAVE);
                break;
            }
        }
        return balance;
    }

    // request leave
    public boolean requestLeave(String type, double hours) {
        boolean status = false;
        if (hours <= 0) {
            return status;
        }
        switch (type) {
            case "annual": {
                status = leave.requestLeave(LeaveType.ANNUAL_LEAVE, hours);
                break;
            }
            case "sick": {
                status = leave.requestLeave(LeaveType.SICK_LEAVE, hours);
                break;
            }
            default: {
                status = leave.requestLeave(LeaveType.OTHER_LEAVE, hours);
            }
        }

        notifyManager();
        return status;
    };

    // work to earn hours
    public void work(double hours) {
        leave.earnBalance(hours/10.0);
    }

    public void notifyManager() {
        // ToDo: notify manager via email / text
    }

    public void report() {
        // ToDo: report your manager here
    }

}
