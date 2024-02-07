FROM node:current-alpine as build-step

RUN mkdir /app
WORKDIR /app
COPY . /app

RUN npm install -g npm@latest
RUN npm install
RUN npm run build


FROM nginx:stable-alpine

RUN apk -U upgrade  && rm -rf /var/cache/apk/*

COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build-step /app/build /usr/share/nginx/html

RUN touch /var/run/nginx.pid
RUN chown -R nginx:nginx /var/run/nginx.pid /usr/share/nginx/html /var/cache/nginx /var/log/nginx /etc/nginx/conf.d
USER nginx

EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
