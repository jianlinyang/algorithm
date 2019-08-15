package event;

import java.io.Serializable;
import java.util.EventListener;

/**
 * @author yang
 * @date 2019/8/14 20:12
 */
public interface Listener extends EventListener {
    void begin(MethodMonitorEvent methodMonitorEvent);

    void end(MethodMonitorEvent methodMonitorEvent);
}
