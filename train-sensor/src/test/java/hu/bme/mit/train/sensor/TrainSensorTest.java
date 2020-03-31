package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.verification.VerificationMode;

import static org.mockito.Mockito.*;


public class TrainSensorTest {

    private TrainUser mockTrainUser;
    private TrainController mockTrainController;

    private TrainSensor trainSensor;

    @Before
    public void before() {
        mockTrainUser = mock(TrainUser.class);
        mockTrainController = mock(TrainController.class);
        when(mockTrainController.getReferenceSpeed()).thenReturn(100);
        trainSensor = new TrainSensorImpl(mockTrainController, mockTrainUser);
    }

    @Test
    public void whenOverrideSpeedLimitCalledWithNegativeNumber_thenShouldSetAlarmFlag () {

        trainSensor.overrideSpeedLimit(-50);
        verify(mockTrainUser).setAlarmState(true);

    }

    @Test
    public void whenOverrideSpeedLimitCalledWithMoreThan500_thenShouldSetAlarmFlag () {

        trainSensor.overrideSpeedLimit(501);
        verify(mockTrainUser).setAlarmState(true);

    }

    @Test
    public void whenOverrideSpeedLimitCalledWithMoreThan50PercentSlowerThanReference_thenShouldSetAlarmFlag () {
        trainSensor.overrideSpeedLimit(50);
        verify(mockTrainUser, never()).setAlarmState(true);

        trainSensor.overrideSpeedLimit(49);
        verify(mockTrainUser, atLeastOnce()).setAlarmState(true);

    }

    @Test
    public void whenOverrideSpeedLimitCalledWithMoreThan50PercentSlowerThenReference_thenCalledWithRightSpeed_thenShouldsetAndResetAlarmFlag () {
        trainSensor.overrideSpeedLimit(30);
        verify(mockTrainUser, atLeastOnce()).setAlarmState(true);

        trainSensor.overrideSpeedLimit(55);
        verify(mockTrainUser, atLeastOnce()).setAlarmState(false);

    }

    @Test
    public void whenOverrideSpeedLimitIsCalled_thenValueIsSet () {

        trainSensor.overrideSpeedLimit(70);
        Assert.assertEquals(70, trainSensor.getSpeedLimit());

    }

}
