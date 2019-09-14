var Router = require("vertx-web-js/router");

var path = Java.type("com.adapterj.example.vertx.Example").class.getClassLoader().getResource("").getPath();

var router = Router.router(vertx);

router.route().handler(function (routingContext) {
  routingContext.response().putHeader("content-type", "text/html").end("Hello World! - " + path);
});

vertx.createHttpServer().requestHandler(router.handle).listen(8080);
