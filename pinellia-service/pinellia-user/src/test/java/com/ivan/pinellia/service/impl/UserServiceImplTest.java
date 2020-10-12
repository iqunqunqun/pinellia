package com.ivan.pinellia.service.impl;

import com.ivan.pinellia.UserApplication;
import com.ivan.pinellia.thread.RoleThread;
import com.ivan.pinellia.thread.RoleThreadPool;
import com.ivan.pinellia.thread.ThreadPoolConfig;
import com.ivan.pinellia.dto.UserDTO;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.*;


@SpringBootTest(classes = {UserApplication.class})
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource(name = "rolePool")
    private ExecutorService rolePool;

    @SneakyThrows
    @Test
    public void saveUser() {

        UserDTO userDTO = new UserDTO();
        userDTO.setArrayList(new CopyOnWriteArrayList<>());

        int size = 20;

        CountDownLatch latch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            RoleThread roleThread = new RoleThread(userDTO);
            Future<Object> submit = rolePool.submit(roleThread, userDTO);
//            System.out.println("submit.get().toString() = " + submit.get().toString());
//            System.out.println("userDTO = " + userDTO.toString());
            printList((UserDTO) submit.get());
            latch.countDown();
        }
        latch.await();
        System.out.println("arraySet = " + userDTO.getArrayList().toString());
        System.out.println("userDTO.getArraySet().size() = " + userDTO.getArrayList().size());
    }

    private void printList(UserDTO userDTO) {
        System.out.println("列表： " + userDTO.getArrayList().toString());
    }

    @SneakyThrows
    @Test
    public void deleteUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setArrayList(new CopyOnWriteArrayList<>());

        for (int i = 0; i < 30; i++) {
            RoleThreadPool roleThreadPool = new RoleThreadPool(userDTO);
            String execute = roleThreadPool.execute();
            System.out.println("arraySet = " + userDTO.getArrayList().toString());
            System.out.println("execute = " + execute);
        }

        System.out.println("arraySet = " + userDTO.getArrayList().toString());

    }
}