// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Sendball;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
public class FixedShoot extends CommandBase {
  Shooter shooter;
  Sendball sendball;
  int time = 0;
  int velocity = 9120;
  /** Creates a new Shooting. */
  public FixedShoot(Shooter shooter, Sendball sendball, int velocity) {
    this.shooter = shooter;
    this.sendball = sendball;
    this.velocity = velocity;
  }
  @Override
  public void initialize() {
    time = 0;
  }
  @Override
  public void execute() {
    
    shooter.spin(velocity);
    
    if(time < 50) {
      ++time;
    }
    if(time > 20) {
      sendball.tryShoot(velocity);
    }
  }
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
    sendball.stopAll();
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}
