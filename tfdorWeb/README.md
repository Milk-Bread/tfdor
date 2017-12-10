# vueweb

> vue project

## 检查项目环境
### 一、检查是否安装node.js

``` 
# 检查node版本
$ node -v
# 如未安装
$ brew install node 
# 或者 
$ npm install node

```
### 二、安装vue.js环境

```

# 全局安装 vue-cli
$ npm install --global vue-cli

```
### 三、创建vue项目

```
# 创建一个基于 webpack 模板的新项目
$ vue init webpack my-project
# 安装依赖
$ cd my-project
$ npm install
# 安装淘宝镜像
$ npm install -g cnpm --registry=https://registry.npm.taobao.org
# 安装路由
$ cnpm install vue-router

```
### 四、引入 Mint-ui

```
# Vue 2.0
$ cnpm install mint-ui -S
# 引入全部组件
import Mint from 'mint-ui'
Vue.use(Mint)
```


### 五、运行、编译、打包

```
# 运行项目 localhost:9000
$ npm run dev

# 编译项目,打包
$ npm run build

# 构建产品并查看包分析器报告
$ npm run build --report

# 运行单元测试
$ npm run unit

# run e2e tests
$ npm run e2e

# run all tests
$ npm test
```

## 六、目录结构

```
├── VueWeb
│   ├── build                         #构建脚本目录
│   ├── config                        #构建配置目录
│   ├── node_modules                  #依赖的node工具包目录
│   ├── src                           #源码目录
│   │   │   ├── assets                #资源目录
│   │   │   │   └──logo.png
│   │   │   ├── components            #组件目录
│   │   │   │   └── HelloWorld.vue
│   │   │   ├── router                #路由配置
│   │   │   │   └── index.js
│   │   │   ├── App.vue               #页面级Vue组件
│   │   │   ├── main.js               #页面入口JS文件
│   ├── static                        #静态资源目录
│   ├── test                          #测试文件目录
│   ├── index.html                    #入口页面
│   └──package.json                   #项目描述文件


```