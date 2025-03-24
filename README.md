# Tlias-Web-Management

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-v3.3.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-v3.0.4-blue.svg)](https://mybatis.org/mybatis-3/)
[![License](https://img.shields.io/badge/License-Apache%202.0-yellow.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## 项目简介

Tlias-Web-Management 是一个基于 **Spring Boot** 框架构建的企业级 Web 管理系统，主要用于管理企业内部的部门、员工、学生等信息，并提供增删改查功能。此外，系统还集成了文件上传到阿里云 OSS、日志记录等功能，确保高效、安全、易用。

### 核心功能
- **增删改查**：支持对部门、员工、学生等信息的全面管理。
- **文件上传**：集成阿里云 OSS，实现文件的安全存储与管理。
- **日志记录**：运用 AOP 技术自动记录系统操作日志，助力问题排查与系统优化。
- **用户认证与权限管理**：基于 JWT（HS256 加密算法）实现用户身份认证及权限控制。
- **性能优化**：通过 SQL 调优和代码优化，提升系统响应速度和稳定性。

---

## 技术栈

| 类别       | 技术/工具                     |
|------------|-------------------------------|
| 后端框架   | Spring Boot                   |
| ORM 框架   | MyBatis                       |
| 文件存储   | 阿里云 OSS                    |
| 安全认证   | JWT (HS256)                   |
| AOP        | Spring AOP                    |
| 数据库     | MySQL                         |
| 分页插件   | PageHelper                    |
| 日志管理   | SLF4J                         |
| 前端框架   | ElementUI                     |
| 反向代理   | Nginx                         |

---

## 项目亮点

1. **JWT 用户认证**  
   - 基于 HS256 加密算法实现用户认证与权限管理，确保数据传输的安全性。
   
2. **AOP 自动日志记录**  
   - 运用 AOP 技术自动记录系统操作日志，支持日志查询与分析，助力快速定位问题。

3. **RESTful API 设计**  
   - 使用 Spring Boot 注解配合 MyBatis 编写高效 SQL，设计规范化的 RESTful API，确保接口高效、稳定。

4. **性能优化**  
   - 对数据库和代码进行性能分析与优化，提升系统响应速度，满足高并发需求。

---

## 快速开始

### 1. 环境准备
- JDK 17+
- Maven 3.8+
- MySQL 5.7+
- 阿里云 OSS 账号（用于文件存储）

### 2. 克隆项目
bash git clone https://github.com/your-repo/Tlias-Web-Management.git

### 3. 配置数据库
编辑 `src/main/resources/application.yml` 文件，配置数据库连接信息：
yaml
spring: 
  datasource:
    url: jdbc:mysql://localhost:3306/tlias?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: your_password

### 4. 配置阿里云 OSS
编辑 `src/main/resources/application.yml` 文件，配置阿里云 OSS 参数：
yaml
aliyun:
  oss:
    endpoint: https://oss-cn-beijing.aliyuncs.com
    bucketName: your_bucket_name
    region: cn-beijing

### 5. 启动项目
bash
mvn clean spring-boot:run

访问地址：`http://localhost:8080`

---

## 项目结构
![image](https://github.com/user-attachments/assets/5ad0387f-2166-420b-8e01-1e8e0f4d0744)

---

---

## 贡献指南

欢迎为本项目贡献代码！请遵循以下步骤：
1. Fork 本项目。
2. 创建新分支：`git checkout -b feature/your-feature-name`
3. 提交更改：`git commit -m "Add some feature"`
4. 推送分支：`git push origin feature/your-feature-name`
5. 提交 Pull Request。

---

## License

本项目采用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。详情参见 [LICENSE](LICENSE) 文件。

---

## 联系方式

如果有任何问题或建议，请随时联系作者：
- Email: 3025194514@qq.com

感谢您的关注和支持！

