package frc.robot.subsystems;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  // Create subsystem hardware
  private XRPServo armServo = new XRPServo(4);

  public Arm() {
    // Code to run when Arm is created
    super();
  }

  private void moveToZero() {
    armServo.setAngle(0.0);
  }

  private void moveToNinety() {
    armServo.setAngle(90.0);
  }

  private void moveToAngle(double angle) {
    armServo.setAngle(angle);
  }

  public Command moveArm(double target) {
    return this.run(() -> moveToAngle(target));
  }

  public Command moveArm(Supplier<Double> targetGetter) {
    return this.run(() -> moveToAngle(targetGetter.get()));
  }

  public Command resetArm() {
    return this.run(() -> moveToZero());
  }
}
