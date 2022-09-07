/*
 Navicat MySQL Data Transfer

 Source Server         : Java
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : stu_sys

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/09/2022 04:33:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `cid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('A0001', 'Java程序设计', 4);
INSERT INTO `courses` VALUES ('B0001', '大学英语', 2);
INSERT INTO `courses` VALUES ('C0001', '高等数学', 5);
INSERT INTO `courses` VALUES ('A0002', '游戏数学与物理', 2);
INSERT INTO `courses` VALUES ('C0002', '线性代数', 2);
INSERT INTO `courses` VALUES ('D0001', '军事理论', 1);
INSERT INTO `courses` VALUES ('A0003', '三维建模基础', 3);

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `sid` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `java` int NULL DEFAULT NULL,
  `english` int NULL DEFAULT NULL,
  `mathematics` int NULL DEFAULT NULL,
  `game` int NULL DEFAULT NULL,
  `algebra` int NULL DEFAULT NULL,
  `military` int NULL DEFAULT NULL,
  `modeling` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES (150806101, '范雄钦', 97, 90, 88, 83, 96, 91, 85);
INSERT INTO `scores` VALUES (150806104, '马睿', 47, 47, 8, 24, 43, 95, 0);
INSERT INTO `scores` VALUES (150806105, '潘雅建', 84, 87, 64, 70, 76, 95, 89);
INSERT INTO `scores` VALUES (150806118, '陈雯颖', 84, 73, 60, 67, 77, 84, 79);
INSERT INTO `scores` VALUES (150806121, '冯靖涵', 90, 80, 65, 86, 81, 97, 81);
INSERT INTO `scores` VALUES (150806122, '何佳妮', 94, 83, 68, 84, 73, 85, 91);
INSERT INTO `scores` VALUES (150806125, '康婕', 92, 78, 62, 71, 86, 86, 74);
INSERT INTO `scores` VALUES (150806126, '李锐', 92, 75, 69, 65, 73, 91, 88);
INSERT INTO `scores` VALUES (150806130, '刘芮菡', 92, 80, 82, 77, 80, 86, 84);
INSERT INTO `scores` VALUES (150806136, '吴涛', 99, 82, 97, 91, 97, 95, 86);
INSERT INTO `scores` VALUES (150806137, '许诺', 97, 81, 87, 90, 92, 88, 86);
INSERT INTO `scores` VALUES (150806138, '许晓妍', 86, 75, 72, 79, 80, 83, 86);
INSERT INTO `scores` VALUES (150806139, '俞婕', 95, 86, 77, 86, 94, 91, 93);
INSERT INTO `scores` VALUES (150806140, '岳艺芯', 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `scores` VALUES (150806141, '张甜', 78, 82, 73, 60, 83, 73, 78);
INSERT INTO `scores` VALUES (150806142, '张斓', 83, 76, 60, 61, 70, 86, 80);
INSERT INTO `scores` VALUES (150806145, '周瑶瑶', 94, 85, 73, 72, 79, 86, 93);
INSERT INTO `scores` VALUES (150806205, '鹿成', 81, 77, 63, 63, 73, 87, 87);
INSERT INTO `scores` VALUES (150806206, '毛怡明', 96, 85, 77, 80, 87, 84, 88);
INSERT INTO `scores` VALUES (150806215, '安琪', 89, 88, 71, 69, 86, 91, 91);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (150806140, '岳艺芯', '女', 18);
INSERT INTO `student` VALUES (150806136, '吴涛', '女', 18);
INSERT INTO `student` VALUES (150806101, '范雄钦', '男', 18);
INSERT INTO `student` VALUES (150806137, '许诺', '女', 18);
INSERT INTO `student` VALUES (150806130, '刘芮菡', '女', 18);
INSERT INTO `student` VALUES (150806206, '毛怡明', '男', 18);
INSERT INTO `student` VALUES (150806139, '俞婕', '女', 18);
INSERT INTO `student` VALUES (150806141, '张甜', '女', 18);
INSERT INTO `student` VALUES (150806145, '周瑶瑶', '女', 18);
INSERT INTO `student` VALUES (150806138, '许晓妍', '女', 18);
INSERT INTO `student` VALUES (150806215, '安琪', '女', 18);
INSERT INTO `student` VALUES (150806126, '李锐', '女', 18);
INSERT INTO `student` VALUES (150806122, '何佳妮', '女', 18);
INSERT INTO `student` VALUES (150806121, '冯靖涵', '女', 18);
INSERT INTO `student` VALUES (150806105, '潘雅建', '男', 18);
INSERT INTO `student` VALUES (150806205, '鹿成', '男', 18);
INSERT INTO `student` VALUES (150806125, '康婕', '女', 18);
INSERT INTO `student` VALUES (150806142, '张斓', '女', 18);
INSERT INTO `student` VALUES (150806118, '陈雯颖', '女', 18);
INSERT INTO `student` VALUES (150806104, '马睿', '男', 18);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '123');

SET FOREIGN_KEY_CHECKS = 1;
