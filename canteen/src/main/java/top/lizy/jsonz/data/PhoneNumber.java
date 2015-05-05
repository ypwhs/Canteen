package top.lizy.jsonz.data;

public class PhoneNumber extends Data {
	
	private static final long serialVersionUID = 3464988342031667428L;

	public PhoneNumber(String s){
		this.s = s;
	}
	
	@Override
	public void Validate() throws ValidateException{
		super.Validate();
		
		if(s == null || !s.matches("^\\d{11}$")){
			throw new ValidateException("Phone Format");
		}
	}

	public String s;
}
