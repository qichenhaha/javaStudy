## servlet: server applet

 概念：运行在服务器端的小程序
	Servlet就是一个接口，定义了java类型被浏览器访问到（tomacat识别）的规则
	将来我们自定义一个类，实现servlet接口，复写方法

### 快速入门

​	1.创建javaEE项目
​	2.定义一个类，实现servlet接口

```
public class ServletDemo01 implements Servlet
```

​	3.实现接口中的抽象方法
​	4.配置servlet
​	在web.xml配置Servlet

```xml
<!--配置servlet--> 
<servlet>
       	<servlet-name>demo01</servlet-name>
        <servlet-class>com.heidan.servlet.ServletDemo01</servlet-class>
   </servlet>
<!--  配置servlet映射路径  -->
    <servlet-mapping>
        <servlet-name>demo01</servlet-name>
        <url-pattern>/demo01</url-pattern>
    </servlet-mapping>
```

### Servlet 执行流程

1.当前服务器接受到客户端 的请求后，会解析请求的url路径，获取访问的Servlet的资源路径

2.查询web。xml文件，是否有对应的<url-pattern>标签体内容

3.如果有在找到对应的<servlet-class>对应的全类名

4.tomacat会，将字节码文件加载进内存，并且创建其方法

5.调用其方法



### Servler中的生命周期

1. 被创建：执行init方法 只执行一次

   Servlet什么时候被创建？

   - 默认情况下第一次，被访问
   - 的时候，Servlet被创建

   - 可以配置执行Servlet的创建时机

     - 在<servlet>标签下

     -  <指定Servlet的创建时机，

     - 1.第一次访问的时候，创建  <load-on-startup>的值为负数  

     - 2.在服务器启动的时候创建的值为0或者正整数。默认为-1

       Servlet的init方法，只执行一次，说明了一个Servlet在内存中只存在一个对象，Servlet是单例的，解决：尽量不要在Servlet中订单成员变量，即使定义了成员变量也不要对其修改值。

       

2. 提供服务： 执行service方法 ，执行多次

   - 每次访问Servlet时，Service方法都会调用一次

3. 被销毁：  执行destroy放，只会执行一次

   - Servlet被销毁时，执行，服务器关闭时，Servlet被销毁
   - 只有服务器正常关闭时，才会执行destroy方法
   - destroy方法在Servlet 销毁之前执行，一般用与释放资源

### Servlet注解

​	Servlet3.0

- 支持注解配置，可以不需要web.xml了

  步骤

  1. 创建javaEE项目，选择Servlet的版本3.0以上

  2. 创建项目的时候可以不创建web.xml

  3. 定义一个类，实现Servlet接口

  4. 复写方法

  5. 在类上使用@webSerlet注解进行配置

     ```java
     @WebServlet("/demo")
     public class Servletdemo01 implements Servlet {
         @Override
         public void init(ServletConfig servletConfig) throws ServletException {
     
         }
     
         @Override
         public ServletConfig getServletConfig() {
             return null;
         }
     
         @Override
         public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
             System.out.println("Servlet3.0来了");
         }
     
         @Override
         public String getServletInfo() {
             return null;
         }
     
         @Override
         public void destroy() {
     
         }
     }
     ```

     ### Servlet的体系结构
     
     ​	 Servet -- 接口
     
     GenericServlet -- 抽象类
     
     HttpServlet -- 抽象类
     
     GenericServlet:将Servlet接口中其他的方法做了默认空实现，只讲Service方法()作为抽象将来定义的Servlet类是，可以继承GeneruceServlet,实现Service()方法即可
     
     
     
     HttpServlet :
     
     对http协议的一种封装，简化操作
     
     - 定义类基础HttpServlet
     - 复写doGet(),doPost()
     
     
     
     ### Servlet相关配置
     
     1.UrlPartten:Servlet访问路径
     
     1. 一个Servlet可以定义多个访问路径:@webSer 
     2. 路径的定义规则
        - /xxx
        - /xxx/xxx 多层路径
        - *.do
     
     
     
     ### Http协议
     
     - 概念：Hyper Test Transfer Protovol 超文本传输协议
     
       - 传输协议: 定义了，客户端请求通信时，发送数据格式
       - 特点
         1. 基于TCP/IP高级协议
         2. 默认端口号：80
         3. 基于请求/响应模型的，一次请求对应一次响应
         4. 无状态的协议，每次请求之前相互独立不能交互数据。
       - 历史版本
         - 1.0 没有给响应都会建立新的连接
         - 1.1 复用连接
     
       请求消息数据格式
     
       1. 请求 行
     
          请求方式   请求url  请求协议/版本 
     
          GET  		/login.html 		HTTP/1.1
     
          请求方式
     
          http 协议有7中请求方式，常用的有两种
     
          ​	GET：
     
          请求的参数在请求行中，在URL后，
     
          请i去的url长度是有限制的，
     
          不太安全
     
          ​	POST：
     
          请求参数在请求体中
     
          请求的url长度是没有限制的
     
          相对安全
     
       2. 请求头
     
          请求名称： 请求头值
     
          常见的请求头
     
          1. 
     
       3. 请求空行
     
       4. 请求体
     
       ​	
     
       
     
     



