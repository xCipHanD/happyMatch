
![Logo](./img/logo.png)


# HappyMatch


CS109期末project，模仿了开心消消乐的ui和游戏设计。

相关仓库： [前端](https://github.com/xcipHanD/happyMatch) | [后端](https://github.com/xcipHanD/happyMatchServer)


## 技术栈
  - Java
  - JavaFx
  - JavaLin
  - MySQL

## 功能

- [x] 用户管理
	- [x] 登录 [LoginController](src\main\java\asia\sustech\happymatch\Login\LoginController.java) -> [LoginStage.fxml](src\main\resources\LoginStage.fxml)
	- [x] 注册 [RegisterController](src\main\java\asia\sustech\happymatch\Login\RegisterController.java) -> [RegisterStage.fxml](src\main\resources\RegisterStage.fxml)
	- [x] 找回密码 [ForgetPwdController](src\main\java\asia\sustech\happymatch\Login\ForgetPwdController.java) -> [ForgetPWD.fxml](src\main\resources\ForgetPWD.fxml)
	- [x] 邮箱验证码
- [x] 游戏界面
	- [x] 大厅 [HallController]([Title](src/main/java/asia/sustech/happymatch/Hall/HallController.java)) -> [Hall.fxml](src\main\resources\Hall.fxml)
		- [x] 签到
		- [x] 商店 [ShopController](src\main\java\asia\sustech\happymatch\Hall\ShopController.java) -> [Shop.fxml](src\main\resources\Shop.fxml)
		- [x] 排行榜
		- [x] 自定义头像
		- [x] 自定义地图 [DIYMapController](src\main\java\asia\sustech\happymatch\DIYMap\DIYMapController.java) -> [DiyMap.fxml](src\main\resources\DiyMap.fxml)
	- [x] 游戏界面 [GameController](src\main\java\asia\sustech\happymatch\GameController\GameController.java) -> [Game.fxml](src\main\resources\Game.fxml)
	- [ ] 结算界面

## 致谢
 - [JavaLin - 非常棒的web服务框架](https://javalin.io/)

## 写在后面
第一次写project，架构和代码都不成熟，存在许多屎山成分。后端并不安全，日后慢慢维护吧。