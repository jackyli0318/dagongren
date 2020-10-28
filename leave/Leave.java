package leave;

import java.util.HashMap;
import java.util.Map;


public class Leave {
    
    public enum LeaveType {
        ANNUAL_LEAVE("年假"), SICK_LEAVE("病假"), OTHER_LEAVE("其他假期");

        private String type;

        LeaveType(String input) {
            type = input;
        };

        public String getType() {
            return type;
        }
    }

    public enum LeaveStatus {
        APPROVED("批准"), PENDING("审核中"), NOT_APPROVED("不批准"), EMPTY("");

        private String type;

        LeaveStatus(String input) {
            type = input;
        }

        public String getType() {
            return type;
        }
    }

    private void pushNotification(Object output) {
        System.out.println(output);
    }

    public Map<LeaveType, Double> leaveBalanceMap;
    public Map<LeaveType, Double> pendingBalanceMap;
    public Map<LeaveType, LeaveStatus> leaveStatusMap;


    public Leave() {
        leaveBalanceMap = new HashMap<>();
        leaveBalanceMap.put(LeaveType.ANNUAL_LEAVE, 0.0);
        leaveBalanceMap.put(LeaveType.SICK_LEAVE, 12.0);
        leaveBalanceMap.put(LeaveType.OTHER_LEAVE, 0.0);

        pendingBalanceMap = new HashMap<>();
        pendingBalanceMap.put(LeaveType.ANNUAL_LEAVE, 0.0);
        pendingBalanceMap.put(LeaveType.SICK_LEAVE, 0.0);
        pendingBalanceMap.put(LeaveType.OTHER_LEAVE, 0.0);

        leaveStatusMap = new HashMap<>();
        leaveStatusMap.put(LeaveType.ANNUAL_LEAVE, LeaveStatus.EMPTY);
        leaveStatusMap.put(LeaveType.SICK_LEAVE, LeaveStatus.EMPTY);
        leaveStatusMap.put(LeaveType.OTHER_LEAVE, LeaveStatus.EMPTY);
    }

    public boolean setLeaveBalance(LeaveType leaveType, double balance) {
        try {
            if (leaveType.equals(LeaveType.ANNUAL_LEAVE) || leaveType.equals(LeaveType.SICK_LEAVE)) {
                if (balance < 0) {
                    throw new Exception("Your leave balance is not enough");
                }
            }

            if (leaveType.equals(LeaveType.SICK_LEAVE) && balance > 12.0) {
                throw new Exception("The maximum of sick leave is 12.");
            }
        } catch (Exception e) {
            // push notification  
            pushNotification(e);
            return false;
        }
        
        leaveBalanceMap.put(leaveType, balance);
        return true;
    }

    public void setLeaveStatus(LeaveType leaveType, LeaveStatus leaveStatus) {
        leaveStatusMap.put(leaveType, leaveStatus);
    }

    public boolean earnBalance(double hours) {
        double balance = leaveBalanceMap.get(LeaveType.ANNUAL_LEAVE);
        System.out.println("you've earned " + LeaveType.ANNUAL_LEAVE + ": " + hours);

        leaveBalanceMap.put(LeaveType.ANNUAL_LEAVE, balance+hours);
        return true;
    }

    public boolean requestLeave(LeaveType leaveType, double hours) {
        double balance = leaveBalanceMap.get(leaveType);
        System.out.println("your " + leaveType + " balance: "+balance);
        boolean status = setLeaveBalance(leaveType, balance-hours);

        // pendingBalanceMap.put(leaveType, hours);

        if (status) {
            setLeaveStatus(leaveType, LeaveStatus.PENDING);
        }
        return status;
    }

    public void clearPendingBalanceMap() {
        pendingBalanceMap.put(LeaveType.ANNUAL_LEAVE, 0.0);
        pendingBalanceMap.put(LeaveType.SICK_LEAVE, 0.0);
        pendingBalanceMap.put(LeaveType.OTHER_LEAVE, 0.0);
    }

}
