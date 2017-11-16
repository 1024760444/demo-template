package com.yhaitao.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * 
 * @author yhaitao
 *
 */
public interface RemoteService extends Remote {
	/**
	 * 业务实现。
	 * @param params 业务参数列表
	 * @return 返回结果列表
	 */
	public Map<String, Object> service(Map<String, Object> params) throws RemoteException;
}
