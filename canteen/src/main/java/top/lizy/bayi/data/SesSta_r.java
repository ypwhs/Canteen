package top.lizy.bayi.data;

import java.util.*;

import top.lizy.jsonz.data.DataR;

public class SesSta_r extends DataR {

	private static final long serialVersionUID = -1026898124680831865L;

	public static final int OUT = 0;
	public static final int CLIENT = 1;
	public static final int SHOP = 2;
	public static final int ADMIN = 3;
	
	public int ty;
	public Date ts_l;
	public SessionStateDat dat;
}
