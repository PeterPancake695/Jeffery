package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOperator extends LinearOpMode {
    TeleDrive drive;
    Robot robot;

    @Override
    public void runOpMode() {
        drive = new TeleDrive(hardwareMap);
        robot = new Robot(hardwareMap);
        waitForStart();
        if(opModeIsActive()) {

        }

        while(opModeIsActive()) {
            drive.move(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.left_trigger, gamepad1.right_trigger);

            if(gamepad1.aWasPressed()) {
                robot.toggle();
            }

            if(Robot.states == Robot.stateMachine.PICKUP && gamepad1.bWasPressed())
                Robot.states = Robot.stateMachine.HOVERING;

            if(Robot.states == Robot.stateMachine.HOVERING) {
                if(gamepad1.dpadUpWasPressed())
                    Intake.caseWrist = Intake.wrist.VERTICAL;
                if(gamepad1.dpadDownWasPressed())
                    Intake.caseWrist = Intake.wrist.HORIZONTAL;
                if(gamepad1.dpadLeftWasPressed())
                    Intake.caseWrist = Intake.wrist.LEFTDIAGONAL;
                if(gamepad1.dpadRightWasPressed())
                    Intake.caseWrist = Intake.wrist.RIGHTDIAGONAL;
            }

            robot.changeState();

            robot.run();
        }
    }

}
