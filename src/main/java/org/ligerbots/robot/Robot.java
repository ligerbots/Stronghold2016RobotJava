/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.ligerbots.robot;

import org.ligerbots.robot.Subsystems.CompressorSubsystem;
import org.ligerbots.robot.Subsystems.DriveTrain;
import org.ligerbots.robot.Subsystems.FlapSubsystem;
import org.ligerbots.robot.Subsystems.IntakeSubsystem;
import org.ligerbots.robot.Subsystems.LEDSubsystem;
import org.ligerbots.robot.Subsystems.ShooterSubsystem;
import org.ligerbots.robot.Subsystems.WedgeSubsystem;
import org.ligerbots.robot.Subsystems.LEDSubsystem.LEDState;
import org.ligerbots.robot.Commands.IntakeRollerCommand;
import org.ligerbots.robot.Commands.JoystickDriveCommand;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static CompressorSubsystem compressor;
	public static DriveTrain drivetrain;
	public static LEDSubsystem leds;
	public static FlapSubsystem flaps;
	public static IntakeSubsystem intake;
	public static OI oi;
	public static ShooterSubsystem shooter;
	public static WedgeSubsystem wedge;

	public static IntakeRollerCommand intakeRoller;
	public static JoystickDriveCommand joystickDrive;

	public static SmartDashboard smartDashboard;
	public static SendableChooser ledChooser;

	public static LEDState cachedLEDState;

	public static double ticks;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		compressor = new CompressorSubsystem();
		drivetrain = new DriveTrain();
		leds = new LEDSubsystem();
		intake = new IntakeSubsystem();
		shooter = new ShooterSubsystem();
		wedge = new WedgeSubsystem();

		joystickDrive = new JoystickDriveCommand();
		intakeRoller = new IntakeRollerCommand();

		oi = new OI();

		//Default stuff
		compressor.SetCompressor(false);
		ticks = 0;

		//LED Options in smartdashboard
        ledChooser = new SendableChooser();
        ledChooser.addOption("Red", LEDState.RED);
        ledChooser.addOption("Blue", LEDState.BLUE);
        ledChooser.addOption("Shoot", LEDState.SHOOT);
        ledChooser.addOption("Off", LEDState.OFF);
		ledChooser.close();
		smartDashboard.putData("LED State", ledChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		joystickDrive.cancel(); //Stop accepting input
		intakeRoller.cancel();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		ticks = 0;
		joystickDrive.start(); //Starts polling for user input to control the robot
		intakeRoller.start();

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		AllPeriodic();

		Scheduler.getInstance().run();
	}

	public void AllPeriodic() { 
		if (ticks % 20 == 0) {
			compressor.SendValuesToSmartDashboard();
			drivetrain.SendValuesToSmartDashboard();
			intake.SendValuesToSmartDashboard();

			//LED Control via smartdashboard
			LEDState currentLEDSelection = (LEDState) ledChooser.getSelected();
			if (currentLEDSelection != cachedLEDState) {
				cachedLEDState = currentLEDSelection;
				leds.SetLEDs(currentLEDSelection);
			}
		}

		ticks++;
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
