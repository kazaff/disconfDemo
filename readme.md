## 使用说明

由于演示代码是基于我司一个模版代码来做的，所以需要在测试时，注意几个细节。

在`config.properties`中定义的authToken是用来做验证的，如果在rest请求时没有添加匹配的请求头，则会造成认证失败。

所以推荐使用`Postman`来测试接口，发送请求前，在`Headers`中配置一个参数，例如：

> token 123456


## 测试数据

由于我们的目的是测试mybatis和disconf的结合，所以我们需要至少两个数据库节点。
分别在对应节点下创建对应的数据库，需要为每个数据库创建一个`test`表，该表至少要包含一个`id`字段。

为了方便观察，推荐为每个不同的test表初始化一个不同的id值。

然后在disconf-web提供的后台中创建`db.properties`：

```
url=jdbc:mysql://localhost:33061/demo?useSSL=false
username=root
password=
driver-class-name=com.mysql.jdbc.Driver
```

测试时，只需要动态修改`url`值，保存即可。

然后使用`Postman`访问：`http://127.0.0.1:8081/order/test`来查看返回的数据是否为切换后的目标表。
