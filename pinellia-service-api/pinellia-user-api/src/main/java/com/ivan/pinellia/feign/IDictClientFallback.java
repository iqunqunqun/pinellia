package com.ivan.pinellia.feign;

import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author Chill
 */
@Component
public class IDictClientFallback implements IDictClient {
	@Override
	public R<String> getValue(String code, Integer dictKey) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<Dict>> getList(String code) {
		return R.fail("获取数据失败");
	}
}
