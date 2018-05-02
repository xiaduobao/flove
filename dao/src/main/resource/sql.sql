CREATE DATABASE IF NOT EXISTS moments DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use moments;

朋友圈

1 用户模块

create TABLE  'flove_user'{

  'id' bigint(20)  AUTO_INCREMENT not NULL ,
  'nickname' VARCHAR (255) not null,
  'contactInfo' VARCHAR (255) not null comment '手机或是邮箱',
  'img_id' VARCHAR (255) not null ,
  'created_time' datetime NOT NULL,
  'updated_time' datetime DEFAULT NULL,
   PRIMARY  KEY ('id'),
   UNIQUE  KEY  ('contactInfo')
   index  'idx_name' 'nickname',
   index   'dix_create_time',
    'updated_time' datetime DEFAULT NULL,
}


2 状态模块

3 评论模块