import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.*;
import java.util.Set;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class RouletteTest {

    @Mock
    IWheel IWheel ;
    Roulette wheel;

    @BeforeEach
    void init(){
        wheel = new Roulette(IWheel);
    }

    /*
    Test if spin stop after 20 seconds
    */

    @Test
    public void Stopped20SAfterSpinTest() {

        wheel.spin(20000);
        wheel.tick(20000);

        verify(IWheel).stopped(anyInt());
    }

    /*
        Test specify ball location when wheel stopped
    */
    @Test
    public void SpecifyBallLocationStopped() {

        wheel.spin(20000);
        wheel.tick(20000);

        verify(IWheel, times(1)).stopped(anyInt());
    }

    /*
        Test specify ball location once wheel stopped
    */
    @Test
    public void SpecifyBallLocationOnceStopped() {

        wheel.spin(20000);
        wheel.tick(20000);
        wheel.tick(20001);

        verify(IWheel, times(1)).stopped(anyInt());
    }

    /*
        Test never notify stopped before spin end
    */
    @Test
    public void NotNotifyStoppedBeforeSpinEnd() {

        wheel.spin(20000);
        wheel.tick(10000);

        verify(IWheel, never() ).stopped(anyInt());
    }
}