# Companies Routes

## All routes starts with `/companies`

--- 

### GET `/{id}`

Response body expected:

   ```
   {
    "id": Long
    "name": String
    "cnpj": String
    "email": String
    "address": String
    }
   ```

#### Returns

* `200 - OK` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid) .
* `401 - UNAUTHORIZED` - token invalid
* `404 - NOT FOUND  ` - company dont exists.

--- 

### POST `/`

Request body expected:

   ```
   {
    "name": String
    "cnpj": String
    "email": String
    "password": String
    "address": String
    }
   ```

Response body expected:

   ```
   {
    "id": Long
    "name": String
    "cnpj": String
    "email": String
    "address": String
    }
   ```

#### Returns

* `201 - CREATED` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid) .
* `401 - UNAUTHORIZED` - token invalid
* `409 - CONFLICT  ` - company already exists.

--- 

### PUT `/`

Request body expected:

   ```
   {
    "id": Long
    "name": String
    "email": String
    "address": String
    }
   ```

Response body expected:

   ```
   {
    "id": Long
    "name": String
    "cnpj": String
    "email": String
    "address": String
    }
   ```

#### Returns

* `200 - OK` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid) .
* `401 - UNAUTHORIZED` - token invalid
* `404 - NOT FOUND  ` - company dont exists.

--- 

### DELETE `/{id}`


#### Returns

* `204 - NOT CONTENT` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid) .
* `401 - UNAUTHORIZED` - token invalid
* `404 - NOT FOUND  ` - company dont exists.

--- 

