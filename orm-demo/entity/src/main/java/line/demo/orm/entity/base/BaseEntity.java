package line.demo.orm.entity.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON.toJSONString()
 * 
 * @author line
 */
public abstract class BaseEntity {

	@Override
	public String toString() {
//		JSONObject.DEFFAULT_DATE_FORMAT += ".SSS";
		return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
