package com.adapterj.example.vertx

import io.vertx.ext.web.Router

class Hello : io.vertx.core.AbstractVerticle()  {
  override fun start() {

    var router = Router.router(vertx)

    router.route().handler({ routingContext ->
      routingContext.response().putHeader("content-type", "text/html").end("Hello World !!!!!!")
    })

    vertx.createHttpServer().requestHandler(router).listen(8080)
  }
}
