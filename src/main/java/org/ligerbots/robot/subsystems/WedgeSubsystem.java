package org.ligerbots.robot.subsystems;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WedgeSubsystem extends Subsystem {

    DoubleSolenoid wedgeArmSolenoid;
    DigitalInput wedgeDownSwitch;

    public WedgeSubsystem() {
        wedgeArmSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_WEDGES_DOWN, RobotMap.PCM_WEDGES_UP);
        wedgeDownSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_WEGDE_DOWN);
    }

    public boolean WedgeDown() {
        return wedgeDownSwitch.get();
    }

    public DoubleSolenoid.Value getWedgeValue() {
        return wedgeArmSolenoid.get();
    }

    public void liftWedge() {
        wedgeArmSolenoid.set(Value.kReverse);
    }

    public void lowerWedge() {
        wedgeArmSolenoid.set(Value.kForward);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}