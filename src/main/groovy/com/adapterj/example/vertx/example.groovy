import io.vertx.ext.web.Router

def path = com.adapterj.example.vertx.Example.class.getClassLoader().getResource("").getPath()

def router = Router.router(vertx)

router.route().handler({ routingContext ->
  routingContext.response().putHeader("content-type", "text/html").end("Hello World! - ${path}")
})

vertx.createHttpServer().requestHandler(router).listen(8080)
