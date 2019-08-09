import com.alibaba.fastjson.JSON;
import com.netzoom.common.annotation.FieldName;
import com.netzoom.common.model.BaseModel;
import com.netzoom.common.model.SuccessModel;
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
		SuccessModel successModel = new SuccessModel();
		BaseModel result = CommonUtil.validateParamsBlankAndNull(successModel,"result","message");
		if ("fail".equals(result.getResult())){
			//do something
			System.out.println(JSON.toJSONString(result));
		}
	}
}
