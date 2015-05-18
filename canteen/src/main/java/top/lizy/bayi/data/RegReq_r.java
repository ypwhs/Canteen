package top.lizy.bayi.data;

import java.util.UUID;

import top.lizy.jsonz.data.DataR;
import top.lizy.jsonz.data.ValidateException;

public class RegReq_r extends DataR {

    private static final long serialVersionUID = 7802913079485821533L;

    public UUID g_id;

    @Override
    public void Validate() throws ValidateException {
        super.Validate();
    }

}
