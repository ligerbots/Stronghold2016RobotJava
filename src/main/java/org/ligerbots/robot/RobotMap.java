/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.ligerbots.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final double TICKS_PER_SECOND = 50;

	public static final int RELAY_LED_RING_SPIKE = 0;

	public static final int PDP_CAN = 0;

	// left = 1, 3, 5
	// right = 2, 4, 6
	public static final int CT_DRIVE_LEFT1 = 1;
	public static final int CT_DRIVE_LEFT2 = 3;
	public static final int CT_DRIVE_LEFT3 = 5;
	public static final int CT_DRIVE_RIGHT1 = 2;
	public static final int CT_DRIVE_RIGHT2 = 4;
	public static final int CT_DRIVE_RIGHT3 = 6;

	public static final int PCM_CAN = 7;

	public static final int CT_INTAKE_ROLLER = 8;

	public static final int PCM_HIGH_FLOW_CAN = 9;


	public static final int PCM_SHIFTER_LOW_GEAR = 1;
	public static final int PCM_SHIFTER_HIGH_GEAR = 0;
	public static final int PCM_INTAKE_UP = 5;
	public static final int PCM_INTAKE_DOWN = 4;
	public static final int PCM_WEDGES_UP = 3;
	public static final int PCM_WEDGES_DOWN = 2;

	public static final int PCM_SHOOTER_FIRE = 6;
	public static final int PCM_SHOOTER_RETRACT = 7;

	public static final int LIMIT_SWITCH_INTAKE_UP = 0;
	public static final int LIMIT_SWITCH_WEGDE_DOWN = 1;
	public static final int LIMIT_SWITCH_INTAKE_BALL_IN_SHOOTER = 2;
	public static final int LIMIT_SWITCH_INTAKE_BALL_DEFENSES_POSITION = 3;

	public static final int PWM_SERVO_SHOOTER_LEFT = 0;
	public static final int PWM_SERVO_SHOOTER_RIGHT = 1;

	public static final int AI_INTAKE_DISTANCE_SENSOR = 0;
	public static final int AI_PRESSURE_SENSOR = 1;

	// Maximum pitch angle. If the robot gets to this angle, we need to go
	// backwards to keep the robot from tipping over.
	public static final float MAX_PITCH_ANGLE = 45;

	//Flap limits
	public static final double FlapLeftHighLimit = 25.8;
	public static final double FlapLeftLowLimit = 148;
	public static final double FlapRightHighLimit = 6.7;
	public static final double FlapRightLowLimit = 111;

	//Roller values
	public static final double ROLLER_SPEED = 1;

}
