package top.lizy.jsonz.client.handler;

import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.DataR;

public abstract class CallHandler {

    public FailReason fr = FailReason.SUCCEED;

    public abstract void success(Data c, DataR r, Object att);

    public abstract void fail(Data c, Object att);

}
