package controller;

import org.junit.Test;

/**
 * Created by guogang on 2017/3/6.
 */
public class gitTest {
    public void getMsg(){
        System.out.println("执行方法开始");
        forMsg();
        System.out.println("执行方法结束");
    }

    public void forMsg(){
        for(int i=0;i<10;i++){
            System.out.println("循环的次数："+i);
        }
    }

}
