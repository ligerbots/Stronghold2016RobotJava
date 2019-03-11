package org.ligerbots.robot.Subsystems;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlapSubsystem extends Subsystem {
    Servo leftFlap;
    Servo rightFlap;

    double currentLeftFraction;
    double currentRightFraction;

    public FlapSubsystem() {
        setSubsystem("Flaps");
        leftFlap = new Servo(RobotMap.PWM_SERVO_SHOOTER_LEFT);
        rightFlap = new Servo(RobotMap.PWM_SERVO_SHOOTER_RIGHT);
    }

    protected void SetLeftFlap(double angle) { //In degrees
        // servo is reversed - need to flip angle
        leftFlap.setAngle(180-angle);
    }

    protected void SetRightFlap(double angle) {
        rightFlap.setAngle(angle);
    }

    public void SetFlapsFraction(double fractionLeft, double fractionRight) {
        //Values verification
        fractionLeft = Math.max(Math.min(fractionLeft, 1), 0);
        fractionRight = Math.max(Math.min(fractionRight, 1), 0);

        currentLeftFraction = fractionLeft;
        currentRightFraction = fractionRight;

        double leftFlapPosition = (RobotMap.FlapLeftHighLimit - RobotMap.FlapLeftLowLimit) * fractionLeft + RobotMap.FlapLeftLowLimit;
        double rightFlapPosition = (RobotMap.FlapRightHighLimit - RobotMap.FlapLeftLowLimit) * fractionRight + RobotMap.FlapRightLowLimit;

        SetLeftFlap(leftFlapPosition);
        SetRightFlap(rightFlapPosition);
    }

    public void SetFlapsFraction(double fraction) {
        SetFlapsFraction(fraction, fraction);
    }

    public boolean SafeToIntake() {
        return currentLeftFraction == 1.0 && currentRightFraction == 1.0;
    }

    //Methods to get current values
    public double getLeftFlapFraction() {
        return currentLeftFraction;
    }

    public double getRightFlapFraction() {
        return currentRightFraction;
    }

    public double getLeftFlapAngle() {
        return leftFlap.getAngle();
    }

    public double getRightFlapAngle() {
        return rightFlap.getAngle();
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}