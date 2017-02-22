package qcyy.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qc.dm.datasource.DataSourceSwitch;
import qc.dm.service.MybatisDao;

import qc.com.util.DataSouceConst;
import qc.mysql.test.IMysqlTest;

@Service
public class MySqlTestService implements IMysqlTest{

	@Autowired
	MybatisDao		mybatis;
	
	public Map<String, Object> getList(Map<String, Object> req) {
		
		DataSourceSwitch.setDataSourceType(DataSouceConst.MYSQLUC);
		Map<String,Object> resMap = mybatis.get("DemoMapper.testuc", req);
		
		return resMap;
	}

}
