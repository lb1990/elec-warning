package com.ruoyi.system;

import java.net.InetAddress;

import com.ghgande.j2mod.modbus.io.ModbusTCPTransaction;
import com.ghgande.j2mod.modbus.msg.WriteSingleRegisterRequest;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;
import com.ghgande.j2mod.modbus.procimg.SimpleRegister;

public class ModbusTCPExample {
    public static void main(String[] args) {
        try {
            // 创建Modbus主站
            TCPMasterConnection connection = new TCPMasterConnection(InetAddress.getByName("127.0.0.1"));
            connection.setPort(502);
            connection.connect();

            // 创建Modbus请求并执行
            ModbusTCPTransaction transaction = new ModbusTCPTransaction(connection);
            transaction.setRequest(new WriteSingleRegisterRequest(0, new SimpleRegister(12345)));
//            transaction.setRequest(new net.wimpi.modbus.msg.WriteSingleRegisterRequest(40001, new SimpleRegister(12345)));
            transaction.execute();

            // 处理响应
            if (transaction.isCheckingValidity()) {
                System.out.println("Modbus请求失败：" + transaction.getResponse().getMessage());
            } else {
                System.out.println("Modbus请求成功");
            }

            // 关闭连接
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}