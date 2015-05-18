package top.lizy.bayi.data;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.ValidateException;

public class RegVerAns_c extends Data {

    private static final long serialVersionUID = 599048405463701022L;
    public UUID g_id;
    public String answer;
    /**
     * 验证码格式为6位数字。
     */
    protected Pattern p_answer = Pattern.compile("^\\s*\\d{6}\\s*$");

    @Override
    public void Validate() throws ValidateException {
        super.Validate();

        if (g_id == null || answer == null)
            throw new ValidateException("not complete");

        Matcher m = p_answer.matcher(answer);
        if (!m.matches())
            throw new ValidateException("code format");

        answer = answer.trim();
    }

}
