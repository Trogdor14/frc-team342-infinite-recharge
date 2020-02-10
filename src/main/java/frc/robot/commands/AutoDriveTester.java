package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Factory;

public class AutoDriveTester extends SequentialCommandGroup {
  /**
   * Used to test Drive To Distance
   */

  public AutoDriveTester() {
    
    super(new DriveToDistance(10)); 

  }

}
