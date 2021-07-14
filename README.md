# app-sales

docker build --tag appsales .

docker run --env URL_CFG_SERVER="http://ip172-18-0-16-c3ngebvqf8u00094pgo0-9999.direct.labs.play-with-docker.com/" -p 8080:8080 appsales