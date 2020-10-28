package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import leave.Leave.LeaveType;
import staff.GeneralStaff;
import staff.Manager;



public class Demo {

    static Map<String, GeneralStaff> staffMap = new HashMap<>();
    static Map<String, Manager> managerMap = new HashMap<>();

    public static void main(String[] args) {
        
        System.out.println("Creating staff: Jacky");
        GeneralStaff staffA = new GeneralStaff("1111", "Jacky");
        System.out.println("Creating Manager: HSBC");
        Manager managerA = new Manager("2222", "HSBC");
        managerA.addSubordinates(staffA);
        for (int i=0; i<10; i++) {
            staffA.work(40.0);
        }
        System.out.println("Your annual leave balance: "+staffA.checkLeaveBalance("annual"));
        staffA.requestLeave("annual", 8.0);
        managerA.approveLeave(staffA, LeaveType.ANNUAL_LEAVE);
        System.out.println("HSBC Approved");
        System.out.println("Your annual leave balance: "+staffA.checkLeaveBalance("annual"));
        System.out.println(staffA.leave.leaveBalanceMap);
        System.out.println(LeaveType.ANNUAL_LEAVE);
        return;
    }
        /* To continue
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Please input: staff / manager");
            String input = scanner.nextLine().toLowerCase().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            // 
            //     input1 format: staff / manager
            //     input2 format: create / login
                
            //     input3 format (create): id|name
            //     input3 format (sign in): id
                
            //     input4 format (after sign in): checkBalance / checkStatus / requestleave / work
            //     input4 format (manager): create / checkBalance / checkStatus / requestleave / work / approve / reject / add / remove

            //     input5 format (requestleave): type|hours
            //     input5 format (approve/reject): id
            //
            switch (input) {
                case "staff": {
                    runStaff(scanner);
                    break;
                }
                default: {
                    System.out.println("Invalid input, returning to last step");
                    break;
                }
            }

        }
    }

    public void runManager(Scanner scanner) {
        System.out.println("Please input: create / login");
        String input = scanner.nextLine().toLowerCase().trim();
        switch (input) {
            case "create": {
                String[] inputList = scanner.nextLine().trim().split("|");
                managerMap.put(inputList[1], new GeneralStaff(inputList[1], inputList[2]));
                break;
            }
            case "login": {
                input = scanner.nextLine().toLowerCase().trim();
                GeneralStaff staff = staffMap.get(input);
                
                break;
            }
            default: {
                System.out.println("Invalid input, returning to last step");
                break;
            }
        }
    }

    public void runStaff(Scanner scanner) {
        System.out.println("Please input: create / login");
        String input = scanner.nextLine().toLowerCase().trim();
        switch (input) {
            case "create": {
                String[] inputList = scanner.nextLine().trim().split("|");
                staffMap.put(inputList[1], new GeneralStaff(inputList[1], inputList[2]));
                break;
            }
            case "login": {
                input = scanner.nextLine().toLowerCase().trim();
                GeneralStaff staff = staffMap.get(input);
                
                break;
            }
            default: {
                System.out.println("Invalid input, returning to last step");
                break;
            }
        }
    }

    */

}