package staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import leave.Leave.LeaveStatus;
import leave.Leave.LeaveType;

public class Manager extends Staff{
    
    private Map<String, GeneralStaff> subordinates;

    public Manager(String id, String name) {
        super(id, name);
        subordinates = new HashMap<>();
    }

    // add a new employee
    public void addSubordinates(GeneralStaff staff) {
        subordinates.put(staff.getId(), staff);
    }

    // fire your employee
    public void removeSubordinates(GeneralStaff staff) {
        subordinates.remove(staff.getId());
    }

    // approve your subordinate's request
    public void approveLeave(GeneralStaff staff, LeaveType leaveType) {
        staff.leave.setLeaveStatus(leaveType, LeaveStatus.APPROVED);
        staff.leave.clearPendingBalanceMap();
        notifySubordinates();
    }

    // reject your subordinate's request
    public void rejectLeave(GeneralStaff staff, LeaveType leaveType) {
        double balance = staff.leave.pendingBalanceMap.get(leaveType);
        double currentBalance = staff.leave.leaveBalanceMap.get(leaveType);

        staff.leave.leaveBalanceMap.put(leaveType, currentBalance+balance);
        staff.leave.setLeaveStatus(leaveType, LeaveStatus.NOT_APPROVED);
        staff.leave.clearPendingBalanceMap();
        notifySubordinates();
    }

    public void notifySubordinates() {
        // ToDo: notification via email / text

    }

}
