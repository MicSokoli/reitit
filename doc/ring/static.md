# Static Resources (Clojure Only)

Static resources can be served with a help of `reitit.ring/create-resource-handler`. It takes optionally an options map and returns a ring handler to serve files from Classpath.

There are two options to serve the files.

## Internal routes

This is good option if static files can be from non-conflicting paths, e.g. `"/assets/*"`.

```clj
(require '[reitit.ring :as ring])

(ring/ring-handler
  (ring/router
    [["/ping" (constantly {:status 200, :body "pong"})]
     ["/assets/*" (ring/create-resource-handler)]])
  (ring/create-default-handler))
```

To serve static files with conflicting routes, e.g. `"/*#`, one needs to disable the conflict resolution:

```clj
(require '[reitit.ring :as ring])

(ring/ring-handler
  (ring/router
    [["/ping" (constantly {:status 200, :body "pong"})]
     ["/*" (ring/create-resource-handler)]]
    {:conflicts (constantly nil)})
  (ring/create-default-handler))
```

## External routes

To serve files from conflicting paths, e.g. `"/*"`, one option is to mount them to default-handler branch of `ring-handler`. This way, they are only served if none of the actual routes have matched.


```clj
(ring/ring-handler
  (ring/router
    ["/ping" (constantly {:status 200, :body "pong"})])
  (ring/routes
    (ring/create-resource-handler {:path "/"})
    (ring/create-default-handler)))
```

## Configuration

`reitit.ring/create-resource-handler` takes optionally an options map to configure how the files are being served.

| key              | description |
| -----------------|-------------|
| :parameter       | optional name of the wildcard parameter, defaults to unnamed keyword `:`
| :root            | optional resource root, defaults to `"public"`
| :path            | optional path to mount the handler to. Works only if mounted outside of a router.
| :loader          | optional class loader to resolve the resources
| :allow-symlinks? | allow symlinks that lead to paths outside the root classpath directories, defaults to `false`

### TODO

* support for things like `:cache`, `:etag`, `:last-modified?`, `:index-files` and `:gzip`
* support for ClojureScript
* serve from file-system
