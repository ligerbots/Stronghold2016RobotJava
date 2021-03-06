/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.ligerbots.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.ligerbots.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING commands WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	//Intake is rollers, wedge is flap
	public XboxController xbox = new XboxController(0);

	Button IntakeShootSequence;
	Button ToggleFlapCommand;
	Button ShootCommand;
	Button GearShiftCommand;
	Button ToggleIntakeCommand;
	Button WedgeToggleCommand;
	Button CompressorToggleCommand;

	public OI() {
		//Map joystick buttons to each command (no autonomous included)
		IntakeShootSequence = new JoystickButton(xbox, 1);
		
		// ToggleFlapCommand = new JoystickButton(xbox, 2);
		
		ShootCommand = new JoystickButton(xbox, 3);
		
		GearShiftCommand = new JoystickButton(xbox, 5);
		
		ToggleIntakeCommand = new JoystickButton(xbox, 6);
		
		WedgeToggleCommand = new JoystickButton(xbox, 7);
		
		CompressorToggleCommand = new JoystickButton(xbox, 8);


		IntakeShootSequence.whenPressed(new IntakeShootSequence());
		
		// ToggleFlapCommand.whenPressed(new ToggleFlapCommand());
		
		ShootCommand.whenPressed(new ShootCommand());
		
		GearShiftCommand.whenPressed(new GearShiftCommand());
		
		ToggleIntakeCommand.whenPressed(new IntakeToggleCommand());
		
		WedgeToggleCommand.whenPressed(new WedgeToggleCommand());
		
		CompressorToggleCommand.whenPressed(new CompressorToggleCommand());

		//commands into smartdashboard
		SmartDashboard.putData("IntakeCommand", new IntakeShootSequence());
		SmartDashboard.putData("ToggleIntakeCommand", new IntakeToggleCommand());
		SmartDashboard.putData("CompressorToggle", new CompressorToggleCommand());
		SmartDashboard.putData("ShootCommand", new ShootCommand());
	}
}
