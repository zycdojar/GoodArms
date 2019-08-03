package cn.lliiooll.exception;

import cn.lliiooll.GoodArms;

public class ThrowException {

    public ThrowException(Exception exception, String msg) {
        GoodArms ga = new GoodArms();
        ga.error("致命的错误: " + msg);
        if (ga.debug) {
            ga.error("DEBUG模式已开启。更多信息：");
            exception.printStackTrace();
        }
    }
}
