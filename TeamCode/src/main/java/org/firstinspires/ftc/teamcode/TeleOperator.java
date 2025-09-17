package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOperator extends LinearOpMode {
    TeleDrive drive = new TeleDrive(hardwareMap);
    Robot robot = new Robot(hardwareMap);

    @Override
    public void runOpMode() {

    }

}
