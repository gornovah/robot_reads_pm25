package com.seatcode.robotread;

import com.seatcode.robotread.actions.RobotSystem;
import com.seatcode.robotread.api.RobotSystemCommandProcessor;
import com.seatcode.robotread.builder.RobotSystemBuilder;
import com.seatcode.robotread.builder.impl.RobotSystemBuilderImpl;
import com.seatcode.robotread.domain.exceptions.RobotException;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Main class responsible to handle direct invocations to the services provided.
 * @author despinosa
 *
 */
public class App {
    private static final String START_ROBOT_SYSTEM = "start_robot_system";
    private static final String EMPTY = "";
    private static String polylineInput = EMPTY;
    private static RobotSystem robotSystem;

    public static void main(String[] args) {
        Scanner inputValue = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            System.out.println("1. Start");
            System.out.println("2. Stop");
            System.out.println("3. Re-Route");
            System.out.println("4. Print report");
            System.out.println("5. Exit");

            try {

                System.out.println("Enter one of the options:");
                int option = inputValue.nextInt();

                switch (option) {
                    case 1:
                        builder(START_ROBOT_SYSTEM);
                        break;
                    case 2:
                        builder("stop_robot_sytem");
                        break;
                    case 3:
                        polylineInput = obtainPolyline();
                        break;
                    case 4:
                        builder("report_measure");
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Only numbers between 1 and 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("You must insert a number");
                inputValue.next();
            }
        }
        System.exit(0);

    }

    private static void builder(String command) {

        if (START_ROBOT_SYSTEM.equals(command)) {
            RobotSystemBuilder robotSystemBuilder = new RobotSystemBuilderImpl();
            robotSystem = robotSystemBuilder.build();
        }
        RobotSystemCommandProcessor commandProcessor = new RobotSystemCommandProcessor();
        if (EMPTY.equals(polylineInput)){
            polylineInput = obtainDefaultPolyline();
        }
        try {
            commandProcessor.proccessCommands(robotSystem, polylineInput, command);
        } catch (InterruptedException e) {
            throw new RobotException("Error stop scheduler system");
        }
    }

    private static String obtainPolyline() {
        Scanner inputValue = new Scanner(System.in);
        System.out.println("Insert new polyline:");
        String polylineInput = inputValue.nextLine();
        System.out.println("new polylineInput:" + polylineInput);
        return polylineInput;
    }

    private static String obtainDefaultPolyline() {
        return "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGu\n" +
                "AD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa\n" +
                "@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkI\n" +
                "CmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^y\n" +
                "VJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSM\n" +
                "GBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@\n" +
                "a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iD\n" +
                "q@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQ\n" +
                "KkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";
    }
}
