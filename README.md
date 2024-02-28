# Youngwon's Portfolio
> Backend/Full-stack Developer **Youngwon**  
> Portfolio url: http://youngwon.me

![youngwon](https://github.com/yw7148/Portfolio_Backend/assets/71220342/18b6f1a7-82c1-4f8c-a779-350cfef28dea)

## Environment
> for DevOps information, please refer [Server Repository](https://github.com/yw7148/Server)

- Spring boot 3.2.1 (Java 17)

- Thymeleaf/HTML/css/js

- Mariadb 11 (Local Development)

- Oracle Database 19c (Production)

## Build
### Build docker image
> In my case, *--platform linux/amd64,linux/arm64* is added to support multi-platform (my production environment is *linux/arm64*)

> to build multi-platform image, docker buildx builder is required in *docker build* environment:  
> *docker buildx create --name mybuilder --bootstrap --use*  
> for more information: [Multi-Platform Images | Docker Docker](https://docs.docker.com/build/building/multi-platform/)
```bash
DOCKER_BUILDKIT=1 docker buildx build \
    --platform linux/amd64,linux/arm64 \ #option to support multi-platform
    --build-arg DB_URL=${TEST_DB_URL} \
    --build-arg DB_USER=${TEST_DB_USER} \
    --build-arg DB_PW=${TEST_DB_PASSWORD} \
    --build-arg JWT_SECRET=${TEST_JWT_SECRET} \
    -t yw7148/portfolio:latest .'
```

## Deploy
#### Before deploy with docker, env file is required
.env file
```
 DB_URL=
 DB_USER=
 DB_PW=
 JWT_SECRET=
```
### Run docker container
```bash
docker run --pull always -d --name portfolio -p 9001:9001 \
    -e PROFILE=prod \
    --env-file ${path_to_envfile} \
    yw7148/portfolio:latest
```