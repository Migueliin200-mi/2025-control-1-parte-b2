package es.upm.grise.profundizacion.cruiseControl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.upm.grise.profundizacion.exceptions.IncorrectSpeedSetException;
import es.upm.grise.profundizacion.exceptions.SpeedSetAboveSpeedLimitException;

public class CruiseControlTest {

    @Mock
    private Speedometer speedometer;

    private CruiseControl cruiseControl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cruiseControl = new CruiseControl(speedometer);
    }

    @Test
    public void setSpeedSetBelowZeroThrowsIncorrectSpeedSetException() {
        try {
            cruiseControl.setSpeedSet(-5);
            fail("Should throw IncorrectSpeedSetException");
        } catch (IncorrectSpeedSetException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Wrong exception thrown: " + e.getClass().getName());
        }
    }

    @Test
    public void setSpeedSetZeroThrowsIncorrectSpeedSetException() {
        try {
            cruiseControl.setSpeedSet(0);
            fail("Should throw IncorrectSpeedSetException");
        } catch (IncorrectSpeedSetException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Wrong exception thrown: " + e.getClass().getName());
        }
    }

    @Test
    public void setSpeedSetValidValueStoresIt() throws Exception {
        cruiseControl.setSpeedSet(80);

        assertEquals(Integer.valueOf(80), cruiseControl.getSpeedSet());
    }

    @Test
    public void setSpeedSetAboveSpeedLimitThrowsException() {
        try {
            cruiseControl.setSpeedLimit(100);
            cruiseControl.setSpeedSet(120);
            fail("Should throw SpeedSetAboveSpeedLimitException");
        } catch (SpeedSetAboveSpeedLimitException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Wrong exception thrown: " + e.getClass().getName());
        }
    }

    @Test
    public void setSpeedSetEqualToSpeedLimitAllowed() throws Exception {
        cruiseControl.setSpeedLimit(100);
        cruiseControl.setSpeedSet(100);

        assertEquals(Integer.valueOf(100), cruiseControl.getSpeedSet());
    }
}
