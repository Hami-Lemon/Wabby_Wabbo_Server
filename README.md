## 匿名论坛主要功能

1. 发布
2. 评论
3. 分类
4. 热帖推荐（点赞、评论数）
5. 热评推荐（点赞数）
6. 文本审查（阿里云API服务）

## 数据库设计

### Tips表

| *id* | date |  type  | starNum | content |
| :--: | :--: | :----: | :-----: | :-----: |
| int  | datetime | varchar |   int   | varchar  |

### Comment表

| *id* | content | starNum | date | floor | Tips_id |
| :--: | :-----: | :-----: | :--: | :---: | :-----: |
| int  | varchar  |   int   | Datetime |  int  |   int   |

## 后端接口

### JSON格式约定

#### 发布(Post) —— url “/posttips”

```json
请求
{
    "data": {
        (Tips对象)
    }
}
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String)
}
```

#### 获取帖子(Get) —— url "/gettips?type="

根据帖子类型获取帖子

```json
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String),
    "data": [
        (Tips对象),
        (Tips对象),
        ...
    ]
}
```

#### 根据id获取帖子(Get) —— url "/getdetail?id="

```json
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String),
    "data": {
        (Tips对象)
    }
}
```

#### 发布评论(Post) —— url "/postcomment"

```json
请求
{
    "data": {
        (Comment对象)
	}
}
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String)
}
```

#### 获取评论(Get) —— url "/getcomment?id="

根据id获取评论

```json
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String),
    "data": [
        (Comment对象),
        (Comment对象),
        ...
    ]
}
```

#### 获取热帖推荐(Get) —— url "/gethottips"

```json
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String),
    "data": [
        (Tips对象),
        (Tips对象),
        ...
    ]
    上述Tips对象，为点赞数和评论数综合后降序排列(前10)
}
```

#### 获取热评推荐(Get) —— url "/gethotcom?id="

根据帖子id获取点赞数前3的评论

```json
响应
{
    "code": 状态码(int),
    "msg": 错误信息(String),
    "data": [
        (Comment对象),
        (Comment对象),
        ...
    ]
}
```

