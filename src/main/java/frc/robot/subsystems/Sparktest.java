package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Sparktest extends SubsystemBase {
    Spark sparkmotor = new Spark(Constants.sparkmotorno); 
    public Sparktest(){
        
    }
}
