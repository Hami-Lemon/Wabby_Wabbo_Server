-- 建立数据库
drop database if exists wabby;
create database wabby;
use wabby;

-- 建立Tips表
drop table if exists Tips;
-- 帖子表
create table Tips
(
    -- 主键
    id      INT PRIMARY KEY AUTO_INCREMENT,
    -- 发贴日期
    date    DATETIME    NOT NULL,
    -- 帖子类型(最多10个字)
    type    VARCHAR(30) NOT NULL,
    -- 点赞数
    starNum INT         NOT NULL DEFAULT 0,
    -- 帖子内容
    content TEXT        NOT NULL
) engine = innodb,
  charset = utf8mb4;

-- 建立comments表
drop table if exists Comments;
-- 评论表
create table Comments
(
    -- 主键
    id      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    -- 评论内容
    content TEXT            NOT NULL,
    -- 点赞数
    starNum INT             NOT NULL DEFAULT 0,
    -- 发布日期
    date    DATETIME        NOT NULL,
    -- 该评论所属的帖子id,外键
    tips_id INT             NOT NULL,
    FOREIGN KEY (tips_id) REFERENCES Tips (id)
) engine = innodb,
  charset = utf8mb4;

-- 建立查询的存储过程(分页查询 基于子查询优化)
drop procedure if exists query_tips_by_type_with_page;
-- 分页查询tips的存储过程
CREATE PROCEDURE query_tips_by_type_with_page(
    -- 类型筛选
    IN tips_type varchar(6),
    -- 页数
    IN page INT,
    -- 每页容量
    IN page_size INT)
BEGIN
    DECLARE start_num INT UNSIGNED DEFAULT page_size * (page - 1);
    select *
    from Tips ot
    where ot.type = tips_type
      and ot.id <= (
        select id
        from Tips it
        where it.type = tips_type
        order by it.id desc
        limit start_num,1
    )
    order by id desc
    limit page_size;
END;

-- 分页查询comments的存储过程
drop procedure if exists query_comments_by_type_with_page;
CREATE PROCEDURE query_comments_by_type_with_page(
    -- 所属帖子的id
    IN tid INT,
    -- 页数
    IN page INT,
    -- 每页容量
    IN page_size INT)
BEGIN
    DECLARE start_num INT UNSIGNED DEFAULT page_size * (page - 1);
    select *
    from Comments oc
    where oc.tips_id = tid
      and oc.id <= (
        select id
        from Comments ic
        where ic.tips_id = tid
        order by ic.id desc
        limit start_num,1
    )
    order by id desc
    limit page_size;
END;
