# Cara menjalankan applikasi

1. Clone project
2. Clean project `mvn clean install spring-boot:run`
3. Testing api menggunakan postman / Curl


a. Testing Curl register api

`curl --location 'http://localhost:8888/api/v1/accounts/register' \
--header 'Content-Type: application/json' \
--data '{
    "user_name" : "dickyadriansyah",
    "role_id" : 2,
    "password" : "Joni123",
    "confirm_password" : "Joni123",
    "full_name" : "muhammad dicky adriansyah"
}'`

b. Testing Curl Login api

`curl --location 'http://localhost:8888/api/v1/accounts/login' \
--header 'Content-Type: application/json' \
--data '{
    "user_name" : "dickanirwansyah",
    "password" : "Sepuluhempat1996"
}'`

c. Tetsing Job list api

`curl --location 'http://localhost:8888/api/v1/job/list' \
--header 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaWNrYW5pcndhbnN5YWgiLCJpYXQiOjE2ODExNDYzNjgsImV4cCI6MTY4MTE0OTk2OH0.n7DsL594nInJPTR2oGAH2bxJFv8ym0ON323xQM0bX7u2wDAaLkXZmCYOoPezw3ukrPSH9uBCUS8O_X6VnPbmxg'`
