FROM ruby:2.5-alpine as jekyll-base

RUN apk add --no-cache build-base gcc bash cmake git nodejs-npm

#RUN gem update --system

RUN gem install jekyll

RUN echo 'http://dl-cdn.alpinelinux.org/alpine/v3.5/main' >> /etc/apk/repositories
RUN apk add --no-cache imagemagick-dev=6.9.6.8-r1
RUN gem install rmagick

EXPOSE 4000

WORKDIR /site

ENTRYPOINT [ "bundle", "exec", "jekyll" ]

CMD [ "--help" ]

##
##
## New stage in multi-stage image
FROM jekyll-base

# create new site by setting -e JEKYLL_NEW=true
ENV JEKYLL_NEW false

COPY docker-entrypoint.sh /usr/local/bin/

# on every container start we'l'
ENTRYPOINT [ "docker-entrypoint.sh" ]

CMD [ "bundle", "exec", "jekyll", "serve", "--force_polling", "-H", "0.0.0.0", "-P", "4000" ]
