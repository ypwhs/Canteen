package top.lizy.jsonz.data;


public class DataR extends Data {
    public static final int PROPER = 1;
    public static final int FAIL = 2;
    public static final int REJECT = 3;
    public static final int TRYAGAIN = 4;
    public static final int DATAINVALID = 5;
    private static final long serialVersionUID = -3706403702407373846L;
    public int r = FAIL;

    @Override
    public void Validate() throws ValidateException {
        super.Validate();

        if (r == PROPER || r == FAIL || r == REJECT || r == TRYAGAIN) {
            // proper
        } else throw new ValidateException("不支持的Request Status。");
    }

}
