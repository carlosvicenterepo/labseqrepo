FROM node:18 AS ui-build
WORKDIR /usr/src/app
COPY / ./labseq_fe/
RUN cd labseq_fe && npm install @angular/cli && npm install && npm run build

FROM node:18 AS server-build
WORKDIR /usr/src/app
COPY --from=ui-build /usr/src/app/labseq_fe/dist ./labseq_fe/dist
COPY *.json ./
COPY src/* ./
RUN npm install

EXPOSE 4200
CMD ["npm", "start"]
