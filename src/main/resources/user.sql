

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `pwd` varchar(50) NOT NULL COMMENT '用户密码',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `phone` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8