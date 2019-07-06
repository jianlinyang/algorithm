package code;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yang
 * @date 2019/7/6 11:00
 */
public class MyLRU {
    private int size;
    private LRUMap lruMap;

    public MyLRU(int size) {
        this.lruMap = new LRUMap(size);
    }

    public int get(int key) {
        if (lruMap.containsKey(key)) {
            Integer value = lruMap.get(key);
            set(key, value);
            return value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        if (lruMap.containsKey(key)) {
            lruMap.remove(key);
        }
        lruMap.put(key, value);
    }

    class LRUMap extends LinkedHashMap<Integer, Integer> {
        private int size;

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > size;
        }

        public LRUMap(int size) {
            this.size = size;
        }
    }
}
