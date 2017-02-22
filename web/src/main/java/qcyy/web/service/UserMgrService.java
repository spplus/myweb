package qcyy.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qc.com.log4j.HpLogger;
import qc.com.util.DataSouceConst;
import qc.com.util.ResCode;
import qc.com.util.ResponseUtil;
import qc.dm.datasource.DataSourceSwitch;
import qc.dm.service.MybatisDao;
import qcyy.inter.IUserMgr;

@Service
public class UserMgrService implements IUserMgr{

	
	@Autowired
	MybatisDao		mybatis;
	
	
	/**
	 * 
	 * 验证用户名密码.
	 * 
	 */
	
	/*
	public Map<String,Object> userLogin(Map<String,Object> req){
		
		HpLogger.debug("userLogin..."+req.toString());
	
		Map<String,Object> res = new HashMap<String, Object>();
		
		return ResponseUtil.FormatResMsg(ResCode.SUCCESS, null, "查询执行成功", res, false);
	}
	
	*/


	@Override
	public Map<String, Object> userLogin(Map<String, Object> req) {
		HpLogger.debug("userLogin..."+req.toString());
		
		Map<String,Object> res = new HashMap<String, Object>();
		DataSourceSwitch.setDataSourceType(DataSouceConst.MYSQLUC);
		Map<String,Object> resMap = mybatis.get("UserMgrMapper.checkuserpwd", req);
		
		if(resMap == null){
			return ResponseUtil.FormatResMsg(ResCode.MSG_BAD_CREDENTIALS, null, "查询执行成功", res, false);
		}
		HpLogger.debug(resMap);
		
		return ResponseUtil.FormatResMsg(ResCode.SUCCESS, null, "查询执行成功", res, false);
	}
}
