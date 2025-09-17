package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot{
    Intake intake;
    Outtake outtake;

    Robot (HardwareMap hwmap) {
        intake = new Intake(hwmap);
        outtake = new Outtake(hwmap);
    }


}
