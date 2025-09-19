package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class Robot{
    public enum stateMachine {
        RETRACTED,
        HOVERING,
        PICKUP,
        DUNKING
    }

    public static stateMachine states = stateMachine.RETRACTED;

    ElapsedTime pickupTimer;
    ElapsedTime transferTimer;

    Intake intake;
    Outtake outtake;

    Robot (HardwareMap hwmap) {
        intake = new Intake(hwmap);
        outtake = new Outtake(hwmap);

        pickupTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        pickupTimer.startTime();
        transferTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        transferTimer.startTime();

    }

    public void toggle() {
        switch (states){
            case RETRACTED:
                states = stateMachine.HOVERING;
                break;
            case HOVERING:
                states = stateMachine.PICKUP;
                pickupTimer.reset();
                break;
            case PICKUP:
                states = stateMachine.DUNKING;
                break;
            case DUNKING:
                states = stateMachine.RETRACTED;
                break;
        }
    }

    public void changeState() {
        switch (states) {
            case RETRACTED:
                Intake.caseSliderHorizontal = Intake.sliderHorizontal.RETRACTED;
                Intake.caseWrist = Intake.wrist.HORIZONTAL;
                Intake.caseArm = Intake.arm.UP;
                Intake.caseClaw = Intake.claw.OPEN;


                break;
            case HOVERING:
                Intake.caseSliderHorizontal = Intake.sliderHorizontal.EXTENDED;
                Intake.caseArm = Intake.arm.HOVERING;
                Intake.caseClaw = Intake.claw.OPEN;

                break;
            case PICKUP:
                Intake.caseSliderHorizontal = Intake.sliderHorizontal.EXTENDED;
                if(pickupTimer.time(TimeUnit.MILLISECONDS) < 200)
                    Intake.caseArm = Intake.arm.DOWN;

                if(pickupTimer.time(TimeUnit.MILLISECONDS) > 250
                        && pickupTimer.time(TimeUnit.MILLISECONDS) < 300)
                    Intake.caseClaw = Intake.claw.CLOSED;

                if(pickupTimer.time(TimeUnit.MILLISECONDS) > 350
                        && pickupTimer.time(TimeUnit.MILLISECONDS) < 400)
                    Intake.caseArm = Intake.arm.HOVERING;
                break;
            case DUNKING:
                Intake.caseSliderHorizontal = Intake.sliderHorizontal.TRANSFER;
                Intake.caseWrist = Intake.wrist.HORIZONTAL;
                Intake.caseArm = Intake.arm.TRANSFER;

                break;
        }
    }

    public void run() {

        intake.runArm();
        intake.runWrist();
        intake.runClaw();
        intake.runSlider();
    }
}
