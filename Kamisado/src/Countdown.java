
import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
static int interval;
static Timer timer;

public static void main(String[] args) {
	Countdown cd = new Countdown();
	cd.setup();
	
}


public void setup(){
    String secs = "5";
    int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = Integer.parseInt(secs);
    System.out.println(secs);
    timer.scheduleAtFixedRate(new TimerTask() {

        public void run() {
            System.out.println(setInterval());

        }
    }, delay, period);
}

private static final int setInterval() {
    if (interval == 1)
        timer.cancel();
    return --interval;
}


public int getInterval() {
	return interval;
}
}