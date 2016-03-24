package vo;

import utils.JsonUtils;

public class ResultVO {
	private String code;
	private String desc;
	public ResultVO() {
		
	}
	public ResultVO(String code,String desc) {
		this.code=code;
		this.desc=desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String toString() {
		return JsonUtils.toString(this);
	}
}
