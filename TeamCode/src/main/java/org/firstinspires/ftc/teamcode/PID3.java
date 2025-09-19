package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID3 {
    private double Kp, Ki, Kd, F;
    private double integralSum, lastError;
    private final ElapsedTime timer;
    private double outputMin = -1.0, outputMax = 1.0;
    private double integralMax = 1.0;

    public PID3(double Kp, double Ki, double Kd, double F) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.F = F;
        this.Kd = Kd;
        this.integralSum = 0;
        this.lastError = Double.NaN;
        this.timer = new ElapsedTime();
        this.timer.reset();
    }

    public void setOutputRange(double min, double max) {
        this.outputMin = min;
        this.outputMax = max;
    }

    public void setIntegralLimit(double limit) {
        this.integralMax = limit;
    }

    public double update(int target, int currpos) {
        //CALC EROARE
        double error = target - currpos;

        //TIMERUL DINTRE APELURI
        double dt = timer.seconds();
        timer.reset();

        //CALC P
        double P = Kp * error;

        //CALC I
        if (!(integralSum >= integralMax && error > 0) && !(integralSum <= -integralMax && error < 0)) {
            integralSum += error * dt;
        }
        integralSum = clamp(integralSum, -integralMax, integralMax);
        double I = Ki * integralSum;

        //CALC D
        double derivative = 0;
        if (!Double.isNaN(lastError)) {
            derivative = (error - lastError) / dt;
        }
        double D = Kd * derivative;

        //CALC FINAL
        double pow = P + I + D + F;


        lastError = error;

        return clamp(pow, outputMin, outputMax);
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}