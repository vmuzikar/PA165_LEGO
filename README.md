# PA165_LEGO

REST
-----

You can access the REST API by default at http://localhost:8080/pa165/rest/v1.

The API for Brick is at <http://localhost:8080/pa165/rest/v1/brick>.

Supported operations:


###### Create
`POST brick/create` creates a brick and consumes JSON with attributes name, width, height, e.g.:
```
curl -i -X POST -H "Content-Type: application/json" --data '{"name": "New Brick", "width": 8, "height": 10}' http://localhost:8080/pa165/rest/v1/brick/create
```

###### Get all
`GET brick` shows all bricks, e.g.:
```
curl -i http://localhost:8080/pa165/rest/v1/brick
```

###### Get one brick
`GET brick/{id}` gets details of a brick, e.g.:
```
curl -i http://localhost:8080/pa165/rest/v1/brick/1
```

###### Delete one brick
`DELETE brick/{id}` deletes a brick
```
curl -i -X DELETE http://localhost:8080/pa165/rest/v1/brick/1
```

###### Edit a brick
`PUT brick/{id}` updates a brick (consumes JSON as the create operation)
```
curl -i -X PUT -H "Content-Type: application/json" --data '{"name": "Edited Brick", "width": 8, "height": 10}' http://localhost:8080/pa165/rest/v1/brick/1
```


WEB
-----

Default usernames/passwords: `admin/admin1` and `user/user1`
