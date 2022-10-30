# all-in-one

Android应用安全实战 Frida协议分析

第1章 Frida逆向环境搭建

1.1 Frida框架介绍

1.2 Frida框架环境搭建

1.2.1 Python的安装

1.2.2 Visual Studio Code的安装

1.2.3 Node.js的安装

1.2.4 Frida的安装

1.3 Android平台环境搭建

1.3.1 Android系统刷机

1.3.2 获取Android系统管理员权限

1.3.3 frida-server配置

第2章 Frida框架Java层应用

2.1 Frida框架的Hook方法

2.1.1 Hook静态方法和实例方法

2.1.2 Hook构造方法

2.1.3 Hook重载方法

2.1.4 Hook方法的所有重载

2.1.5 对象参数的构造

2.1.6 主动调用Java函数

2.2 Frida框架Hook类

2.2.1 获取和修改类的字段

2.2.2 Hook内部类和匿名类

2.2.3 枚举所有已加载的类和枚举类的所有方法

2.2.4 Hook类的所有方法

2.3 实战：某嘟牛协议分析

2.3.1 某嘟牛Java层登录协议分析

2.3.2 Frida框架辅助协议分析

2.3.3 Frida框架生成加密参数

第3章 关键代码快速定位

3.1 集合的Hook

3.1.1 Hook HashMap定位散列表

3.1.2 打印函数栈

3.1.3 Hook ArrayList定位动态数组

3.2 组件与事件的Hook

3.2.1 Hook Toast定位提示

3.2.2 Hook findViewById定位组件

3.2.3 Hook setOnClickListener定位按钮点击事件

3.3 常用类的Hook

3.3.1 Hook TextUtils定位用户输入

3.3.2 Hook JSONObject定位JSON数据

3.3.3 Hook Collections定位排序算法

3.3.4 Hook String定位字符转换

3.3.5 Hook StringBuilder定位字符串操作

3.3.6 Hook Base64定位编码

3.4 其他类的定位

3.4.1 Hook定位接口的实现类

3.4.2 Hook定位抽象类的实现类

3.5 实战：去除应用程序的强制升级

第4章 算法“自吐”脚本开发

4.1 工具函数封装

4.2 Frida Hook MD5算法

4.2.1 Hook MD5算法update方法

4.2.2 Hook MD5算法digest方法

4.3 Frida Hook MAC算法

4.3.1 Hook MAC算法密钥

4.3.2 Hook MAC算法update方法

4.3.3 Hook MAC算法doFinal方法

4.4 Frida Hook数字签名算法

4.4.1 Hook 数字签名算法update方法

4.4.2 Hook 数字签名算法sign方法

4.5 Objection辅助Hook

4.5.1 Objection的安装和基本使用

4.5.2 实战：某电竞界面跳转

第5章 Frida框架so层基本应用

5.1 获取Module

5.1.1 通过模块名来获取Module

5.1.2 通过地址来获取Module

5.1.3 Process中的常用属性和方法

5.2 枚举符号

5.2.1 枚举模块的导入表

5.2.2 枚举模块的导出表

5.2.3 枚举模块的符号表

5.2.4 Module中的常用属性和方法

5.3 Frida Hook so函数

5.3.1 Hook导出函数

5.3.2 从给定地址查看内存数据

5.3.3 Hook任意函数

5.3.4 获取指针参数返回值

5.3.5 Frida inlineHook获取函数执行结果

5.4 Frida修改函数参数与返回值

5.4.1 修改函数数值参数与返回值

5.4.2 修改字符串参数

5.5 实战：某热点登录协议分析

第6章 JNI函数的Hook与快速定位

6.1 JNI函数的Hook

6.1.1 JNIEnv的获取

6.1.2 枚举libart符号表来Hook

6.1.3 通过计算地址的方式来Hook

6.2 主动调用so函数

6.2.1 Frida API主动调用JNI函数

6.2.2 so层文件打印函数栈

6.2.3 DebugSymbol类

6.2.4 so层主动调用任意函数

6.2.5 通过NativeFunction主动调用JNI函数

6.3 JNI函数注册的快速定位

6.3.1 Hook dlsym获取函数地址

6.3.2 Hook RegisterNatives获取函数地址

6.4 ollvm混淆应用协议分析实战

6.4.1 jnitrace工具的使用

6.4.2 实战：某App应用程序协议分析

第7章 Frida框架so层进阶应用

7.1 Frida操作内存数据

7.1.1 内存读写

7.1.2 Frida修改so函数代码

7.1.3 Frida 从内存中导出 so函数

7.1.4 ollvm字符串解密

7.1.5 构造二级指针

7.1.6 读写文件

7.2 Frida其他常用API介绍

7.2.1 NativePointer类的常用方法

7.2.2 Memory的常用方法

7.2.3 替换函数

7.3 Frida进阶Hook

7.3.1 Hook系统函数dlopen

7.3.2 Hook系统函数JNI_Onload

7.3.3 Hook系统函数initarray

7.3.4 Hook系统函数pthread_create

7.3.5 监控内存读写

7.3.6 函数追踪工具frida-trace

7.3.7 Frida API的简单封装

7.3.8 代码跟踪引擎stalker

7.4 实战：某观察登录协议分析

第8章 Frida框架算法转发方案

8.1 Frida的Python库使用

8.1.1 Frida注入方式

8.1.2 spawn方式启动与连接非标准端口

8.2 Frida与Python交互

8.3 Frida的RPC调用

8.4 实战：某嘟牛Frida算法转发