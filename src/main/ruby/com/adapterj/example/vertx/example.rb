require 'vertx-web/router'

path = Java::ComAdapterjExampleVertx::Example::class.get_class_loader().get_resource("").get_path()

router = VertxWeb::Router.router($vertx)

router.route().handler() { |routingContext|
  routingContext.response().put_header("content-type", "text/html").end("Hello World! - #{path}")
}

$vertx.create_http_server().request_handler(&router.method(:handle)).listen(8080)
