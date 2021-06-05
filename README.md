## 匿名论坛主要功能

1. 发布
2. 评论
3. 分类
4. 热帖推荐（点赞、评论数）
5. 热评推荐（点赞数）
6. 文本审查（阿里云API服务）

## 数据库设计

建表文件为`build.sql`建议一条一条地执行里面的语句， `data.sql`中有用于测试的数据

### Tips表

|    id    |       date        |     type     |  starNum   |  content  |
| :------: | :---------------: | :----------: | :--------: | :-------: |
| int 主键 | datetime 发贴日期 | varchar 分类 | int 点赞数 | text 内容 |

### Comment表

|    id    |  content  |  starNum  |       date        |        tips_id        |
| :------: | :-------: | :-------: | :---------------: | :-------------------: |
| int 主键 | text 内容 | int点赞数 | datetime 发布日期 | int 所属帖子的id,外键 |

## 后端接口

### JSON格式约定

#### 发布(Post) —— `url “/posttips”`

```json
请求
{
    "data": {
        //(Tips对象)
    }
}
响应
{
    "code": "状态码(int)",
    "msg": "错误信息(String)"
}
```

#### 获取帖子(Get) —— `url "/gettips?type=xxx&page=xx"`

根据帖子类型获取帖子

参数说明：

- type为帖子类型
- page为页数，持久层采用分页查询的方式

```json
响应
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

```json
响应
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": {
       // (Tips对象)
    }
}
```

#### 发布评论(Post) —— `url "/postcomment"`

```json
请求
{
    "data": {
       // (Comment对象)
	}
}
响应
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

```json
响应
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

```json
响应
{
    "code": "状态码(int)",
    "msg": "错误信息(String)",
    "data": [
        //(Tips对象),
        //(Tips对象),
        //...
    ]
    上述Tips对象，为点赞数和评论数综合后降序排列(前10)
}
```

#### 获取热评推荐(Get) —— `url "/gethotcom?tid=xx"`

根据帖子id获取点赞数前3的评论

参数说明：

- `tid`: 评论所属帖子的id， 因为是获取到一个帖子下面评论中的热评

```json
响应
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

