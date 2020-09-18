# api项目

## 技术点 
1. springboot 
2. mybatis
	- 多数据源 √
	- 分页
3. 日志 
    - logback 滚动日志配置 √
	- lombok sl4j √
4. restful风格 
5. 工具类
	- hutool-all √
	- druid-spring-boot-starter √
6. 异常处理
7. AOP切面拦截
8. 定时任务Quartz
9. 异常处理
	- 错误代码（枚举）
	- API形式的接口封装返回格式
	- 模板页面请求的异常返回错误页面
10. 前端vue界面管理
11. 功能页面设计
    - 接口列表
        - 接口详情页面
        - 输入接口测试参数显示返回数据
    - 定时任务列表
    - 首页
        - 接口情况汇总
        - 定时任务情况汇总
    - 错误列表页面
        - 错误详情

## 坑
1. spring容器中只能存在一个同名been，即扫描的包中不能存在同名类或接口
2. 在配置多数据源时，application.yml文件中spring.datasource.{数据源}.url无法被识别，应修改为spring.datasource.{数据源}.jdbc-url
3. mysql driver-class-name:由com.mysql.jdbc.Driver修改为com.mysql.cj.jdbc.Driver

## 知识点
1. @Configuration注解：用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法
2. @ConfigurationProperties注解：用于获取application.properties或application.yml文件中参数的值
3. @Qualifier注解
4. DataSourceBuilder类
5. 读写分离概念：
	- 主数据库（primary）进行增删改操作
	- 从数据库（secondary）进行查操作，定期从主数据库备份数据
	- 把查数据的工作放在热备份的数据库中，降低第一台数据库的压力，但增加了从数据库的不稳定性，在尽可能节约资金的情况下保证数据安全
6. @RestController注解：返回json而不是view视图
7. @primary注解：在多个平级的been中优先选择
8. @Transactional注解
9. hutool-all工具类库
10. alibaba-druid连接池类库
11. GsonFormat插件Ctrl+s

## 思路
1. 接口控制，数据库加标签，aop切面判断
	- 调用接口前判断接口标签字段是否可用，禁用状态返回统一页面或字符串
2. 接口数据、实时动态存入数据库并展示
	- 数据库表结构
	- 接口可用标签
	- 接口调用日志
		- 接口入参、出参
		- 接口错误信息
3. 数据库-配置表-根据配置表生产新的实体表
4. 接口实体
    - 启用/关闭
    - http/https
    - post/get/update/delete
    - 请求参数
        - 参数
        - 参数说明
    - 返回结果
        - 参数
        - 参数说明
    - 接口标签
    - 接口分组
    - 接口调用次数
    - 接口报错次数
    - 报错日志
5. 2个项目分开，通过数据库表关系连接操作
    - 日志文件按天存入本地文档并记录路径存入数据库
    - 接口项目获取所有requestMapping中的url并存入数据库
        - 单独的更新接口按钮，每次更新会对比是否有新接口路径出现（for循环list存在跳过，不存在创建新记录）
    - 管理后台通过数据库中接口表数据进行展示并进行控制（修改数据库接口启用/关闭字段）
    - 每次调用后或报错后切面拦截记录接口调用次数和报错信息
## 坑
1. 找不到mapper的情况 

    pom.xml中<build>标签下增加： 

           <resources>
                <resource>
                    <directory>${basedir}/src/main/java</directory>
                    <includes>
                        <include>**/*.yml</include>
                        <include>**/*.xml</include>
                    </includes>
                </resource>
                <resource>
                    <directory>${basedir}/src/main/resources</directory>
                </resource>
            </resources> 
   
   并在application.yml配置文件mybtis配置部分使用如下格式的mapper-locations
   >mapper-locations: com.liminghua.api
   
   目前测试了多种形式
   
    - resources绝对路径 ×
    - resources相对路径 ×
    - java       包路径 √
    
    推测原因为自定义注入数据源与某部分代码冲突导致springboot用传统的classpath找不到路径，待验证原因。
    
    报错信息
    
        org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.liminghua.api.sysb.mapper.UserMapper.getAllUser
    	at org.apache.ibatis.binding.MapperMethod$SqlCommand.<init>(MapperMethod.java:235)
    	at org.apache.ibatis.binding.MapperMethod.<init>(MapperMethod.java:53)
    	at org.apache.ibatis.binding.MapperProxy.lambda$cachedInvoker$0(MapperProxy.java:107)
    	at java.util.concurrent.ConcurrentHashMap.computeIfAbsent(ConcurrentHashMap.java:1660)
    	at org.apache.ibatis.binding.MapperProxy.cachedInvoker(MapperProxy.java:94)
    	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:85)
    	at com.sun.proxy.$Proxy76.getAllUser(Unknown Source)
    	at com.liminghua.api.mapper.UserMapperTest.selectAll(UserMapperTest.java:34)
    	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    	at java.lang.reflect.Method.invoke(Method.java:498)
    	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
    	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
    	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    	at org.springframework.test.context.junit4.statements.RunBeforeTestExecutionCallbacks.evaluate(RunBeforeTestExecutionCallbacks.java:74)
    	at org.springframework.test.context.junit4.statements.RunAfterTestExecutionCallbacks.evaluate(RunAfterTestExecutionCallbacks.java:84)
    	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
    	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
    	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
    	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
    	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:251)
    	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:97)
    	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
    	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
    	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:190)
    	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    	at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
    	at org.junit.vintage.engine.execution.RunnerExecutor.execute(RunnerExecutor.java:40)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
    	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
    	at java.util.Iterator.forEachRemaining(Iterator.java:116)
    	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
    	at org.junit.vintage.engine.VintageTestEngine.executeAllChildren(VintageTestEngine.java:80)
    	at org.junit.vintage.engine.VintageTestEngine.execute(VintageTestEngine.java:71)
    	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:229)
    	at org.junit.platform.launcher.core.DefaultLauncher.lambda$execute$6(DefaultLauncher.java:197)
    	at org.junit.platform.launcher.core.DefaultLauncher.withInterceptedStreams(DefaultLauncher.java:211)
    	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:191)
    	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:128)
    	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:69)
    	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:33)
    	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:230)
    	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:58)
