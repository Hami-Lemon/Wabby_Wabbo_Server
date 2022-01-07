# 歪比巴卜论坛

这是我们一个软件工程的实验作业，如果你有兴趣，~~可以来逛一逛 ：[歪比巴卜论坛](void(0))(服务器已到期，请在本地运行)~~

这个仓库是歪比巴卜论坛的后端仓库，目前的后端很简单，只能说勉强能用。

前端仓库：[github](https://github.com/Hami-Lemon/Wabby_Wabbo_UI)

## 数据库设计

建表文件为`build.sql`， `data.sql`中有用于测试的数据

数据库使用的是`Mysql`，如果你城需要使用其它的数据库，需要自己修改`build.sql`中的建表语句，以及`c3p0-config.xml`中的链接池配置

### Tips表

|   id    |       date        |     type     |  starNum   |  content  |
| :-----: | :---------------: | :----------: | :--------: | :-------: |
| int主键 | datetime 发贴日期 | varchar 分类 | int 点赞数 | text 内容 |

### Comment表

|    id    |  content  |  starNum  |       date        |        tips_id        |
| :------: | :-------: | :-------: | :---------------: | :-------------------: |
| int 主键 | text 内容 | int点赞数 | datetime 发布日期 | int 所属帖子的id,外键 |

## 接口约定

前后端分离，以json方式交互

#### 发布(Post) —— `url： “/posttips”`

- request

  ```json
  {
      "data": {
          "id": 1,
          "data": "2021-07-01 12:00:00",
          "type": "学习",
          "starNum": 100,
          "content": "内容"
      }
  }
  ```

- response

  ```json
  {
      "code": "200",
      "msg": "错误信息(String),code为200时操作成功"
  }
  ```


#### 获取帖子(Get) —— `url "/gettips?type=xxx&page=xx"`

根据帖子类型获取帖子

参数说明：

- type为帖子类型
- page为页数，持久层采用分页查询的方式

response

```json
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": [
       // (Tips对象),
        //(Tips对象),
        //...
    ]
}
```

#### 根据id获取帖子(Get) —— `url "/getdetail?id="`

参数说明：

- id: 对应帖子的id

response

```json
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": {
       // (Tips对象)
    }
}
```

#### 发布评论(Post) —— `url "/postcomment"`

- request

  ```json
  {
    "id": 1,
    "content": "评论内容",
    "starNum": 20,
    "date": "2021-03-04 12:32:32",
    "tipsId": 25
  }
  ```

- response

  ```json
  {
      "code": "状态码(int)",
      "msg": "错误信息(String)"
  }
  ```

#### 获取评论(Get) —— `url "/getcomment?tid=xxx&page=xx"`

根据id获取评论

参数说明：

- `tid`: 评论所属帖子的id， 因为是获取到一个帖子下的评论
- `page`: 页数，同样采用分布查询的方式

response

```json
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": [
       // (Comment对象),
        //(Comment对象),
        //...
    ]
}
```

#### 获取热帖推荐(Get) —— `url "/gethottips"`

按照点赞数多少进行排序

response

```json
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": [
        //(Tips对象),
        //(Tips对象),
        //...
    ]
}
```

#### 获取热评推荐(Get) —— `url "/gethotcom?tid=xx"`

根据帖子id获取点赞数前3的评论

参数说明：

- `tid`: 评论所属帖子的id， 因为是获取到一个帖子下面评论中的热评

response

```json
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": [
        //(Comment对象),
        //(Comment对象),
        //...
    ]
}
```

#### 搜索帖子(Get）—— `url "/searchtips?content=&page="`

通过关键词字符串匹配搜索帖子

参数说明：

- content: 搜索的内容
- page：页数，默认为1

response

```json
{
    "code": 状态码(int),
    "msg": 错误信息(String),
}
```

#### 增加帖子热度(Get）—— `url "/addtipsstarnum?id=&addNum="`

参数说明：

- id: 对应帖子的id
- addNum: 点赞数

response

```json
{
    "code": 状态码(int),
    "msg": 错误信息(String),
}
```

#### 增加评论热度(Get）—— `url "/addcommentsstarnum?id=&addNum="`

参数说明：

- id: 对应评论的id
- addNum: 点赞数

response

```json
{
    "code": 状态码(int),
    "msg": 错误信息(String),
}
```

#### 获取总页数（Get) -- `url "/getpagenum?type=xxx"`

参数说明：

- type: 帖子的类型

response

```json
{
    "code":200,
    "msg":"OK",
    "data":3
}
```
