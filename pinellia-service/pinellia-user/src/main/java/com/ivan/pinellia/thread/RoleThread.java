package com.ivan.pinellia.thread;

import cn.hutool.core.util.RandomUtil;
import com.ivan.pinellia.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * <p></p>
 *
 * @author ivan
 * @className RoleThread
 * @since 2020/9/15 20:50
 */

@Slf4j
public class RoleThread implements Runnable {

    private UserDTO userDTO;

    public RoleThread(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public void run() {
        userDTO.getArrayList().add(String.valueOf(RandomUtil.randomInt(1,5)));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("线程{}正在执行", Thread.currentThread().getName());
    }


    /*@Override
    public Object call() throws Exception {
        userDTO.getArrayList().add(RandomUtil.randomString(1));
        Thread.sleep(2000);
        log.info("线程{}正在执行", Thread.currentThread().getName());
        return userDTO;
    }*/


}
