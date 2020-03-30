# adapterj-example-vertx

Click [here](https://github.com/york-deng/adapterj-example-vertx/blob/master/README.md) in [English](https://github.com/york-deng/adapterj-example-vertx/blob/master/README.md)

一个基于，标准HTML模板(没有任何特殊的语法、标签、属性)、Vert.x，及[AdapterJ](https://github.com/york-deng/adapterj)的范例. 

## 运行环境
* JDK 1.8+
* Maven 3.6+
* Vert.x 3.8+

## 使用方法 
1. 运行以下命令行   
2. 在浏览器中打开[演示地址](http://localhost:8080/)   

## 命令行
```
git clone https://github.com/york-deng/adapterj.git
cd adapterj
mvn clean deploy

cd ..

git clone https://github.com/york-deng/adapterj-example-vertx.git
cd adapterj-example-vertx
mvn clean package   
vertx run com.adapterj.example.vertx.SimpleVerticle -cp .:target/adapterj-example-vertx-1.0.0-all.jar
```

## 演示地址
http://localhost:8080/
