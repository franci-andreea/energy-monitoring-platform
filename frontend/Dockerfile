FROM node

# set a directory for the app
WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent

# copy all the files to the container
RUN mkdir src
COPY . .
RUN npm run build

# production environment
FROM nginx
COPY --from=0 /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]  