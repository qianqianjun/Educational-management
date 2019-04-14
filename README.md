# 三组组内公告(仅放在本组分支)
## 本周任务(第7周)
- 本周完成Service层和Dao层的基本开发
- Dao层包括 Dao,Domain,Mapper,Test
- 服务层包括：Service,Test
- 前端框架构思和实现
## 完成度
### 孟庆强
完成了power表格的Dao和Service，督促组员进行任务
## 分工
1. 孟庆强:权限表，负责整周任务调度
2. 段浩南和李展鹏: 负责成绩单个录入的前端
3. 刘威负责学生表，李金东负责学期表，王家伟负责老师表
4. 陆俊健负责文档撰写，具体要求查看wiki中要求和组长通知
## 杂项
- 遇到不会的问题群里交流
- 用idea进行git使用方便一些奥
- 本周周记继续由段浩南撰写
- 数据库表更新于微信群,可能继续更新变动,做好改代码心里准本.
- pull项目后需要更改的点 application中的端口号 数据库账号 密码
## 要求
### 全体同学
- 必须完成任务，否侧我会搞你的
- 在自己的文件html或者java等标注出作者
- git提交的时候写出自己更新的信息
- 每完成一点，提交到git该分支一点，并且每次更新都要在该md文件中的完成报告栏目写出自己的工作任务完成了哪些
- 勤奋点，省的让被人BBBBB
### 前端同学
   本周要求你实现小批量学生数据插入的界面，基于女生挖出的界面，如果没有的话自己根据
   html代码风格写，要求如下:
   - 可以一个一个加入，在vue.js 前端先储存,最后点击按钮一并提交到后端中
   - 再未提交到后段之前可以查看自己添加了那些学生的内容，并且可以删除(在vue.js中)
   - 条件查询学生信息构思，查看女生是否完成该页面，如果未完成，自己构思
### 后端同学
所有方法都要测试，写方法注释和类注释
## 组长更新了权限表的例子包括
### PowerDao层:
- buct.software.dao.PowerDao
- buct.software.domain.Power
- mapper/PowerMapper.xml
- buct.software.mapperTest.PowerDaoTest

### PowerService层:
- buct.software.service.PowerService
- buct.software.ServiceTest.PowerServiceTest

# 通知公告

- 现在已经成功导入了css 和 js 资源，有了简单界面，各组负责后端的同学clone 项目到自己电脑试着运行一下。注意： JDK 使用 1.8 数据库使用 mysql 8.0，运行成功之后输入 http://localhost:8080/index 即可看到登录界面，更多界面接口请看 IndexControler 中的定义

- 各组组长注意，在domain 中建立自己子系统对应的表，更新到github 中。

- 排课子系统的数据库还有些问题，尽快找我重新设计一下。

- 各组某些负责移动端网页开发的同学，你们当我是傻子么？ 让你们练习的代码直接用现成的？ 好了，我暂时不追究了，只希望开发的时候别给我掉链子，钻空子的同学你们尽快补上遗漏的技能。还有，别把我当傻子，傻子当不了你们团长！以后老实点 \[捂脸]。

# 作业发布：

作业详情请看下面的地址：[作业地址](https://github.com/qianqianjun/Educational-management/blob/master/worksheet1.md)

## 软件工程项目

---

### 各个分支名称

- 排课子系统： ClassScheduling

- 选课子系统：SelectCourse

- 毕业设计子系统： GraduationProject

- 成绩管理子系统： GradeManagement

- 后台管理系统：BackstageManagement

- #### 使用git branch 可以查看自己所在组的分支

### 初始任务

- 所有成员完成 Jetbrains Intelij IDEA的安装，完成Java 开发环境的搭建，编写运行第一个Java Hello world 程序

- 所有成员完成 git 的安装，注册github 账号。

- 确保安装好了mysql 数据库。

- 确保安装好了 Google Chrome 浏览器

- 简单学习一下 [vue.js](http://www.runoob.com/vue2/vue-tutorial.html) （相信大家之前都已经完成了Html ，css ,js 的基础语法的学习，不熟悉的再看一下）

### 数据库设计（暂定）

- 排课子系统：

  课程表：

  | cno | cname | description | college | status       |     |     |     |
  | --- | ----- | ----------- | ------- | ------------ | --- | --- | --- |
  | 课程号 | 课程名   | 课程描述        | 开课学院    | 课程状态（未审核/审核) |     |     |     |

  排课表：

  | semester | cno | tno  | capacity | classroom | start_week | end_week | teach_time |
  | -------- | --- | ---- | -------- | --------- | ---------- | -------- | ---------- |
  | 学期       | 课程号 | 教师工号 | 课程容量     | 教室编号      | 教学起始周      | 教学结束周    | 上课时间（1-3）  |

- 选课子系统：

  学生表：

  | sno | sname | sex | major | klass | come_year | grade |     |
  | --- | ----- | --- | ----- | ----- | --------- | ----- | --- |
  | 学号  | 姓名    | 性别  | 专业    | 班级    | 入学年份      | 年级*   |     |

  选课表：

  | semester | sno | cno | tno    | grade |     |     |     |
  | -------- | --- | --- | ------ | ----- | --- | --- | --- |
  | 学期       | 学号  | 课程号 | 任课教师工号 | 课程成绩  |     |     |     |

- 成绩管理子系统：

  主要操作选课表

- 后台管理系统：

  学期表：

  | start_year | end_year | semester |     |     |     |     |     |
  | ---------- | -------- | -------- | --- | --- | --- | --- | --- |
  | 起始学年       | 终止学年     | 学期号      |     |     |     |     |     |
