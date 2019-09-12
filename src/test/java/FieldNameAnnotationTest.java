import com.alibaba.fastjson.JSON;
import com.netzoom.common.annotation.FieldName;
import com.netzoom.common.model.BaseModel;
import com.netzoom.common.model.PageParam;
import com.netzoom.common.model.SuccessModel;
import com.netzoom.common.util.CommonUtil;
import com.netzoom.common.util.FieldNameHandler;
import com.netzoom.common.util.PageHelperUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

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

	@Test
	public void tesss(){
		Long baseUUID = Math.abs(UUID.randomUUID().getMostSignificantBits()) >> 2;
		System.out.println(baseUUID);
	}

	@Test
	public void test(){
//		System.out.println("----->"+String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits())).substring(10));
//		19位
//		Long baseUUID = Math.abs(UUID.randomUUID().getMostSignificantBits());
//		System.out.println(baseUUID);
//		//当前时间
//		Long currentTime = System.currentTimeMillis();
//		//算法时间
//		String uuid_short = String.valueOf(Math.abs(baseUUID*currentTime)).substring(10);
//		Set set = new HashSet();
//		for (int i = 0;i<10000000;i++){
//			Long baseUUID = Math.abs(UUID.randomUUID().getMostSignificantBits()) >> 2;
//			//当前时间
//			Long currentTime = System.currentTimeMillis();
//
//			//算法时间
//			String uuid_short = String.valueOf(Math.abs(baseUUID*currentTime)+currentTime).substring(0);
//			set.add(uuid_short);
//		}
//		System.out.println(set.size());
	}

	@Test
	public void uuid8_test(){
		
	}

	@Test
	public void page_test(){
		try{
			PageParam pp = new PageParam();
			pp.setOrderType("aa");
			PageHelperUtils.startPage(pp,true,"xixi","aaa","bbb","ccc");
		}catch ( Exception e ){
			System.out.println(e.getMessage());
		}
	}


}
