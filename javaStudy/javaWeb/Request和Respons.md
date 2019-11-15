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
         1. 通过request对象获取请求转发对象：RequestDispatcher getRequestDispatcher(String path)
         2. 使用RequestDispatcher对象进行转发：forward(ServletRequest request,ServletResponse response)
      
      2. 特点
      
         1. 浏览器地址路径不发生变化
         2. 只能转发到当前服务器内部资源中
         3. 转发是一次请求
      
      3. 共享数据：
      
         1. 域对象 一个有作用范围的对象，可以在范围能去共享数据
      
         2. request域：代表一次请求的范围，一般用于请求转发多个资源中共享数据
      
         3. 方法
      
            1. setAttribute(String name,Object obj); 存储数据
            2. Object getAttitude(String name); 通过键获取值
            3.  removeAttribute(String name);通过键值对移除键值对
      
         4. 获取ServletContext
      
            ```
            ServletContext servletContext = req.getServletContext();
            ```
   
   BeanUtils工具类，简化数据封装
   
   ​	1.javaBean：标准的java类型
   
   ​				1.类必须是public修饰
   
   ​				2.必须提供空的构造器
   
   ​				3.成员变量必须使用price修饰
   
   ​				4.提供公有的setter和getter方法
   
   ​	2.概念
   
   ​		成员变量
   
   ​	属性：setter和getter方法截取后的产物
   
   ​	列如：getUsername() --> Username-->username
   
   ​	3.方法
   
   ​	setProperty
   
   ​	getProperty
   
   ​	poulate
   
   
   
   Http协议
   
   ​	1.请求消息: 客户端发送给服务器的数据
   
   ​	
   
   重定向
   
   1. ​	重定向方法
   
      1. ```java
         resp.sendRedirect("/Response_war_exploded/ResponseDemo02");
         ```
   
   2. 重定向的特点
   
      1. 地址栏发生改变
      2. 重定向可以访问其他站点（服务器）的资源
      3. 重定向是两次请求，不能使用response对象传递数据
   
   3. 转发的特点
   
      1. 转发到的地址栏路径不边
      2. 转发只能访问当前服务器下的资源
      3. 转发是一次请求，可以共享数据
   
   4. forward和redirect区别
   
      1. 路径的写法:
   
         1. 相对路径，通过相对路径不可以确定唯一资源
            1. 如： ./index.html
            2. ../: 后退一级目录
         2. 绝对路径： 通过就对路径可以确定唯一资源
            1. 如果/Response_war_exploded/ResponseDemo01
            2. 以/开头路径
            3. 规则： 判断定义的路径是给谁用的？判断请求路径从哪儿发出
               1. 给客户端浏览器使用 ： 需要加虚拟目录（项目的访问路径）
                  1. 建议使用虚拟目录动态获取：req.getContextPath();
               2. 给服务器使用： 不需要加虚拟目录
                  1. 转发路径
   
      2. 服务器输出字符流
   
         1. 乱码处理
   
            1. 告诉浏览器，服务器的消息体的编码，建议浏览器使用的编码，是在获取流之前获取
   
               resp.setContentType("text/html;charset=utf-8");
   
      3. 服务器输出字节流
   
         1. ```java
            resp.setContentType("text/html;charset=utf-8");
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write("你好".getBytes("utf-8"));
            ```
   
            ​		
   
      4. 验证码
   
         1. 本质是一张图片
         2. 目的：防止恶意表单注册
   
3. ServletContext对象 

   1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信
   2. 功能：
      1. 获取MIME类型
         - MIME类型：在互联网通信过程中定义的一种文件数据类型
         - 格式： 大类型/小类型  text/html 
         - 获取 String mimeType = servletContext.getMimeType(filename);
      2. 域对象：共享数据
         - setAttrbute(String name,Object value)
         - getAttribute(String name)
         - removeAttribute(String name)
         - ServletContext对象访问:所有用户请求数据
      3. 获取文件的真实（服务器）路径
         1. 获取文件服务器路径web目录下的资源
            1. context.getRealPath("/b.txt")
            2. context.getRealPath("/WEB-INF/c.txt")

4. 文件下载需求

   1. 文件下载
      1. 页面显示超连接
      2. 点击超连接后弹出下载提示框
      3. 完成图片文件下载
   2. 分析
      1. 超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框，不能满足需求，
      2. 任何资源都必须弹出下载提示框
      3. 使用响应头设置资源的打开方式
   3. 步骤
      1. 定义页面，编辑超连接href属性，指向Sevlet，传递资源名称filename
      2. 定义Servlet
         1. 获取文件名称
         2. 使用字节流加载文件进内存
         3. 指定response的响应头：content-disposition:attachment;filename=xxx
         4. 将数据写出response输出流

