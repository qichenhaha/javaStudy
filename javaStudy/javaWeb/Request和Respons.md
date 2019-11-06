# Request

1.tomacat服务器会根据请求的url中的资源路径，创建对应的ServletDemo01对象

2.tomcat服务器，会创建requset和response对象，requset对象中封装请求的消息数据

3.tomcat讲reuqest和response两个对象传递给Service方法，并且调用Service方法

4.我们通过request对象获取请求的消息数据，通过rresponse对象设置消息数据

5.服务器在给浏览器做出响应之前会从response对象中拿我们设置的响应消息

1. request对象和response对象原理
   - request和response对象是由服务器创建的，我们来使用他们
   - request对象是用来获取请求信息，response对象是来设置响应消息
   
2. request功能

   1. ​	获取请求消息数据
   
      - 获取请求行数据
   
        GET /day14/demo01?name-zhangsan HTTP/1.1
   
        方法：
   
        ​	**1.获取请求方式：GET**
   
        ​		String getMethod()
   
        ​	**2.获取虚拟目录： /day14**
   
        ​		String getContextPath()
   
        ​	**3.获取Servlet路径 /demo01**
   
        ​		String getServletPath()
   
        ​	**4.获取GET方式的请求参数：name-zhangsan**
   
        ​		String getQueryString()
   
        ​	**5.获取请求的URL ： /day14/demo01**
   
        ​		String  getRequestURL()  /day14/demo01
   
        ​		StringBuffer getRequestURL() 	:http://localhost/day14/demo01
   
        ​	**6.获取协议及版本： HTTP/1.1**
   
        ​		String getProtocol()
   
        ​	**7.获取客户机的IP地址**
   
        ​		String getRemoteAddr()
   
      - 获取请求头数据 
   
        方法:
   
        ```
         Enumeration<String> headerNames = req.getHeaderNames();
                while (headerNames.hasMoreElements()){
                    String name = headerNames.nextElement();
                    String header = req.getHeader(name);
                    System.out.println(name+"----"+ header);
                }
        ```
   
        
   
      - 获取请求体数据
   
        1. 请求体：只有Post请求方法才有请求体，在请求体中封装了POST请求参数
        2. 步骤
           - 获取流对象
           - 再从流对象中拿数据
   
      - 其他功能
   
        ​	1.获取请求参数通用方法		
   
        ```
       1.String getParmeter(String name)： 根据参数名称获取参数值
         2.String[] getParamerterValues(String name): 根据参数名称			获取参数值的数组
        ```
        
           			  3. 
   
   2. 请求转发
   
      1. 一种在服务器内部的资源跳转方式
      2.  
   
   