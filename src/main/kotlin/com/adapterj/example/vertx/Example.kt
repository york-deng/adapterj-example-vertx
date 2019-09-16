package com.adapterj.example.vertx

import io.vertx.ext.web.Router

class Example : io.vertx.core.AbstractVerticle()  {
  override fun start() {

    var path = com.adapterj.example.vertx.Example.`class`.getClassLoader().getResource("").getPath()

    var router = Router.router(vertx)

    router.route().handler({ routingContext ->
      routingContext.response().putHeader("content-type", "text/html").end("Hello World! - ${path}")
    })

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }
}
