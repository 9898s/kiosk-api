# 매장 테이블 예약 서비스

## 개요

- 이 프로젝트는 매장을 방문할때 미리 방문 예약을 진행하는 서비스입니다.
- 점장은 자신의 매장을 등록할 수 있습니다.
- 매장 목록과 세부 정보를 확인할 수 있습니다.
- 고객은 매장을 예약할 수 있으며 리뷰를 작성할 수 있습니다.

## 기능 사항

- 점장은 매정에 접수된 예약을 승인/거절 할 수 있습니다.
- 점장은 예약 서비스에 자신의 매장을 등록할 수 있습니다.
- 점장은 매장 서비스에 자신의 매장 예약 정보를 조회할 수 있습니다.
- 서비스 이용자들은 매장 검색, 상세 정보를 조회할 수 있습니다.
- 서비스 이용자들은 매장 리뷰를 조회할 수 있습니다.
- 서비스 가입자들은 매장을 예약할 수 있고, 리뷰를 남길 수 있습니다.
- 예약한 이후에, 예약 10분전에 도착하였는지 방문 확인을 진행합니다.

## 기술 스택

- JDK 11, Spring Boot 2.7.14
- Jpa, Validation, MySQL
- IntelliJ IDEA, Github

## 개발 기간

- 2023/08/ ~ 2023/08/25

## ERD

<hr>

## API 명세서

## 오류

<details>
<summary>더보기</summary>
예외가 발생했을 때, 본문에 해당 문제를 기술한 JSON 객체가 담겨있습니다.

| Path           | Type     | Description |
|----------------|----------|-------------|
| `errorCode`    | `String` | 에러 코드       |
| `errorMessage` | `String` | 에러 메세지      |

예를 들어, 이미 가입된 고객 이메일일 경우 다음과 같은 응답을 받게 됩니다.

``` http request
HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:31:06 GMT
Connection: close

{
  "errorCode": "EXIST_CUSTOMER_EMAIL",
  "errorMessage": "이미 가입된 고객 이메일입니다."
}
```

</details>

## 고객

<details>
<summary>더보기</summary>

> 고객 리소스는 계정 등록, 수정 삭제를 할 때 사용됩니다.

### 등록

`POST` 요청을 사용해서 새 계정을 등록할 수 있습니다.

#### Request fields

| Path       | Type     | Description |
|------------|----------|-------------|
| `email`    | `String` | 이메일         |
| `passowrd` | `String` | 비밀번호        |
| `phone`    | `String` | 휴대폰         |

#### Example request

``` http request
POST http://localhost:8080/api/customer/signup
Content-Type: application/json

{
  "email": "test@test.com",
  "password": "1234",
  "phone": "010-1111-1111"
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `phone`       | `String`        | 휴대폰         |
| `createdDate` | `LocalDateTime` | 등록일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:33:34 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com",
  "phone": "010-1111-1111",
  "createdDate": "2023-08-25 20:33:34"
}
```

### 수정

`PUT` 요청을 사용해서 계정을 수정할 수 있습니다.

#### Request fields

| Path       | Type     | Description |
|------------|----------|-------------|
| `email`    | `String` | 이메일         |
| `passowrd` | `String` | 비밀번호        |
| `phone`    | `String` | 휴대폰         |

#### Example request

``` http request
PUT http://localhost:8080/api/customer/update/1
Content-Type: application/json

{
  "email": "test@test.com2",
  "password": "1111",
  "phone": "010-1111-2222"
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `phone`       | `String`        | 휴대폰         |
| `updatedDate` | `LocalDateTime` | 수정일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:36:08 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com2",
  "phone": "010-1111-2222",
  "updatedDate": "2023-08-25 20:36:08"
}
```

### 삭제

`DELETE` 요청을 사용해서 계정을 삭제할 수 있습니다.

#### Path parameters

> /api/customer/delete/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `deletedYn`   | `Boolean`       | 삭제유무        |
| `deletedDate` | `LocalDateTime` | 삭제일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:38:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com2",
  "deletedYn": true,
  "deletedDate": "2023-08-25 20:38:57"
}
```

</details>

## 점장

<details>
<summary>더보기</summary>

> 점장 리소스는 계정을 만들 때 사용합니다.

### 등록

`POST` 요청을 사용해서 새 계정을 등록할 수 있습니다.

#### Request fields

| Path        | Type      | Description |
|-------------|-----------|-------------|
| `email`     | `String`  | 이메일         |
| `passowrd`  | `String`  | 비밀번호        |
| `partnerYn` | `Boolean` | 파트너 가입 유무   |

#### Example request

``` http request
POST http://localhost:8080/api/manager/signup
Content-Type: application/json

{
  "email": "test@test.com",
  "password": "1111",
  "partnerYn": true
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `partnerYn`   | `Boolean`       | 파트너 가입 유무   |
| `createdDate` | `LocalDateTime` | 등록일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:41:56 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com",
  "partnerYn": true,
  "createdDate": "2023-08-25 20:41:56"
}
```

### 수정

`PUT` 요청을 사용해서 계정을 수정할 수 있습니다.

#### Request fields

| Path        | Type      | Description |
|-------------|-----------|-------------|
| `email`     | `String`  | 이메일         |
| `passowrd`  | `String`  | 비밀번호        |
| `partnerYn` | `Boolean` | 파트너 가입 유무   |

#### Example request

``` http request
PUT http://localhost:8080/api/manager/update/1
Content-Type: application/json

