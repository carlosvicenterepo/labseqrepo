FROM node:18 as builder
WORKDIR /app
COPY package*.json ./
COPY src/labseq.conf /app
RUN npm install
COPY . .
RUN npm run build


FROM nginx:alpine
COPY --from=builder /app/dist/labseq_fe/ /usr/share/nginx/html
COPY --from=builder /app/labseq.conf /etc/nginx/conf.d/
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
