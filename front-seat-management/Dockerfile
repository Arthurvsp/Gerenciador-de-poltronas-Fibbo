# Stage 1: Build Angular
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# Força o build dentro do container e garante saída no caminho esperado
RUN npm run build -- --output-path=/app/dist/my-app --base-href=/

# Stage 2: Nginx
FROM nginx:alpine
COPY --from=build /app/dist/my-app /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
