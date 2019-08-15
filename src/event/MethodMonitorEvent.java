package event;

import java.util.EventObject;

/**
 * @author yang
 * @date 2019/8/14 20:09
 */
public class MethodMonitorEvent extends EventObject {
    public long timestamp;

    public MethodMonitorEvent(Object s) {
        super(s);
    }
}
