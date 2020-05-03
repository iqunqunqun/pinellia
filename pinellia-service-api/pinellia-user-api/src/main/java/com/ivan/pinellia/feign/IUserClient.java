package com.ivan.pinellia.feign;


import com.ivan.pinellia.entity.Dict;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.constant.AppConstant;
import com.ivan.pinellia.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	/**
	 * 新增或修改
	 * @param user
	 * @return
	 */
	@PostMapping(USER_API_PREFIX + "/submit")
	@ApiOperation(value = "新增或修改", notes = "传入User")
	R<Boolean> submit(@Valid @RequestBody User user);
}
