# node setup to build 
FROM  node:latest as build
WORKDIR /usr/app
COPY ./package*.json /usr/app
RUN npm install --force --fetch-timeout=240000
COPY . /usr/app/
RUN npm run build

# nginx setup
FROM nginx:latest
WORKDIR /usr/web
COPY --from=build /usr/app/dist/angular-16-crud /usr/web/
COPY --from=build /usr/app/nginx.conf /etc/nginx/
