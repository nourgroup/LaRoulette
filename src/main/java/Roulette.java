import java.util.Random;

public class Roulette {
    private boolean is_spinning = false;
    private final IWheel IWheel;
    private long spinForMs;
    private long currentMs = 0;
    private final Random random = new Random();

    Roulette(final IWheel IWheel) {
        this.IWheel = IWheel;
    }

    /*
        Start to spin the wheel
    */
    void spin(final long spinForMs) {
        this.is_spinning = true;
        this.spinForMs = spinForMs;
    }

    /*
        If the time is higher or equal we stop spinning
    */
    void tick(final long timeMs) {
        currentMs = timeMs;
        if (is_spinning && (currentMs >= spinForMs))
        {
            is_spinning = false;
            // Returns number between 0-26
            final int location = random.nextInt(37);
            this.IWheel.stopped(location);
        }
    }
}
