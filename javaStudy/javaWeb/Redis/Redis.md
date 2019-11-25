### Redis

1. 概念

   1. 关系形数据库与Nosql数据库并非对立而是互补的关系，即通常情况下使用关系型数据库，再合适Nosql的时候使用Nosql数据库，让Nosql数据库对关系形数据的不足进行弥补。一般会将数据存储杂交关系型数据库中，再nosql数据库中备份存储关系型数据库的数据。

2. 下载安装

   1. http://www.redis.cn/download.html 下载地址

3. 命令操作

   1. 数据结构

      1. redis存储的是：key,value格式的数据，其中key都是字符串，value有5种不同的数据结构

      * value的数据结构：
      			    	1) 字符串类型 string
      			2) 哈希类型 hash ： map格式  
      			3) 列表类型 list ： linkedlist格式。支持重复元素
      			4) 集合类型 set  ： 不允许重复元素
      			5) 有序集合类型 sortedset：不允许重复元素，且元素有顺序

   2. 字符串类型 string

      1. 存储  **set key value**
      2. 获取   **get key**
      3. 删除 **del key**

   3. 哈希类型

      1. 存储： **hset key field value**
      2. 获取：**hget key field** 
      3. 删除：**hdel key field**
      4. 获取所有的field 和 value ：**hgetall key**

   4. 列表类型

      1. 添加：
         1. :将元素加入列表左边 **lpush key value**
         2. 将元素加入列表右边 **rpush key value**
      2. 获取
         1. **lrange key start end** 范围获取
      3. 删除
         1. **lpop key** 删除列表最左边的元素，并将元素返回
         2. **rpop key** 删除列表最右边的元素，并将元素返回

   5. 集合类型 set ： 不允许重复元素

      1. 存储：sadd key value
      2. 获取set集合中所有元素：smembers key 
      3. 删除：srem key value

   6. 通用命令

      1. **keys*** 查询所有的key
      2. **type key** 查询当前key的类型
      3. **del key** 删除key

4. 持久化操作

   1. redis是一个内存数据库，当redis服务器重启，获取电脑重启，数据就会丢失，我们可以将redis内存中的数据持久化保存再硬盘的文件中

   2. redis持久化机制

      1. RDB: 默认方式，不需要进行配置，默认就使用这种机制

         1. 再一定的间隔时间中，检测key的变化情况，然后持久化数据

            **save 900 1**		900秒内发生一次改变持久化一次
            **save 300 10**		300秒内发生一次改变持久化一次
            **save 60  10000**	60秒内发生一次改变持久化一次

      2. AOF: 在日志记录方式，可以记录每一条命令的操作。可以每一次操作后，持久化数据

5. 使用java客户端操作redis

   

   