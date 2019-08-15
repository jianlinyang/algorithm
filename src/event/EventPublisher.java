package event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yang
 * @date 2019/8/14 20:16
 */
public class EventPublisher {
    private List<ListenerImpl> listeners = new ArrayList<>();

    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent methodMonitorEvent = new MethodMonitorEvent(this);
        publishEvent("begin", methodMonitorEvent);
        TimeUnit.SECONDS.sleep(5);
        publishEvent("end", methodMonitorEvent);
    }

    private void publishEvent(String s, MethodMonitorEvent methodMonitorEvent) {
        for (ListenerImpl listener : listeners) {
            if ("begin".equals(s)) {
                listener.begin(methodMonitorEvent);
            } else {
                listener.end(methodMonitorEvent);
            }
        }

    }

    public void addEventListener(ListenerImpl listener) {
        listeners.add(listener);
    }

    public void removeEventListener(ListenerImpl listener) {
        listeners.remove(listener);
    }

    public static void main(String[] args) throws Exception {
        EventPublisher eventPublisher = new EventPublisher();
        ListenerImpl listener = new ListenerImpl();
        eventPublisher.addEventListener(listener);
        eventPublisher.methodMonitor();
    }
}
