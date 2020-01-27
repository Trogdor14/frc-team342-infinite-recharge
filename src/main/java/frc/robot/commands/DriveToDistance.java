package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Factory;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSystem;

public class DriveToDistance extends CommandBase {
  /**
   * Drives the robot to a given distance
   * 
   * https://www.chiefdelphi.com/t/neo-motor-integrated-encoder-w-spark-max-controller/340458
   */

  private final DriveSystem driveSystem; 

  private double goal;

  private double init_L1;
  private double init_L2;
  private double init_R1;
  private double init_R2; 
  private double current_L1;
  private double current_L2;
  private double current_R1;
  private double current_R2;
  private double current_Left;
	private double current_Right; 
	private double L1_rotation_count;
  private double L2_rotation_count;
  private double R1_rotation_count;
  private double R2_rotation_count;

	//private double degrees_off_zero;
	private double left_speed;
  private double right_speed;
  
  private static final double SPEED_CONST = 0.5;
  public static final double TEST_DISTANCE = 1.5;

  public DriveToDistance(double distance) {
    driveSystem = Factory.getDrive(); 
    goal = distance;
  }

  @Override
  public void initialize() {
    init_L1 = driveSystem.encoderL1.getPosition();
    init_L2 = driveSystem.encoderL2.getPosition(); 
    init_R1 = driveSystem.encoderR1.getPosition();
    init_R2 = driveSystem.encoderR2.getPosition();
  }

  @Override
  public void execute() {
    
    left_speed = SPEED_CONST; 
    right_speed = SPEED_CONST; 

    current_L1 = driveSystem.encoderL1.getPosition() - init_L1;
    current_L2 = driveSystem.encoderL2.getPosition() - init_L2; 
    current_R1 = driveSystem.encoderR1.getPosition() - init_R1;
    current_R2 = driveSystem.encoderR2.getPosition() - init_R2;

    L1_rotation_count = Math.abs(current_L1 / 4096); 
    L2_rotation_count = Math.abs(current_L2 / 4096); 
    R1_rotation_count = Math.abs(current_R1 / 4096); 
    R2_rotation_count = Math.abs(current_R2 / 4096); 

    //might need to do some testing to see which side is inverted
    driveSystem.Drive(left_speed, right_speed *1, 0); 

    SmartDashboard.putNumber("left1", L1_rotation_count);
    SmartDashboard.putNumber("left2", L2_rotation_count);
    SmartDashboard.putNumber("right1", R1_rotation_count);
    SmartDashboard.putNumber("right2", R2_rotation_count);

  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.stopDrive(); 
  }

  @Override
  public boolean isFinished() {
    /*if((L1_rotation_count >= goal && L2_rotation_count >= goal) || (R1_rotation_count >= goal && R2_rotation_count >= goal)){
      return true;
    } else {
      return false;
    }*/

    //kurnnell likes to be difficult so heres this new if statment @cnonnell 
    return ((L1_rotation_count >= goal && L2_rotation_count >= goal) || (R1_rotation_count >= goal && R2_rotation_count >= goal) ? true : false);
  }

}

