1. 会话技术
   - Cookie
   - Session
2. JSP:入门学习



### 会话技术

1. 会话：一次会中包含多次请求和响应
   1. 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一个方断开为止
   2. 在一次会话的范围内共享数据
      1. 方式
         - 客户端会话技术:Cookie
         - 服务器端会话技术: Session

### Cookice

1. ​	概念：客户端会话技术，将数据保存在客户端
2. 快速入门
   - ​	使用步骤
     1. ​	创建Cookie对象，保存数据
     2. ​     发送Cookie
     3. ​     获取Cookie拿到数据
   - 实现原理
     - 基于响应头set-cookie和请求头cookie实现
   - Cookie实现
     - 可以创建多个cookie对象，使用response调用多次addCookie方法发送Cookie即可
     - cookie在浏览器中保存的时间，默认情况下，当浏览器关闭后，cookie数据被销毁
     - 设置cookie的生命周期持久化存储
       - setMaxAge(int  seconds)
         1. 正数（讲cookie数据写到硬盘中，持久化存储）
         2. 负数 默认值，存储在浏览器的内存里面，浏览器关闭就没有了
         3. 0  删除cookie信息
     - cookie存储中文
       - 在tomcat8之前 cookie 中 不能直接存储中文
         - 需要在中文数据转码--- 一般采用URL编码（%E3）
   - cookie共享的问题
     - 假设在一个tomcat服务器中，部署的多个web项目，那么在这些web项目中cookie能不能共享
       - 默认情况下，cookie不能共享
       - setPath（String path）: 设置cookie的范围取值，默认情况下，设置当前的虚拟目录
         - 如果要共享，这讲路径设置为 “/”
     - 不同的tomcat服务器间cookie共享问题
       - setDomain（String path）如果设置了一级域名相同，那么多个服务器之间cookie可以共享
       - setDomain(".baidu.com"),那么 tieba.baidu.com和news.baidu.com 中的cookie可以共享
   - cookie的特点
     - cookie存储数据在客户端浏览器，存在客户端的浏览器不是特别安全
     - 浏览器对于单个cookie的大小有限制（4kb），以及同一个域名下的总cookie数量页面有限制的（20个）
     - cookie不能存储空格
   - cookie 案例
     - 记住上一次访问时间
       - 需求
         - 访问一个Servlet,如果第一次访问，则提示：你好，欢迎您首次访问
         - 如果不是第一次访问，则提示：欢迎回来，您上次访问的时间为： 显示时间字符串
3. Session:
   1. 概念: 服务器端会员技术，在一个会话中的多次请求间共享数据，将数据保存在服务器端的对象中.HttpSession
   2. 快速入门
      1. HttpSession 对象
         1. Object getAttribute(String name) 
         2. Void setAttribute(String name,Object value)
         
      2. void removeAttribute(String name) 
         1. 服务器如何确保在第一次会话范围内，多次获取Session对象是同一个
         
         2. 服务器如何在一次会话中，多次获取Sessio对象是同一个
         
            
         
            1. Session 是依赖于Cookie 
         
            2. 客户端第一次访问服务器，服务器会响应一个Set-Cookie：JSESSIONID=5C03851DD87A3AA0D65E4F6661F3D7CA，给存储在浏览器的内部。
         
               1. 当客户端在一次请求当前项目其他资源的时候，就会携带这个
         
               2. Cookie：JSESSIONID=5C03851DD87A3AA0D65E4F6661F3D7CA
         
                  服务器会自动的获取这个Cookie信息，会根据这个Cookie信息，在内存中去找。内存中有没有JSESSIONID为这个的。所有这个getSession就找到了Session对象，返回给Session标记。
         
      3. 细节
      
         1. 当客户端关闭后，服务器端不关闭，两次获取的session是否为同一个？
      
            1. 默认情况下，不是的
      
            2. 解决客户端关闭之后，Session依然可以保持
      
            3. ```
               		// 1.获取Session对象
                       HttpSession session = req.getSession();
                       session.setAttribute("msg","Hello Session");
                       System.out.println(session.getId());
               
                       //2.忘客户端关闭之后Session 也能相同
                       Cookie cookie = new Cookie("JSESSIONID",session.getId());
                       cookie.setMaxAge(60*60);
                       // 3.给Cookie响应的客户端
                       resp.addCookie(cookie);
               ```
      
               
      
         2. 客户端不关闭，服务器端关闭，两次获取的session是同一个吗? 
      
            1. 不是同一个，但是要确保数据不丢失
               - session的钝化
                 - 在服务器正常关闭之前，将Session对象系列化到硬盘上
               - session活化
                 - 在服务器启动后，将session转换为内存中的session对象即可
               - Tomacat服务器已经帮助我们 实现了钝化和活化
               - 原理 当前项目在tomcat中允许，如果服务器关闭之后，会在tomcat work目录中，生成一个.ser的文件，里面存储的session对象，当前tomcat启动，运行这个项目，.ser的文件会自动读取，从工作目录中删除，当客户端在继续访问的时候，服务器还是会有那个session对象
      
         3. Session 什么时候被销毁
      
            1. 服务器关闭
      
            2. sessin对象调用invalidata()
      
            3. session默认失效时间为30分钟，在tomcat中web.xml中可以配置
      
               1. ```
                  <session-config>
                  	<session-timeout>30</session-timeout>
                  </session-config>
                  ```
      
         4. Session 的特点
      
            1. session用于存储一次会话的多次请求的数据，存在服务器端
            2. session可以存储任意类型，任意大小的数据
            3. 