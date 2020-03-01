/*
 Navicat Premium Data Transfer

 Source Server         : root@127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 127.0.0.1:3306
 Source Schema         : logicmachine

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 01/03/2020 20:52:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for atomic_fact
-- ----------------------------
DROP TABLE IF EXISTS `atomic_fact`;
CREATE TABLE `atomic_fact`  (
  `atomic_fact_id` int(11) NOT NULL,
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`atomic_fact_id`) USING BTREE,
  INDEX `fk_op`(`operator`) USING BTREE,
  CONSTRAINT `fk_op` FOREIGN KEY (`operator`) REFERENCES `operator` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of atomic_fact
-- ----------------------------
INSERT INTO `atomic_fact` VALUES (0, '', '', '=', NULL);
INSERT INTO `atomic_fact` VALUES (1, 'customer', 'age', '<', '30');
INSERT INTO `atomic_fact` VALUES (2, 'customer', 'sex', '=', '女');

-- ----------------------------
-- Table structure for connective
-- ----------------------------
DROP TABLE IF EXISTS `connective`;
CREATE TABLE `connective`  (
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '真值联结词' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of connective
-- ----------------------------
INSERT INTO `connective` VALUES ('!', '取反');
INSERT INTO `connective` VALUES ('→', '蕴含');
INSERT INTO `connective` VALUES ('↔', '等值');
INSERT INTO `connective` VALUES ('∧', '合取');
INSERT INTO `connective` VALUES ('∨', '析取');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '张三', 50, '男');
INSERT INTO `customer` VALUES (2, '李四', 20, '女');
INSERT INTO `customer` VALUES (3, '王五', 10, '男');
INSERT INTO `customer` VALUES (4, '陈六', 80, '女');

-- ----------------------------
-- Table structure for fact
-- ----------------------------
DROP TABLE IF EXISTS `fact`;
CREATE TABLE `fact`  (
  `fact_id` int(11) NOT NULL COMMENT '主键',
  `atomic_id` int(11) NULL DEFAULT 0 COMMENT '为0则不为原子事实，非0则对应到原子事实 表的某个记录',
  PRIMARY KEY (`fact_id`) USING BTREE,
  INDEX `actomic_id`(`atomic_id`) USING BTREE,
  CONSTRAINT `fact_ibfk_1` FOREIGN KEY (`atomic_id`) REFERENCES `atomic_fact` (`atomic_fact_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fact
-- ----------------------------
INSERT INTO `fact` VALUES (1, 0);
INSERT INTO `fact` VALUES (2, 0);
INSERT INTO `fact` VALUES (3, 0);
INSERT INTO `fact` VALUES (5, 1);
INSERT INTO `fact` VALUES (4, 2);

-- ----------------------------
-- Table structure for fact_connective
-- ----------------------------
DROP TABLE IF EXISTS `fact_connective`;
CREATE TABLE `fact_connective`  (
  `parent_fact` int(11) NOT NULL,
  `child_fact` int(255) NOT NULL,
  `connective` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`parent_fact`, `child_fact`) USING BTREE,
  INDEX `connective`(`connective`) USING BTREE,
  INDEX `child_fact`(`child_fact`) USING BTREE,
  CONSTRAINT `fact_connective_ibfk_1` FOREIGN KEY (`parent_fact`) REFERENCES `fact` (`fact_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fact_connective_ibfk_2` FOREIGN KEY (`connective`) REFERENCES `connective` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fact_connective_ibfk_3` FOREIGN KEY (`child_fact`) REFERENCES `fact` (`fact_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fact_connective
-- ----------------------------
INSERT INTO `fact_connective` VALUES (1, 2, '∧');
INSERT INTO `fact_connective` VALUES (1, 4, '∧');
INSERT INTO `fact_connective` VALUES (1, 5, '∧');
INSERT INTO `fact_connective` VALUES (2, 4, '∨');
INSERT INTO `fact_connective` VALUES (2, 5, '∨');

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator`  (
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '原子事实标操作符' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('!=', '不等于');
INSERT INTO `operator` VALUES ('!in', '不在');
INSERT INTO `operator` VALUES ('<', '小于');
INSERT INTO `operator` VALUES ('<=', '小于或等于');
INSERT INTO `operator` VALUES ('=', '等于');
INSERT INTO `operator` VALUES ('>', '大于');
INSERT INTO `operator` VALUES ('>=', '大于或等于');
INSERT INTO `operator` VALUES ('in', '存在');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` int(10) NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent` int(10) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1, '父菜单1', NULL);
INSERT INTO `tb_menu` VALUES (2, '父菜单2', NULL);
INSERT INTO `tb_menu` VALUES (3, '父菜单3', NULL);
INSERT INTO `tb_menu` VALUES (4, '父菜单4', NULL);
INSERT INTO `tb_menu` VALUES (5, '父菜单5', NULL);
INSERT INTO `tb_menu` VALUES (6, '一级菜单6', 1);
INSERT INTO `tb_menu` VALUES (7, '一级菜单7', 1);
INSERT INTO `tb_menu` VALUES (8, '一级菜单8', 1);
INSERT INTO `tb_menu` VALUES (9, '一级菜单9', 2);
INSERT INTO `tb_menu` VALUES (10, '一级菜单10', 2);
INSERT INTO `tb_menu` VALUES (11, '一级菜单11', 2);
INSERT INTO `tb_menu` VALUES (12, '一级菜单12', 3);
INSERT INTO `tb_menu` VALUES (13, '一级菜单13', 3);
INSERT INTO `tb_menu` VALUES (14, '一级菜单14', 3);
INSERT INTO `tb_menu` VALUES (15, '一级菜单15', 4);
INSERT INTO `tb_menu` VALUES (16, '一级菜单16', 4);
INSERT INTO `tb_menu` VALUES (17, '一级菜单17', 4);
INSERT INTO `tb_menu` VALUES (18, '一级菜单18', 5);
INSERT INTO `tb_menu` VALUES (19, '一级菜单19', 5);
INSERT INTO `tb_menu` VALUES (20, '一级菜单20', 5);
INSERT INTO `tb_menu` VALUES (21, '二级菜单21', 6);
INSERT INTO `tb_menu` VALUES (22, '二级菜单22', 6);
INSERT INTO `tb_menu` VALUES (23, '二级菜单23', 7);
INSERT INTO `tb_menu` VALUES (24, '二级菜单24', 7);
INSERT INTO `tb_menu` VALUES (25, '二级菜单25', 8);
INSERT INTO `tb_menu` VALUES (26, '二级菜单26', 9);
INSERT INTO `tb_menu` VALUES (27, '二级菜单27', 10);
INSERT INTO `tb_menu` VALUES (28, '二级菜单28', 11);
INSERT INTO `tb_menu` VALUES (29, '二级菜单29', 12);
INSERT INTO `tb_menu` VALUES (30, '二级菜单30', 13);
INSERT INTO `tb_menu` VALUES (31, '二级菜单31', 14);
INSERT INTO `tb_menu` VALUES (32, '二级菜单32', 15);
INSERT INTO `tb_menu` VALUES (33, '二级菜单33', 16);
INSERT INTO `tb_menu` VALUES (34, '二级菜单34', 17);
INSERT INTO `tb_menu` VALUES (35, '二级菜单35', 18);
INSERT INTO `tb_menu` VALUES (36, '二级菜单36', 19);
INSERT INTO `tb_menu` VALUES (37, '二级菜单37', 20);
INSERT INTO `tb_menu` VALUES (38, '三级菜单38', 21);
INSERT INTO `tb_menu` VALUES (39, '三级菜单39', 22);
INSERT INTO `tb_menu` VALUES (40, '三级菜单40', 23);
INSERT INTO `tb_menu` VALUES (41, '三级菜单41', 24);
INSERT INTO `tb_menu` VALUES (42, '三级菜单42', 25);
INSERT INTO `tb_menu` VALUES (43, '三级菜单43', 26);
INSERT INTO `tb_menu` VALUES (44, '三级菜单44', 27);
INSERT INTO `tb_menu` VALUES (45, '三级菜单45', 28);
INSERT INTO `tb_menu` VALUES (46, '三级菜单46', 28);
INSERT INTO `tb_menu` VALUES (47, '三级菜单47', 29);
INSERT INTO `tb_menu` VALUES (48, '三级菜单48', 30);
INSERT INTO `tb_menu` VALUES (49, '三级菜单49', 31);
INSERT INTO `tb_menu` VALUES (50, '三级菜单50', 31);

SET FOREIGN_KEY_CHECKS = 1;
