package qc.com.shiro.bean;

import java.io.Serializable;

/**
 * 公用机构Bean.
 * 
 * @author zhangzg
 */
public class OrgBean implements Serializable {

	/**
	 * 序列化id.
	 */
	private static final long serialVersionUID = 1L;

	/** 机构名称 */
	private String orgName;
	/** 机构代码 */
	private String orgId;
	/** 机构类型代码 */
	private String orgTypeId;
	/** 机构类型名称 */
	private String orgTypeName;
	/** 权限类型：1 查看权限 2 修改权限 */
	private String controlType;
	/** 是否开放注册：0不开放,1开放.  */
	private String isOpenCust;
	
	public String getIsOpenCust() {
        return isOpenCust;
    }

    public void setIsOpenCust(String isOpenCust) {
        this.isOpenCust = isOpenCust;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgTypeId
	 */
	public String getOrgTypeId() {
		return orgTypeId;
	}

	/**
	 * @param orgTypeId
	 *            the orgTypeId to set
	 */
	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	/**
	 * @return the orgTypeName
	 */
	public String getOrgTypeName() {
		return orgTypeName;
	}

	/**
	 * @param orgTypeName
	 *            the orgTypeName to set
	 */
	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	/**
	 * @return the controlType
	 */
	public String getControlType() {
		return controlType;
	}

	/**
	 * @param controlType
	 *            the controlType to set
	 */
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	@Override
	public String toString() {
		return "OrgBean [orgName=" + orgName + ", orgId=" + orgId
				+ ", orgTypeId=" + orgTypeId + ", orgTypeName=" + orgTypeName
				+ ", controlType=" + controlType + "，isOpenCust=" + isOpenCust +"]";
	}

}
