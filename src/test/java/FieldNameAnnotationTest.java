import com.alibaba.fastjson.JSON;
import com.netzoom.common.annotation.FieldName;
import com.netzoom.common.model.BaseModel;
import com.netzoom.common.util.CommonUtil;
import com.netzoom.common.util.FieldNameHandler;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 字段名解析器单元测试
 * @author tanzj
 */
public class FieldNameAnnotationTest {

	/**
	 * 字段名解析器单元测试
	 */
	@Test
	public void fieldNameResolve_test(){
		FieldNameHandler fieldNameHandler = new FieldNameHandler();
		fieldNameHandler.resolveFieldName(new BaseModel());
	}

	/**
	 * 空值校验器单元测试
	 * @throws NoSuchFieldException
	 */
	@Test
	public void validateParamsBlankAndNull_test() throws NoSuchFieldException {
		BaseModel baseModel = new BaseModel();
		BaseModel result = CommonUtil.validateParamsBlankAndNull(baseModel,"result","message");
		if ("fail".equals(result.getResult())){
			System.out.println(JSON.toJSONString(result));
		}
	}
}
