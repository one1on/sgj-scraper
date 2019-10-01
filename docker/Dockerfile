FROM gradle:jdk11
COPY . /app
WORKDIR /app
RUN bash -c 'if [ -d /app/.gradle ]; then rm -R /app/.gradle; fi'
RUN bash -c 'if [ -d /app/docs ]; then rm -R /app/docs; fi'
RUN bash -c 'if [ -d /app/site ]; then rm -R /app/site; fi'
RUN bash -c 'if [ -d /app/build ]; then rm -R /app/build; fi'
RUN bash -c 'if [ -d /app/.env ]; then rm -R /app/.env; fi'
RUN gradle build --no-daemon
CMD [ "gradle", "run", "--no-daemon"]