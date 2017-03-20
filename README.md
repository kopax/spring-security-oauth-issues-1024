Issue [#1024](https://github.com/spring-projects/spring-security-oauth/issues/1024)
=============

I am trying to secure oauth authentication and resource using **spring-security**

I have `ResourceServerConfigurerAdapter` class annotated with `@Order(99)` but the filter chain seems to be ignored.

These are my rules : 

An __anonymous user__ should be able to access only these endpoints

- http://localhost:8081/login
- http://localhost:8081/logout
- http://localhost:8081/ping

An __authenticated user__ with `ROLE_USER` should be able to access only these endpoints

- http://localhost:8081/oauth/authorize
- http://localhost:8081/oauth/token

An __authenticated user using a jwt__ can access these endpoints

- http://localhost:8081/**

Reproduction
------------

1. Clone the project

    git clone https://github.com/kopax/spring-security-oauth-issues-1024 && cd spring-security-oauth-issues-1024

2. start the server

    ./gradlew build --info && java -jar build/libs/api-oauth2.jar --spring.profiles.active=default

    
3. get a cookie

    export COOKIE=$(curl -v --silent http://localhost:8081/ 2>&1 | grep cookie -i | awk -F ' ' '{print $3}')
        
4. authenticate the cookie

    export COOKIE=$(curl --cookie "$COOKIE" -d username=admin -d password=verysecret -v --silent http://localhost:8081/login 2>&1 | grep cookie -i | awk -F ' ' '{print $3}')
 
5. try to get a secured oauth resource at `/`

    curl --cookie "$COOKIE" -v http://localhost:8081/
    
 
Expected
--------

http status code `401` due to missing header __Authorization__ 

Result
------

http status code `200`

_Note: if you remove `@Order(99)`_




Useful information:
===================

Spring server:

- http://localhost:8081/

Security account (cookie):

- username: `admin`
- password: `verysecret`

OAuth account (jwt):

- client_id: `myfirstapp`
- client_secret: `test`
- redirect_uri: `http://localhost:8081/`
- access_token_uri: `http://localhost:8081/oauth/token`
- authorization_uri: `http://localhost:8081/oauth/authorize`
- authorization_grant: `code`
- redirect_uri: `http://localhost:8081/cb/myfirstapp`