{
  "email": "test@test.com",
  "password": "1111",
  "partnerYn": false
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `partnerYn`   | `Boolean`       | 파트너 가입 유무   |
| `updatedDate` | `LocalDateTime` | 수정일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:43:04 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com2",
  "partnerYn": false,
  "updatedDate": "2023-08-25 20:43:04"
}
```

### 삭제

`DELETE` 요청을 사용해서 계정을 삭제할 수 있습니다.

#### Path parameters

> /api/manager/delete/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `email`       | `String`        | 이메일         |
| `deletedYn`   | `Boolean`       | 삭제유무        |
| `deletedDate` | `LocalDateTime` | 삭제일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:43:49 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "email": "test@test.com2",
  "deletedYn": true,
  "deletedDate": "2023-08-25 20:43:49"
}
```

</details>

## 매장

<details>
<summary>더보기</summary>

> 매장 리소스는 매장 등록, 수정, 삭제, 검색, 정보, 리뷰 조회를 할 떄 사용합니다.

### 등록

`POST` 요청을 사용해서 매장을 등록할 수 있습니다.

#### Request fields

| Path          | Type     | Description |
|---------------|----------|-------------|
| `name`        | `String` | 이름          |
| `location`    | `String` | 위치          |
| `description` | `String` | 설명          |

#### Example request

``` http request
POST http://localhost:8080/api/shop/add
Content-Type: application/json

{
  "managerId": 1,
  "name": "카레 맛집",
  "location": "서울 특별시",
  "description": "카레 팝니다."
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `managerId`   | `Long`          | 점장 고유값      |
| `name`        | `String`        | 이름          |
| `location`    | `String`        | 위치          |
| `description` | `String`        | 설명          |
| `createdDate` | `LocalDateTime` | 등록일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:45:38 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "managerId": 1,
  "name": "카레 맛집",
  "location": "서울 특별시",
  "description": "카레 팝니다.",
  "createdDate": "2023-08-25 20:45:38"
}
```

### 수정

`PUT` 요청을 사용해서 매장 정보를 수정할 수 있습니다.

#### Path parameters

> /api/shop/update/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Request fields

| Path          | Type     | Description |
|---------------|----------|-------------|
| `name`        | `String` | 이름          |
| `location`    | `String` | 위치          |
| `description` | `String` | 설명          |

#### Example request

``` http request
PUT http://localhost:8080/api/shop/update/1
Content-Type: application/json

{
  "name": "초밥 맛집",
  "location": "강원도",
  "description": "초밥 팝니다."
}
```

#### Response fields

| Path          | Type            | Description |
|---------------|-----------------|-------------|
| `id`          | `Long`          | 고유값         |
| `name`        | `String`        | 이름          |
| `location`    | `String`        | 위치          |
| `description` | `String`        | 설명          |
| `createdDate` | `LocalDateTime` | 등록일         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:49:23 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "name": "초밥 맛집",
  "location": "강원도",
  "description": "초밥 팝니다.",
  "updatedDate": "2023-08-25 20:49:23"
}
```

### 삭제

`PUT` 요청을 사용해서 매장을 삭제할 수 있습니다.

#### Path parameters

> /api/shop/delete/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Example response

``` http request
HTTP/1.1 200 
Content-Length: 0
Date: Fri, 25 Aug 2023 11:52:02 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>
```

### 검색

`GET` 요청을 사용해서 매장을 검색할 수 있습니다.

#### Path parameters

> /api/shop/search/{name}

| Path   | Type     | Description |
|--------|----------|-------------|
| `name` | `String` | 이름          |

#### Response fields

| Path            | Type     | Description |
|-----------------|----------|-------------|
| `totalCount`    | `Long`   | 매장수         |
| `list.id`       | `Long`   | 고유값         |
| `list.name`     | `String` | 이름          |
| `list.location` | `String` | 위치          |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:52:55 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "totalCount": 2,
  "list": [
    {
      "id": 2,
      "name": "카레 맛집",
      "location": "서울 특별시"
    },
    {
      "id": 3,
      "name": "카레 맛집2",
      "location": "서울 특별시"
    }
  ]
}
```

### 정보

`GET` 요청을 사용해서 매장 정보를 확인할 수 있습니다.

#### Path parameters

> /api/shop/detail/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Response fields

| Path          | Type     | Description |
|---------------|----------|-------------|
| `id`          | `Long`   | 고유값         |
| `name`        | `String` | 이름          |
| `location`    | `String` | 위치          |
| `description` | `String` | 설명          |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 11:59:07 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "name": "카레 맛집",
  "location": "서울 특별시",
  "description": "카레 팝니다."
}
```

### 리뷰

`GET` 요청을 사용해서 매장 리뷰를 확인할 수 있습니다.

#### Path parameters

> /api/shop/review/{id}

| Path | Type   | Description |
|------|--------|-------------|
| `id` | `Long` | 고유값         |

#### Response fields

| Path                 | Type            | Description |
|----------------------|-----------------|-------------|
| `totalCount`         | `Long`          | 리뷰수         |
| `list.customerEmail` | `String`        | 작성자         |
| `list.contents`      | `String`        | 내용          |
| `list.createdDate`   | `LocalDateTime` | 작성일자        |

#### Example response

``` http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Aug 2023 12:03:20 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "totalCount": 2,
  "list": [
    {
      "customerEmail": "test@test.com",
      "contents": "맛있습니다. 강추!",
      "createdDate": "2023-08-25 21:03:05"
    },
    {
      "customerEmail": "test@test.com",
      "contents": "맛있습니다. 강추!",
      "createdDate": "2023-08-25 21:03:05"
    }
  ]
}
```

</details>