/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : jt_helper

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 04/09/2023 23:58:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jt_helper
-- ----------------------------
DROP TABLE IF EXISTS `jt_helper`;
CREATE TABLE `jt_helper`  (
  `id` bigint NOT NULL COMMENT '主键',
  `notebook` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '核心文章',
  `sign` bit(1) NULL DEFAULT b'0' COMMENT '是否星标',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助手' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jt_helper
-- ----------------------------
INSERT INTO `jt_helper` VALUES (1698714287729549314, '# 安装nginx并配置托管dist\n\n## 配置文件\n\n```js\nserver {\n    listen       80;\n    server_name  www.520jt.cn;\n    return 301 https://$host$request_uri;\n}\nserver {\n    listen       443 ssl http2;\n    server_name  www.520jt.cn;\n    root         /usr/share/nginx/html/dist;  # 静态博客的存放位置\n\n    ssl_certificate \"/root/app/nginx/520jt.cn_nginx/520jt.cn_bundle.crt\";  # 证书路径\n    ssl_certificate_key \"/root/app/nginx/520jt.cn_nginx/520jt.cn.key\";  # 证书密钥路径\n    ssl_session_cache shared:SSL:50m;  # ssl session cache分配50m空间, 缓存ssl session\n    ssl_session_timeout  1d;  # ssl session 超时时间为1天\n    ssl_session_tickets off;  # ssl session ticket 机制, 部分版本有bug, 视情况开启.\n\n    ssl_protocols TLSv1.2;  # ssl 协议版本\n    ssl_ciphers \'ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256\';  # ssl ciphers\n    ssl_prefer_server_ciphers on;  # 倾向于使用server端的ciphers\n\n    # HSTS 6 months\n    add_header Strict-Transport-Security max-age=15768000;  \n    # 添加个http header, 告诉浏览器直接转到https, 此功能有风险, 慎重选择. \n    # (比如你的证书过期忘记续了, 那么用户想转到http都没办法)\n\n    ssl_stapling on;  # 启用ssl OCSP stapling功能, 服务端主动查询OCSP结果, 提高TLS效率\n    ssl_stapling_verify on;  # 开启OCSP stapling 验证\n\n    # Load configuration files for the default server block.\n    # include /etc/nginx/default.d/*.conf;  # 我的博客的location在这里配置\n\n\n       location /api/ {\n            proxy_pass http://127.0.0.1:520/;\n            proxy_redirect off;\n            proxy_set_header Host $host;\n            proxy_set_header    X-Read-IP       $remote_addr;\n            proxy_set_header    X-Forwarded-For $proxy_add_x_forwarded_for;\n        }\n\n\n    error_page 404 /404.html;\n        location = /40x.html {\n    }\n\n    error_page 500 502 503 504 /50X.html;\n        location = /50X.html {\n    }\n}\n```\n\n\n## 方法一，使用epel源   \n\n### 安装epel源\n\n```bash\nyum -y install epel-release\nyum -y install nginx\nsystemctl start nginx.service\nsystemctl enable nginx.service\nnginx -v   检查安装版本\n重启\n/usr/sbin/nginx -s stop\n/usr/sbin/nginx -s reload\n```\n\n配置文件位置：\n\n> /etc/nginx\n\n- 查找默认的安装位置\n\n```bash\nfind / -name *nginx*\ncd /usr/share/nginx\n```\n\n- 查看启动的service配置\n\n```bash\n[root@localhost /] cat /etc/systemd/system/multi-user.target.wants/nginx.service\n```\n\n- 文件配置解读\n\n```bash\n[root@localhost /] cat /etc/nginx/nginx.conf\n```\n\n变量说明\n\n\n\n| 变量名                | 含义                                      |\n| :-------------------- | ----------------------------------------- |\n| $remote_addr          | 用以记录客户端的ip地址                    |\n| $http_x_forwarded_for | 用以记录客户端的ip地址                    |\n| $remote_user          | 用来记录客户端用户名称                    |\n| $time_local           | 用来记录访问的时间与时区                  |\n| $request              | 用来记录请求的url与请求协议（http/https） |\n| $status               | 记录请求状态：成功时200等                 |\n| $body_bytes_send      | 记录发送给客户端文件主体内容大小          |\n| $http_referer         | 记录从哪个页面链接过来的                  |\n| nginx                 | 基于域名的虚拟主机                        |\n\n\n​	\n\n​		\n​	', b'0', '2023-09-04 23:07:01', '2023-09-04 23:07:01');

-- ----------------------------
-- Table structure for jt_label
-- ----------------------------
DROP TABLE IF EXISTS `jt_label`;
CREATE TABLE `jt_label`  (
  `id` bigint NOT NULL COMMENT '主键',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `jt_label_pk`(`label` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jt_label
-- ----------------------------

-- ----------------------------
-- Table structure for jt_user
-- ----------------------------
DROP TABLE IF EXISTS `jt_user`;
CREATE TABLE `jt_user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `account` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jt_user
-- ----------------------------
INSERT INTO `jt_user` VALUES (1, 'juntian', '123', '76d80224611fc919a5d54f0ff9fba446', NULL, NULL);

-- ----------------------------
-- Table structure for label_helper
-- ----------------------------
DROP TABLE IF EXISTS `label_helper`;
CREATE TABLE `label_helper`  (
  `label_id` bigint NOT NULL COMMENT '标签id',
  `helper_id` bigint NOT NULL COMMENT '笔记id',
  INDEX `helper_fk`(`helper_id` ASC) USING BTREE,
  INDEX `label_fk`(`label_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签-笔记' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label_helper
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
