// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.classes;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDevice.Direction;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Class for supporting continuous analog encoders, such as the US Digital MA3. */
public class AnalogEncoder implements Sendable, AutoCloseable {
  private final AnalogInput m_analogInput;
  private AnalogTrigger m_analogTrigger;
  // private Counter m_counter;
  private double m_distancePerRotation = 1.0;
  
  private double prevVoltage;
  private double angle;

  protected SimDevice m_simDevice;
  protected SimDouble m_simPosition;

  /**
   * Construct a new AnalogEncoder attached to a specific AnalogIn channel.
   *
   * @param channel the analog input channel to attach to
   */
  public AnalogEncoder(int channel) {
    this(new AnalogInput(channel));
  }

  /**
   * Construct a new AnalogEncoder attached to a specific AnalogInput.
   *
   * @param analogInput the analog input to attach to
   */
  public AnalogEncoder(AnalogInput analogInput) {
    m_analogInput = analogInput;
    init();
  }

  private void init() {
    m_analogTrigger = new AnalogTrigger(m_analogInput);
    // m_counter = new Counter();
    prevVoltage = 0;
    angle = 0;

    m_simDevice = SimDevice.create("AnalogEncoder", m_analogInput.getChannel());

    if (m_simDevice != null) {
      m_simPosition = m_simDevice.createDouble("Position", Direction.kInput, 0.0);
    }

    // Limits need to be 25% from each end
    m_analogTrigger.setLimitsVoltage(0, 5);
    // m_counter.setUpSource(m_analogTrigger, AnalogTriggerType.kRisingPulse);
    // m_counter.setDownSource(m_analogTrigger, AnalogTriggerType.kFallingPulse);

    SendableRegistry.addLW(this, "Analog Encoder", m_analogInput.getChannel());
  }

  /**
   * Get the analog input voltage for the encoder
   *
   * <p>This is reported in rotations since the last reset.
   *
   * @return the analog input voltage in volts
   */
  public double getVoltage() {
    return m_analogInput.getVoltage();
  }

  /**
   * Get the encoder value since the last reset.
   *
   * <p>This is reported in rotations since the last reset.
   *
   * @return the encoder value in rotations
   */
  public double getAngle()
  {
    return angle;
  }

  public void update() {
    double currVoltage = m_analogInput.getVoltage();
   

      double increment = 0;

      // convert v/5 = x/360 solve for x
      // double smallAngle = (currVoltage - prevVoltage / 5) * 360;

      if (Math.abs(currVoltage - prevVoltage) >= 4 ) {
        if (currVoltage < prevVoltage) {
          increment = (5 + currVoltage - prevVoltage) / 5 * 360;
        }
        else {
          increment = -(5 + prevVoltage - currVoltage) / 5 * 360;
        }
      }

      else {
        increment = (currVoltage - prevVoltage) / 5 * 360;
      }

      angle += increment;

      prevVoltage = currVoltage;
  }

  public double getBigAngle()
  {
    double scaled = angle / 5.8;
    scaled = scaled % 360;
    if(scaled < 0 ) scaled = 360 + scaled;
    return scaled;

  }

  public void setPrevVoltage(double voltage)
  {
    prevVoltage = voltage;
  }
  /**
   * Get the offset of position relative to the last reset.
   *
   * <p>getPositionInRotation() - getPositionOffset() will give an encoder absolute position
   * relative to the last reset. This could potentially be negative, which needs to be accounted
   * for.
   *
   * @return the position offset
   */

  /**
   * Set the distance per rotation of the encoder. This sets the multiplier used to determine the
   * distance driven based on the rotation value from the encoder. Set this value based on the how
   * far the mechanism travels in 1 rotation of the encoder, and factor in gearing reductions
   * following the encoder shaft. This distance can be in any units you like, linear or angular.
   *
   * @param distancePerRotation the distance per rotation of the encoder
   */
  public void setDistancePerRotation(double distancePerRotation) {
    m_distancePerRotation = distancePerRotation;
  }

  /**
   * Get the distance per rotation for this encoder.
   *
   * @return The scale factor that will be used to convert rotation to useful units.
   */
  public double getDistancePerRotation() {
    return m_distancePerRotation;
  }

  /**
   * Get the distance the sensor has driven since the last reset as scaled by the value from {@link
   * #setDistancePerRotation(double)}.
   *
   * @return The distance driven since the last reset
   */
  public double getDistance() {
    return getDistancePerRotation();
  }

  /**
   * Get the channel number.
   *
   * @return The channel number.
   */
  public int getChannel() {
    return m_analogInput.getChannel();
  }

  /** Reset the Encoder distance to zero. */
  public void reset() {
    // m_counter.reset();
    angle = 0;
    prevVoltage = m_analogInput.getVoltage();
  }

  @Override
  public void close() {
    // m_counter.close();
    m_analogTrigger.close();
    if (m_simDevice != null) {
      m_simDevice.close();
    }
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("AbsoluteEncoder");
    builder.addDoubleProperty("Distance", this::getDistance, null);
    builder.addDoubleProperty("Distance Per Rotation", this::getDistancePerRotation, null);
  }
  
}
