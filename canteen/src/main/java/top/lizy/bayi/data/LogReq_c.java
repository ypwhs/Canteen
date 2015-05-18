package top.lizy.bayi.data;

import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.PhoneNumber;
import top.lizy.jsonz.data.ValidateException;

public class LogReq_c extends Data {

    private static final long serialVersionUID = 2615727920204685412L;

    public PhoneNumber phone;

    @Override
    public void Validate() throws ValidateException {
        super.Validate();

        phone.Validate();
    }
}
