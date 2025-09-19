package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Intake {
    FtcDashboard dashboard;

    DcMotorEx motor;
    Servo servoArm, servoWrist, servoClaw;

    PID3 pidIntake;
    public static double kP = 0.05, kI = 0, kD = 0;
    public static int targetSliderExtended = 1600, targetSliderRetracted = 0, targetSliderTransfer = 200;
    public static double positionArmUp = 0.45, positionArmDown = 0.9, positionArmHovering = 0.8, positionArmTransfer = 0;
    public static double positionWristHorizontal = 0.3, positionWristVertical = 0.63;
    public static double positionWristDiagonalLeft = 0, positionWristDiagonalRight = 0.5;
    public static double positionClawOpen = 0, positionClawClosed = 0.4;

    public enum sliderHorizontal {
        RETRACTED,
        EXTENDED,
        TRANSFER
    }
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
    public static sliderHorizontal caseSliderHorizontal;
    public static arm caseArm;
    public static wrist caseWrist;
    public static  claw caseClaw;

    void hardware(HardwareMap hwmap) {
        servoArm = hwmap.get(Servo.class, "servoarmintake");
        servoWrist = hwmap.get(Servo.class, "servowristintake");
        servoClaw = hwmap.get(Servo.class, "servoclawintake");
        motor = hwmap.get(DcMotorEx.class, "motor");
    }
    Intake(HardwareMap hwmap){
        dashboard = FtcDashboard.getInstance();
        pidIntake = new PID3(kP, kI, kD, 0);
        pidIntake.setOutputRange(-1.0, 1.0);
        pidIntake.setIntegralLimit(0.25);

        hardware(hwmap);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void runSlider() {
        int currentPosition = motor.getCurrentPosition();
        double power = 0;
        switch (caseSliderHorizontal) {
            case RETRACTED:
                power = pidIntake.update(targetSliderRetracted, currentPosition);
                break;
            case EXTENDED:
                power = pidIntake.update(targetSliderExtended, currentPosition);
                break;
            case TRANSFER:
                power = pidIntake.update(targetSliderTransfer, currentPosition);
                break;
        }
        motor.setPower(power);
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
