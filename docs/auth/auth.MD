# Auth Routes

## All routes starts with `/auth`

--- 

### POST `/register/company`

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
    "tokens": {
        "accessToken": String
        "refreshToken": String
    },
    "company": {
        "id": Long
        "name": String
        "cnpj": String
        "email": String
        "address": String
    }
}
   ```

#### Returns

* `201 - CREATED` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid).

--- 

### POST `/register/candidate`

Request body expected:

   ```
   {
    "name": String
    "email": String
    "password": String
    "number": String
    }
   ```

Response body expected:

   ```
{
    "tokens": {
        "accessToken": String
        "refreshToken": String
    },
    "candidate": {
        "id": Long
        "name": String
        "email": String
        "password": String
        "number": String
    }
}
   ```

#### Returns

* `201 - CREATED` - Sucess.
* `400 - BAD REQUEST` - Invalid parameters(null parameters, size invalid).