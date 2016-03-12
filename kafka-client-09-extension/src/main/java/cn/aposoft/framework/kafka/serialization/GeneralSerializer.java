/**
 * 
 */
package cn.aposoft.framework.kafka.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.kafka.common.serialization.Serializer;

/**
 * 序列化全部Serializable对象的通用序列化工具
 * <p>
 * 本方法的实例未引用外部资源,所有内存资源自动释放,不会发生memory leak.
 * 
 * @author Jann Liu
 *
 */
public class GeneralSerializer implements Serializer<Serializable> {
	// 维护outputStream的缓存对象,提升效率 通过对blocking queue的操作实现单一线程控制
	private final static ConcurrentMap<ObjectOutputStream, ByteArrayOutputStream> streamMap = new ConcurrentHashMap<ObjectOutputStream, ByteArrayOutputStream>();

	/**
	 * nothing to do
	 */
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to do
	}

	// 释放out
	private static void release(ObjectOutputStream out) {
		// just keep five cached outStream, others are automatically discarded
		// by the queue and remove the bout
		streamMap.remove(out);
		try {
			out.close();
			out = null;
		} catch (IOException e) {
			// this will never happen
		}
	}

	// 读取ObjectOutputStream的Facade方法
	private final static ObjectOutputStream getObjectOutputStream() {
		return createObjectOutputStream();

	}

	// 读取out 的byte[]
	private final static byte[] getBytes(ObjectOutputStream out) {
		ByteArrayOutputStream bout = streamMap.get(out);
		if (bout != null) {
			byte[] result = bout.toByteArray();
			try {
				out.flush();
			} catch (IOException e) {
				// this will never happen
			}
			return result;
		} else {
			return null;
		}
	}

	// 创建对象输出流
	private final static ObjectOutputStream createObjectOutputStream() {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(bout);
			streamMap.putIfAbsent(out, bout);
		} catch (IOException e) {
			// this will never happen
		}
		return out;
	}

	/**
	 * @see Serializer#serialize(String, Object)
	 */
	@Override
	public byte[] serialize(String topic, Serializable data) {
		if (data == null) {
			return null;
		}
		ObjectOutputStream out = null;
		try {
			out = getObjectOutputStream();
			out.writeObject(data);
			out.flush();
			byte[] result = getBytes(out);
			release(out);
			return result;
		} catch (IOException e) {
			// this should never happen
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// this should never happen
				}
			}
		}
		return null;
	}

	/**
	 * This method has nothing to do
	 */
	@Override
	public void close() {
		// nothing to do
	}

}
