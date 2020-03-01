# 谓词逻辑

##表、实体类、谓词逻辑

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

值的两种类型：枚举（字符串）、数值

逻辑运算符：
is（字符串，数字）
larger(数字)
less(数字)

∃customer,customer.customer_id=1&&customer.name="张三"

//属性谓词，名字等于某值
function nameIs(customer_id,name){
  var boolean = ∃customer,customer_id=1&&name=name;
  //select count(1) from customer where customer_id=#{customer_id} and name=#{name}
  return boolean;
}

function sexIs(customer_id,sex){
  var boolean = ∃customer,customer_id=1&&sex=sex;
  //select count(1) from customer where customer_id=#{customer_id} and sex=#{sex}
  return boolean;
}

//年龄大于某值
function ageLarger(customer_id,age){
  var boolean = ∃customer,customer_id=customer_id&&customer.age>age;
  //select count(1) from customer where customer_id=#{customer_id} and age > #{age}
  return boolean;
}

function nameIsAndageLarger(customer_id,name,age){
 return nameIs(customer_id,name)&&ageLarger(customer_id,age);
}

#规则集
1的性别是女，sexIs(1,"女")
1的年龄大于20,ageLarger(1,20)
