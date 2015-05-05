package top.lizy.bayi.data;

import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.ValidateException;

public class StateChange_c extends Data {

	private static final long serialVersionUID = 5858509116850151988L;

	public static final int NOTHING = 0;
	public static final int USER_LOGIN = 1;
	public static final int USER_LOGOUT_MANUALLY = 2;
	public static final int LOGOUT_BY_SYSTEM = 3;
	public static final int LOGOUT_BY_ANOTHER = 4;
	
	public int ty = NOTHING;
	public long account = -1;
	
	@Override
	public void Validate() throws ValidateException {
		super.Validate();
	}

}
