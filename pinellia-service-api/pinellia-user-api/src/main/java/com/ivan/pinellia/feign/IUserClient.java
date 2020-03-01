/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ivan.pinellia.feign;


import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.constant.AppConstant;
import com.ivan.pinellia.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign接口类
 *
 * @author Chill
 */
@FeignClient(
		value = AppConstant.APPLICATION_USER_NAME,
		fallback = IUserClientFallback.class
)
public interface IUserClient {

	String DICT_API_PREFIX = "/dict";

	String USER_API_PREFIX = "/user";

	/**
	 * 获取字典表对应值
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	@GetMapping(DICT_API_PREFIX + "/getValue")
	R<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") Integer dictKey);

	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	@GetMapping(DICT_API_PREFIX + "/getList")
	R<List<Dict>> getList(@RequestParam("code") String code);

	/**
	 *
	 * 获取用户详情
	 * @date 2020/2/29 9:55 下午
	 * @param uid:
	 * @return com.ivan.pinellia.tool.api.R<com.ivan.pinellia.vo.UserVO>
	 * @author chenyifan
	 */
	@GetMapping(USER_API_PREFIX + "/detail/{uid}")
	R<UserVO> detail(@PathVariable("uid") Integer uid);

	/**
	 *
	 * 根据账号查询用户
	 * @date 2020/2/29 10:05 下午
	 * @param account:
	 * @return com.ivan.pinellia.tool.api.R<com.ivan.pinellia.vo.UserVO>
	 * @author chenyifan
	 */
	@GetMapping(USER_API_PREFIX + "/detailByAccount")
	R<UserVO> detailByAccount(@RequestParam String account);
}
