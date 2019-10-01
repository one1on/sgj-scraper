FROM gradle:jdk11
COPY . /app
WORKDIR /app
RUN cp .env.dist .env
RUN gradle build --no-daemon
CMD [ "gradle", "run", "--no-daemon"]