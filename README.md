本项目是一个逻辑计算机，可以用做风控规则计算引擎，专家系统。数据可放到数据库中，非常灵活。

# 表、实体类、谓词逻辑的对应关系

|类型      | 标识        | 非标识属性  |
|:-------:|:----------:|:-------------:|
|表格      |主键         | 非主键列 |
|实体类    |主键对应的属性| 非主键列对应的属性  |
|谓词逻辑  | 个体标识     |   谓词 |
    
customer表 

|customer_id      | name     | age  |  sex  |
|:---------------:|:-----:|:------:|:------:|
|1                |张三     | 20   |男   |
|2                |李四      | 60  |女   |

customer.customer_id标识了张三这个个体。
customer.customer_id的name列值为“张三”，相当于如下谓词命题成立：
name(customer.customer_id,"张三")，一个二元关系谓词。

存在量词∃
全称量词∀
∃customer,customer.customer_id=1&&customer.name="张三"

# 值的两种类型

|类型      | 说明     | 
|:--------:|:-----:|
|枚举（字符串） |不能进行算术计算     | 
|数值          |可进行算术计算      | 

sql中的隐式转换会导致索引失效

# 原子事实操作符

|符号      | 含义     | 
|:--------:|:-----:|
|<         |小于     | 
|<=          |小于或等于      | 
|<>          |不等于      | 
|=          |等于      | 
|>         |大于      | 
|>=         |大于或等于  | 
|in          |包含于   | 
|!in          |不包含于   | 

# 真值联结符
|符号      | 含义     | 
|:--------:|:-----:|
|AND         |析取     | 
|NOT          |取反      | 
|OR          |合取      | 
|→       |蕴含      | 
|↔        |等值      | 



# 事实分类

原子事实，其真值从表中获得  
复合事实，由原子事实联结而成的复合事实  
中间事实，复合事实的一种，可以复用来组成其他组合事实  
根事实，中间事实的一种，其值是我们的计算目标  

逻辑引擎的功能是从原子事实的真值计算出根事实的真值  




