package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class TeleDrive {
    DcMotorEx motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight;

    TeleDrive(HardwareMap hwmap) {
        motorFrontLeft = hwmap.get(DcMotorEx.class, "motorfrontleft");
        motorFrontRight = hwmap.get(DcMotorEx.class, "motorfrontright");
        motorBackLeft = hwmap.get(DcMotorEx.class, "motorbackleft");
        motorBackRight = hwmap.get(DcMotorEx.class, "motorbackright");

        motorFrontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void move(double leftStickX, double leftStickY, double leftTrigger, double rightTrigger) {

    }
}
