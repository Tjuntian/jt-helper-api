# JT-Helper API

> JT-Helper springboot API service

## 示例

[ http://119.91.239.213:519/](http://119.91.239.213:519/)

## 环境要求

- maven>apache-maven-3.6.1
- redis(缓存验证码)
- springboot3
- jdk>17

## 功能

- 腾讯云存储桶

  需要配置腾讯云存储桶保存图片可以在cn.jt.utils.TXCosUtils中配置

- 笔记CROS

- 标签CROS

- Knife4j接口文档

- jjwt令牌生成

- redis缓存验证码

- 防xss注入

## 部署

- 下载redis并运行
- 创建数据库jt_helper
- 运行sql文件创建表(根目录的jt_helper.sql文件)
- 在application.yml文件中配置mysql和redis
- 运行

## 补充

- 用户表有一个账号可以自行在数据库添加账号密码,密码需要用md5加密,可在网站上进行md5加密并修改数据库
- 初始账号密码
  - 账号:123
  - 密码:qwe

