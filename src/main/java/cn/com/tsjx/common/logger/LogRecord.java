package cn.com.tsjx.common.logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LogRecord {

	private Map<String, Object> datas = new HashMap<String, Object>();
	private long start = System.currentTimeMillis();

	public LogRecord add(String key, Object value) {
		this.datas.put(key, value);
		return this;
	}

	public LogRecord addAll(Map<String, Object> attrs) {
		this.datas.putAll(attrs);
		return this;
	}

	public LogRecord stop(String key) {
		long time = System.currentTimeMillis() - start;
		return this.add(key, time);
	}

	public static LogRecord newInstance() {
		return new InnerLogRecord();
	}

	private static class InnerLogRecord extends LogRecord implements Map<String, Object> {

		private InnerLogRecord() {
		}

		public int size() {
			return super.datas.size();
		}

		public boolean isEmpty() {
			return super.datas.isEmpty();
		}

		public boolean containsKey(Object key) {
			return super.datas.containsKey(key);
		}

		public boolean containsValue(Object value) {
			return super.datas.containsValue(value);
		}

		public Object get(Object key) {
			return super.datas.get(key);
		}

		public Object put(String key, Object value) {
			return super.datas.put(key, value);
		}

		public Object remove(Object key) {
			return super.datas.remove(key);
		}

		public void putAll(Map<? extends String, ? extends Object> m) {
			super.datas.putAll(m);
		}

		public void clear() {
			super.datas.clear();
		}

		public Set<String> keySet() {
			return super.datas.keySet();
		}

		public Collection<Object> values() {
			return super.datas.values();
		}

		public Set<java.util.Map.Entry<String, Object>> entrySet() {
			return super.datas.entrySet();
		}
	}
}
