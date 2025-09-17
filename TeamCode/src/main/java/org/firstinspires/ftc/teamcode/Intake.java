package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Intake {
    FtcDashboard dashboard;

    Servo servoArm, servoWrist, servoClaw;

    public static double positionArmUp = 0.45, positionArmDown = 0.9, positionArmHovering = 0.8, positionArmTransfer = 0;
    public static double positionWristHorizontal = 0.3, positionWristVertical = 0.63;
    public static double positionWristDiagonalLeft = 0, positionWristDiagonalRight = 0.5;
    public static double positionClawOpen = 0, positionClawClosed = 0.4;

    public enum arm {
        UP,
        DOWN,
        HOVERING,
        TRANSFER
    }

    public enum wrist {
        HORIZONTAL,
        VERTICAL,
        LEFTDIAGONAL,
        RIGHTDIAGONAL
    }

    public enum claw {
        OPEN,
        CLOSED
    }
    public static arm caseArm;
    public static wrist caseWrist;
    public static  claw caseClaw;

    void hardware(HardwareMap hwmap) {
        servoArm = hwmap.get(Servo.class, "servoarmintake");
        servoWrist = hwmap.get(Servo.class, "servowristintake");
        servoClaw = hwmap.get(Servo.class, "servoclawintake");
    }
    Intake(HardwareMap hwmap){
        dashboard = FtcDashboard.getInstance();

        hardware(hwmap);
    }

    void runArm() {
        switch (caseArm) {
            case UP:
                servoArm.setPosition(positionArmUp);
                break;
            case DOWN:
                servoArm.setPosition(positionArmDown);
                break;
            case HOVERING:
                servoArm.setPosition(positionArmHovering);
                break;
            case TRANSFER:
                servoArm.setPosition(positionArmTransfer);
                break;
        }
    }

    void runWrist() {
        switch (caseWrist) {
            case HORIZONTAL:
                servoWrist.setPosition(positionWristHorizontal);
                break;
            case VERTICAL:
                servoWrist.setPosition(positionWristVertical);
                break;
            case LEFTDIAGONAL:
                servoWrist.setPosition(positionWristDiagonalLeft);
                break;
            case RIGHTDIAGONAL:
                servoWrist.setPosition(positionWristDiagonalRight);
                break;
        }
    }

    void runClaw() {
        switch (caseClaw) {
            case OPEN:
                servoClaw.setPosition(positionClawOpen);
                break;
            case CLOSED:
                servoClaw.setPosition(positionClawClosed);
                break;
        }
    }
}
