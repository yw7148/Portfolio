FROM amazoncorretto:17 as build

ARG DB_URL
ARG DB_USER
ARG DB_PW
ARG JWT_SECRET

ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PW=${DB_PW}
ENV JWT_SECRET=${JWT_SECRET}

WORKDIR /workspace/app

COPY . /workspace/app
RUN --mount=type=cache,target=/root/.gradle bash ./gradlew clean build
RUN mkdir -p ./build/dependency && (cd ./build/dependency; jar -xf ../libs/portfolio-*[0-9].jar)

FROM amazoncorretto:17

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/portfolio-*[0-9].jar", "-Dspring.profiles.active=${PROFILE}","portfolio.PortfolioApplication"]