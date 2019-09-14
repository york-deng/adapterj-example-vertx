package com.adapterj.example.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

/**
 * 
 * @author York/GuangYu DENG
 */
public class Hello extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    final Router router = Router.router(vertx);
    
    router.route().handler(routingContext -> {
      routingContext.response().putHeader("content-type", "text/html").end("Hello World !!!!!!");
    });
    
    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
}
