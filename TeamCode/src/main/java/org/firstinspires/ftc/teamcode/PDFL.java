package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class PDFL {

    double kP, kD, kF, kL;
    double deadZone;
    RingBuffer<Double> timeBuffer = new RingBuffer<Double>(3);
    RingBuffer<Double> errorBuffer = new RingBuffer<Double>(3);
    ElapsedTime timer;


    PDFL(double kP, double kD, double kF, double kL) {
        timeBuffer.put(0.0);
        errorBuffer.put(0.0);

        this.kP = kP;
        this.kD = kD;
        this.kF = kF;
        this.kL = kL;
        this.timer = new ElapsedTime();
        this.timer.reset();
    }

    public void setDeadZone(double deadZone) {
        this.deadZone = deadZone;
    }

    public void reset() {
        timeBuffer.fill(0.0);
        errorBuffer.fill(0.0);
        this.timer.reset();
    }

    public double run(double error) {
        double time = timer.time(TimeUnit.MILLISECONDS);
        double previous_time = timeBuffer.getLastValue();
        double previous_error = errorBuffer.getLastValue();

        timeBuffer.put(time);
        errorBuffer.put(error);

        double delta_time = time-previous_time;
        double delta_error = error-previous_error;

        // if PDFL hasnt been updated, reset it
        if(delta_time > 200) {
            reset();
            return run(error);
        }

        double p = pComponent(error);
        double d = dComponent(delta_error, delta_time);
        double f = fComponent();
        double l = lComponent(error);

        double response;

        if (Math.abs(error) < deadZone)
            response = p + d + f;
        else
            response = p + d + f + l;

        return response;
    }

    double pComponent(double error) {
        double response = kP * error;

        return response;
    }

    double dComponent(double deltaError, double deltaTime){
        double derivative = deltaError / deltaTime;
        double response = derivative * kD;

        return response;
    }

    double fComponent(){
        double response = kF;
        return response;
    }

    double lComponent(double error) {
        double direction = Math.signum(error);
        double response = direction * kL;

        return response;
    }
}
