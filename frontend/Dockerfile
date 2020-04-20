# build
FROM node as build

ARG API_PATH
ENV VUE_APP_API_PATH=$API_PATH

COPY . /opt
WORKDIR /opt
RUN npm i && npm run build


# final image
FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build ./opt/dist /usr/share/nginx/html
