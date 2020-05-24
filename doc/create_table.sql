-- `action` definition

CREATE TABLE `action` (
  `id` bigint(20) unsigned NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '结果类型；0：执行结果；1：执行规则集',
  `result` varchar(255) DEFAULT NULL COMMENT '执行结果',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- atomic_fact definition

CREATE TABLE `atomic_fact` (
  `id` bigint(15) unsigned NOT NULL COMMENT '主键',
  `atomic_fact_function` varchar(255) DEFAULT NULL COMMENT '方法',
  `operator` varchar(255) DEFAULT NULL COMMENT '原子事实操作符',
  `value` varchar(255) DEFAULT NULL COMMENT '右值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='原子事实表，一行再加上对应的表行代表一个原子事实，其真值取决于表中的数据';


-- fact definition

CREATE TABLE `fact` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL,
  `atomic_id` bigint(20) unsigned DEFAULT '0' COMMENT '为0则不为原子事实，非0则对应到原子事实 表的某个记录',
  `type` smallint(1) DEFAULT '0' COMMENT '事实类型，0为默认普通事实（或原子事实），1为组合事实',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='事实表';


-- fact_connective definition

CREATE TABLE `fact_connective` (
  `parent_fact` bigint(20) unsigned NOT NULL COMMENT '父事实',
  `child_fact` bigint(20) unsigned NOT NULL COMMENT '子事实',
  `connective` varchar(255) DEFAULT NULL COMMENT '联结词',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`parent_fact`,`child_fact`) USING BTREE,
  KEY `connective` (`connective`) USING BTREE,
  KEY `child_fact` (`child_fact`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='事实关联表，记录事实之间的树形联结结构';


-- risk_param definition

CREATE TABLE `risk_param` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `params` json DEFAULT NULL COMMENT '风控参数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- rule definition

CREATE TABLE `rule` (
  `id` bigint(20) unsigned NOT NULL,
  `fact_id` bigint(20) unsigned DEFAULT NULL,
  `action_id` bigint(20) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- rules definition

CREATE TABLE `rules` (
  `id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则集，若干规则的集合，由若干个rule组成，将作为决策的起点';


-- rules_rule definition

CREATE TABLE `rules_rule` (
  `rules_id` bigint(20) unsigned NOT NULL COMMENT '策略id',
  `rule_id` bigint(20) unsigned NOT NULL COMMENT '规则id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rules_id`,`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则集-规则关系表，保存指定规则集下有哪些规则';


-- action_rule definition

CREATE TABLE `action_rule` (
  `action_id` bigint(20) unsigned NOT NULL COMMENT '结果id',
  `rule_id` bigint(20) unsigned NOT NULL COMMENT '规则id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`action_id`,`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='结果id-规则关系表，保存指定规则集下有哪些规则';