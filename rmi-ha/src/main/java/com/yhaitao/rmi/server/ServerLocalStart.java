package com.yhaitao.rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.yhaitao.rmi.RemoteService;
import com.yhaitao.rmi.impl.RemoteServiceImpl;

public class ServerLocalStart {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		// 组建rmi连接
		String ip = "localhost";
		int port = 7102;
		String rmiUrl = String.format("rmi://%s:%s/%s", ip, port, RemoteService.class.getName());
		System.err.println(rmiUrl);
		
		// 启动rmi服务
		RemoteService service02 = new RemoteServiceImpl();
		LocateRegistry.createRegistry(7102);
		Naming.bind(rmiUrl, service02);
		System.err.println("Service has started ... ");
	}
}
