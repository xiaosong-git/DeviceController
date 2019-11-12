package com.xs.dc.JSch;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.FileSystem.Classpath;

public class ShellUtils {

    /**
     * 创建session
     * @param host 主机名称/ip地址
     * @param user 登陆用户名
     * @param psw  密码
     * @param port 端口
     * @return
     */
    public static Session getSession(String host,String user,String psw,int port){
        JSch jsch=new JSch();
        Session session=null;
        try {
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
        } catch (JSchException e) {
            System.out.println("连接linux主机失败");
            e.printStackTrace();
        }
        return session;

    }
    /**
     * 得到可以执行命令的连接
     * @param session 连接session
     * @return 可以执行命令的ChannelExec
     */
    public static ChannelExec getChanel(Session session){
        ChannelExec openChannel=null;
        try {
            if(null !=session){
                openChannel = (ChannelExec) session.openChannel("exec");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return openChannel;
    }
    /**
     * 
     * @param openChannel
     * @param command
     * @return
     */
    public static String getExcRes(ChannelExec openChannel,String command){
        InputStream in =null;
        BufferedReader reader=null;
        StringBuffer result=new StringBuffer();
        try {
            try {
                openChannel.setCommand(command);
                int exitStatus = openChannel.getExitStatus();
                System.out.println(exitStatus);
                openChannel.connect();
                in = openChannel.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String buf = null;
                while ((buf = reader.readLine()) != null) {
                    result.append(new String(buf.getBytes("gbk"),"UTF-8")+"<br>\r\n");
                }
//reader.close();
            } catch (JSchException e) {
                result.append(e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            result.append(e.getMessage());
            e.printStackTrace();
        } /*finally {
//try {
//reader.close();
//} catch (IOException e) {
//e.printStackTrace();
//}
        }*/
        return result.toString();

    }

    public static ChannelShell ChannelShell(Session session) {
        ChannelShell channel = null;
        try {
            if (null != session) {
                channel = (ChannelShell) session.openChannel("shell");
                channel.connect();
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channel;
    }


    public  static String executeNewFlow(ChannelShell channel, String command) {
        InputStream in =null;
        OutputStream out=null;
        String  msg=null;
        BufferedReader reader=null;
        StringBuffer result=new StringBuffer();
        try {
            in  =  channel.getInputStream();
            out =  channel.getOutputStream();
            out.write(command.getBytes());
            out.flush();
//            reader = new BufferedReader(new InputStreamReader(in));
//            while ((msg =reader.readLine()) !=null ){
//                System.out.println(msg);
//            }
            byte[] tmp=new byte[1024];
            while (true){
                int i=0;
                //线程等待 200ms
                Thread.currentThread().sleep(200);
                while(in.available()>0){
                    i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                }
                //System.out.print(new String(tmp, 0, i));
                return new String(tmp, 0, i);
//                if(channel.isClosed()){
//                    if(in.available()>0) continue;
//                    System.out.println("exit-status: "+channel.getExitStatus());
//                    break;
//                }
            }
        }catch (Exception e){
            result.append(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }




    public static void disConnect(Session session,ChannelExec openChannel){
        if(session!=null&&!openChannel.isClosed()){
            openChannel.disconnect();
        }
        if(session!=null&&session.isConnected()){
            session.disconnect();
        }
    }
    
    public static  List<String> getLinux(String cmd, ChannelShell channelShell) throws UnsupportedEncodingException {
//      控制台 打印 模拟linux 黑窗口
      //System.out.println("\n-----------------Linux Shell-----------------\n");
      //System.out.println("执行命令： " + cmd);
      String res="";
//      判断 输入的命令  如果是 这三个 展示命令 会将 渲染的颜色取消  否则 变成 字符串 会出现乱码
      if(cmd.equals("ls") || cmd.equals("ll") ||cmd.equals("vi")){
          res = executeNewFlow(channelShell, cmd +" --color=never"+ " \n");
          //System.out.println(res);
      }else {
//          否则就正常输出命令
           res = ShellUtils.executeNewFlow(channelShell, cmd + " \n");

      }
//      遍历执行结果 按照 \r\n 分隔
      String[] str ={} ;
      str = res.split("\r\n");
      List<String> list = new ArrayList();
      for (String st:str){
          list.add(st);
      }
//          将结果返回给前台页面
          return list;

      }


    public static void main(String[] args) throws IOException {
    	try {
    		Process prs = Runtime.getRuntime().exec("cmd /c start mstsc -url ssh://Administrator:wgmhao123 /v:192.168.1.11");
    		int exitValue = prs.waitFor();
		} catch (Exception e) {
			System.out.println("打开xshell异常");
			// TODO: handle exception
		}
    	//String[] filePath = new String[] {"D:/远程访问工具/xshell.exe","root:inkwash@xiaosong#2018@47.99.209.40:22"};
    	    }
    
    public static void scanFile(File thefile, String fileName, List<String> list){
		//这一句也是调试用的，可以直接将thefile传入下面的语句。
		File file = thefile;
		File[] files = file.listFiles();
		//正常情况下如果文件夹为空，file.listFiles();得到的值应该是files = [];这样的话程序是无误的，
		//但是有时候会出现返回为Null的情况。暂时不知道是什么原因，但是我们依然可以把它当作[]来处理进行跳过。
		//所以需要加这样一句话。将null变成File[0];
//		if(files == null)
//		{
//			files = new File[0];
//		}
		//上面是其中一种思路，其实直接采用下面的思路即可。
		//之前没用采用下面这种思路，是担心遇到file == null的时候递归会中断。
		//看来还是对递归的了解不够深入。
		if(files != null)
		{
		for(File f : files)
		{
			if(f.isFile())
			{
				//这一段是测试在console里面打印输出所有扫描到的文件，仅作调试使用。
				try
				{
					System.out.println("查找中:" + f.getCanonicalPath());
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//若有需要，可以同时传入参数，限制文件的名字。或者用FileFilter也行。
//				if(f.getName().substring(f.getName().length()-3).equals("xml"))
				
				//使用str1.indexOf(str2),只要文件名中包含str2就可以了。
				//用于模糊查找。
				if(f.getName().indexOf(fileName) >= 0)
				{
					try
					{
						list.add(f.getCanonicalPath());
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if(f.isDirectory())
			{
				scanFile(f, fileName, list);
			}
		}
		}
	}

}
