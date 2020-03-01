package com.ivan.pinellia.feign;

import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author Chill
 */
@Component
public class IUserClientFallback implements IUserClient {
	@Override
	public R<String> getValue(String code, Integer dictKey) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<Dict>> getList(String code) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<UserVO> detail(Integer uid) {
		return R.fail("获取ID获取用户详情失败");
	}

	@Override
	public R<UserVO> detailByAccount(String account) {
		return R.fail("通过账号获取用户详情失败");
	}
}
