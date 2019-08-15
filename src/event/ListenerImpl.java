package event;

/**
 * @author yang
 * @date 2019/8/14 20:14
 */
public class ListenerImpl implements Listener {
    @Override
    public void begin(MethodMonitorEvent methodMonitorEvent) {
        methodMonitorEvent.timestamp=System.currentTimeMillis();
    }

    @Override
    public void end(MethodMonitorEvent methodMonitorEvent) {
        long l = System.currentTimeMillis() - methodMonitorEvent.timestamp;
        System.out.println("耗时"+l);
    }
}
