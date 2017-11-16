package com.yhaitao.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import com.yhaitao.rmi.RemoteService;

/**
 * 
 * @author yhaitao
 *
 */
public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoteServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 业务实现。
	 * @param params 业务参数列表
	 * @return 返回结果列表
	 */
	public Map<String, Object> service(Map<String, Object> params) throws RemoteException {
		Object key = params.get("key");
		if(key != null) {
			System.err.println(key);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new HashMap<String, Object>(params);
	}
}
