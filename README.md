# PA165_LEGO

REST
-----

You can access the REST API by default at http://localhost:8080/pa165/rest/v1/.

The API for Brick is at http://localhost:8080/pa165/rest/v1/brick.

Supported operations:
* `POST brick/create` creates a brick and consumes JSON with attributes name, width, height
* `GET brick` shows all bricks
* `GET brick/{id}` gets details of a brick
* `DELETE brick/{id}` deletes a brick
* `PUT brick/{id}` updates a brick
