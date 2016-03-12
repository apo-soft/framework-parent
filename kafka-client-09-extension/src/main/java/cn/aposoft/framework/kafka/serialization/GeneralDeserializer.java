/**
 * 
 */
package cn.aposoft.framework.kafka.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * 反序列化全部Serializable对象的通用序列化工具 *
 * <p>
 * 本方法的实例未引用外部资源,所有内存资源自动释放,不会发生memory leak.
 * 
 * @author Jann Liu
 * 
 */
public class GeneralDeserializer implements Deserializer<Serializable> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to do
	}

	@Override
	public Serializable deserialize(String topic, byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new ByteArrayInputStream(data));
			Serializable s = (Serializable) in.readObject();
			return s;
		} catch (ClassNotFoundException e) {
			// this might happen
			throw new SerializationException("Error when deserializing the given bytes.");
		} catch (IOException e) {
			// this will never happen
			throw new SerializationException("Error when deserializing the given bytes.");
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// this will never happen
				}
				in = null;
			}
		}

	}

	@Override
	public void close() {
		// nothing to do
	}

}
