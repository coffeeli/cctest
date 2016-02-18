package dto;

import utils.DTOUtils;

public abstract class AbstractBean {
	public static final String ID = "_id";
	@Override
	public String toString() {
		return DTOUtils.toString(this);
	}

}
