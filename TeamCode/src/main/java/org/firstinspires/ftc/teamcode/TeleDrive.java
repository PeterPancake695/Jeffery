package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void move(double leftStickX, double leftStickY, double leftTrigger, double rightTrigger) {
        double rotation = leftTrigger - rightTrigger;
        double denominator = Math.max(1, Math.abs(leftStickY + leftStickX + rotation));
        double powerMotorFrontLeft = (leftStickY - leftStickX + rotation) / denominator;
        double powerMotorFrontRight = (leftStickY + leftStickX - rotation) / denominator;
        double powerMotorBackLeft = (leftStickY + leftStickX + rotation) / denominator;
        double powerMotorBackRight = (leftStickY - leftStickX - rotation) / denominator;

        motorFrontLeft.setPower(powerMotorFrontLeft);
        motorFrontRight.setPower(powerMotorFrontRight);
        motorBackLeft.setPower(powerMotorBackLeft);
        motorBackRight.setPower(powerMotorBackRight);
    }
}
