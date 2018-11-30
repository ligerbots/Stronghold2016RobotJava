/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2877.robot;

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
	public static final int CT_ID_LEFT_LEADER = 1;
	public static final int CT_ID_LEFT_FOLLOWER = 2;
	public static final int CT_ID_RIGHT_LEADER = 3;
	public static final int CT_ID_RIGHT_FOLLOWER = 4;

	// Copied from PowerUp2018Robot
	public static final int CT_LEFT_1 = 4; //Should be 5 on production
	public static final int CT_LEFT_2 = 5;
	public static final int CT_RIGHT_1 = 6; // Should be 3 on production
	public static final int CT_RIGHT_2 = 3;
	public static final int CT_ELEVATOR_1 = 2;
	public static final int CT_ELEVATOR_2 = 1;
	public static final int CT_ELEVATOR_3 = 11;
	public static final int CT_ELEVATOR_4 = 12;
	public static final int CT_INTAKE_1 = 7;
	public static final int CT_INTAKE_2 = 8;
	public static final int CT_CLIMBER_1 = 11;
	public static final int CT_CLIMBER_2 = 12;
  
	
	public static final int PCM_ID = 9;
	
	public static final double GEARING_FACTOR = 1d;
	public static final double WHEEL_CIRCUMFERENCE = 3.92 * Math.PI;
	public static final int REV_BLINKIN = 1;
	public static final double AUTO_DRIVE_DISTANCE_TOLERANCE = 0;
	
	public static final int ULTRASONIC_LEFT_TRIGGER = 4;
	public static final int ULTRASONIC_LEFT_ECHO = 5;
	public static final int ULTRASONIC_RIGHT_TRIGGER = 6;
	public static final int ULTRASONIC_RIGHT_ECHO = 7;
	public static final double ULTRASONIC_DISTANCE_THRESHOLD = 3.0; //inches suck it mark, i programmed something
	
	public static final int PRESSURE_GAUGE = 0;	// Analog port zero
	public static final int LED_PWM_CHANNEL = 3;
	
	public static final double TOP_SPEED = 1.0;
	public static final double RAMP_DOWN_SPEED = 0.6;
	public static final double RAMP_DOWN_DIST = 10.0;
	public static final double AUTO_ACCEEPTABLE_TURN_ERROR = 1;
	
/* Copied from Stronghold2016Robot C++ code

	static constexpr double TICKS_PER_SECOND = 50.0;

	static constexpr int RELAY_LED_RING_SPIKE = 0;

	static constexpr int PDP_CAN = 0;

	// left = 1, 3, 5
	// right = 2, 4, 6
	static constexpr int CT_DRIVE_LEFT1 = 1;
	static constexpr int CT_DRIVE_LEFT2 = 3;
	static constexpr int CT_DRIVE_LEFT3 = 5;
	static constexpr int CT_DRIVE_RIGHT1 = 2;
	static constexpr int CT_DRIVE_RIGHT2 = 4;
	static constexpr int CT_DRIVE_RIGHT3 = 6;

	static constexpr int PCM_CAN = 7;

	static constexpr int CT_INTAKE_ROLLER = 8;

	static constexpr int PCM_HIGH_FLOW_CAN = 9;


	static constexpr int PCM_SHIFTER_LOW_GEAR = 1;
	static constexpr int PCM_SHIFTER_HIGH_GEAR = 0;
	static constexpr int PCM_INTAKE_UP = 5;
	static constexpr int PCM_INTAKE_DOWN = 4;
	static constexpr int PCM_WEDGES_UP = 3;
	static constexpr int PCM_WEDGES_DOWN = 2;

	static constexpr int PCM_SHOOTER_FIRE = 6;
	static constexpr int PCM_SHOOTER_RETRACT = 7;

	static constexpr int LIMIT_SWITCH_INTAKE_UP = 0;
	static constexpr int LIMIT_SWITCH_WEGDE_DOWN = 1;
	static constexpr int LIMIT_SWITCH_INTAKE_BALL_IN_SHOOTER = 2;
	static constexpr int LIMIT_SWITCH_INTAKE_BALL_DEFENSES_POSITION = 3;

	static constexpr int PWM_SERVO_SHOOTER_LEFT = 0;
	static constexpr int PWM_SERVO_SHOOTER_RIGHT = 1;

	static constexpr int AI_INTAKE_DISTANCE_SENSOR = 0;
	static constexpr int AI_PRESSURE_SENSOR = 1;

	// Maximum pitch angle. If the robot gets to this angle, we need to go
	// backwards to keep the robot from tipping over.
	static constexpr float MAX_PITCH_ANGLE = 45.0;
*/
  
}
