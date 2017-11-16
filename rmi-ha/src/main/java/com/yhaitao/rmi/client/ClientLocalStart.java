package com.yhaitao.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.yhaitao.rmi.RemoteService;

/**
 * 本地客户端
 * @author admin
 *
 */
public class ClientLocalStart {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		// 组建rmi连接
		String ip = "localhost";
		int port = 7102;
		String rmiUrl = String.format("rmi://%s:%s/%s", ip, port, RemoteService.class.getName());
		System.err.println(rmiUrl);
		
		// 入参
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", 20);
		
		// 远程调用
		RemoteService service02 = (RemoteService) Naming.lookup(rmiUrl);
		Map<String, Object> service = service02.service(params);
		
		System.err.println(service.get("key"));
	}
}
