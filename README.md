# adapterj-example-vertx

点击[这里](https://github.com/york-deng/adapterj-example-vertx/blob/master/README_CN.md)阅读[中文说明](https://github.com/york-deng/adapterj-example-vertx/blob/master/README_CN.md)

An example based on Standard HTML Template (without any special syntax, tags, attributes), Vert.x, and [AdapterJ](https://github.com/york-deng/adapterj). 

## Environment Requirements
* JDK 1.8+
* Maven 3.6+
* Vert.x 3.8+

## Guide 
1. Download adapterj-example-vertx   
2. Unpack adapterj-example-vertx-master.zip   
3. Run these two Command Lines as below   
4. Open the [URL](http://localhost:8080/) with a web browser   

## Command Lines
```
cd adapterj-example-vertx
mvn clean package   
vertx run com.adapterj.example.vertx.Example -cp .:target/adapterj-example-vertx-1.0.0-all.jar   
```

## URL
http://localhost:8080/
